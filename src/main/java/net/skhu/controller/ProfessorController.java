package net.skhu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.domain.Attendance;
import net.skhu.domain.Course;
import net.skhu.domain.Professor;
import net.skhu.domain.Time;
import net.skhu.etc.DateCard;
import net.skhu.repository.AdminRepository;
import net.skhu.repository.AttendanceRepository;
import net.skhu.repository.CourseRepository;
import net.skhu.repository.DivisionRepository;
import net.skhu.repository.ProfessorRepository;
import net.skhu.repository.RegistrationRepository;
import net.skhu.repository.StudentRepository;

@Controller
@RequestMapping("professor")
public class ProfessorController {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ProfessorRepository professorRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	RegistrationRepository registrationRepository;
	@Autowired
	DivisionRepository divisionRepository;
	@Autowired
	AttendanceRepository attendanceRepository;
	@Autowired
	AdminRepository adminRepository;

	public int getSemId() {
		return adminRepository.findById(1).get().getSemester().getId();
	}

	public int getSemWeekNum() {
		return adminRepository.findById(1).get().getSemester().getWeekNum();
	}

	// 페이지 내의 로그아웃 버튼을 클릭
	@RequestMapping("logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userNum");
		return "redirect:/login";
	}

	// 강의 목록
	@RequestMapping("list")
	public String list(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userNum = (String) session.getAttribute("userNum");
		Professor professor = professorRepository.findByProfNum(userNum);
		model.addAttribute("user", professor);

		// 이번학기 해당 교수의 강의 목록
		model.addAttribute("list", courseRepository.findByProfessor_IdAndSemester_Id(professor.getId(), getSemId()));
		return "professor/list";
	}

	// 해당 강의의 주차별 목록
	// personModal에 값 없음
	@RequestMapping(value = "dateList")
	public String dateList(Model model, @RequestParam("courId") int courId, HttpServletRequest request) {
		// 주차별 목록을 위한 클래스
		List<DateCard> list = new ArrayList<>();

		Course c = courseRepository.findById(courId).get();
		List<Time> times = c.getTimes();

		int attNum = 0; // 차시
		int i = 0; // 주차
		for (; i < getSemWeekNum(); ++i) {
			DateCard card = new DateCard();

			// 차시
			card.setAttNum(++attNum);

			if (c.getSplit() == false || attNum % 2 == 1) { // 분할 강의가 아니거나 분할 강의의 앞 강의일 때
				// 날짜
				card.setDate(times.get(0).getStartDate().plusWeeks(i));

				// 시간
				card.setStartTime(times.get(0).getStartTime());
				card.setEndTime(times.get(0).getEndTime());

			} else {
				// 날짜
				card.setDate(times.get(1).getStartDate().plusWeeks(--i));

				// 시간
				card.setStartTime(times.get(1).getStartTime());
				card.setEndTime(times.get(1).getEndTime());
			}

			// 출석체크 인원
			card.setCheckNum(
					attendanceRepository.findByNumAndStateNotAndRegistration_Course_id(attNum, 0, courId).size());

			// 총인원
			card.setTotalNum(c.getRegistrations().size());

			list.add(card);
		}

		// ※위의 반복문이 분할 강의 일 때 마지막 주차가 실행되지 않음.※
		if (times.size() != 1) {
			DateCard card = new DateCard();
			card.setAttNum(++attNum);
			card.setDate(times.get(1).getStartDate().plusWeeks(--i));
			card.setStartTime(times.get(1).getStartTime());
			card.setEndTime(times.get(1).getEndTime());
			card.setCheckNum(
					attendanceRepository.findByNumAndStateNotAndRegistration_Course_id(attNum, 0, courId).size());
			card.setTotalNum(c.getRegistrations().size());
			list.add(card);
		}

		HttpSession session = request.getSession();
		String userNum = (String) session.getAttribute("userNum");
		Professor professor = professorRepository.findByProfNum(userNum);
		model.addAttribute("user", professor);

		model.addAttribute("course", c); // 페이지 상에서 필요
		model.addAttribute("list", list);
		model.addAttribute("courId", courId); // javascirpt단의 처리에서 필요(url)

		return "professor/dataList";
	}

	// 주차별 목록에서 하나를 클릭할 경우
	// 해당 정보를 얻어 모달에 적용
	// 그냥 dataList에서는 모달에 값 없음
	@RequestMapping(value = "personList", method = RequestMethod.GET)
	public String personList(Model model, @RequestParam("courId") int courId, @RequestParam("attNum") int attNum,
			HttpServletRequest request) {

		// 해당 과목과 해당 차시의 attendance를 찾음.
		List<Attendance> attendances = attendanceRepository.findByRegistration_Course_IdAndNum(courId, attNum);
		model.addAttribute("modalList", attendances);
		model.addAttribute("attNum", attNum);
		dateList(model, courId, request);

		return "professor/dataList";
	}

	// personModal의 저장을 하였을 경우
	// state 즉 출석 상태 셀렉션의 값이 배열로 넘어옴.
	@RequestMapping(value = "personList", method = RequestMethod.POST)
	public String personList(Model model, @RequestParam("courId") int courId, @RequestParam("attNum") int attNum,
			@RequestParam("state") int[] state, HttpServletRequest request) {
		System.out.println(Arrays.toString(state));
		// 보여줬던 학생 리스트를 다시 찾아서
		List<Attendance> attendances = attendanceRepository.findByRegistration_Course_IdAndNum(courId, attNum);

		// 그 순서대로 출석 상태 변경 후 저장
		for (int i = 0; i < attendances.size(); ++i) {
			attendances.get(i).setState(state[i]);
			attendanceRepository.save(attendances.get(i));
		}

		return "redirect:dateList?courId=" + courId;
	}

	// personModal의 전체 출석
	@RequestMapping(value = "allCheck")
	public String allCheck(Model model, @RequestParam("courId") int courId, @RequestParam("attNum") int attNum,
			HttpServletRequest request) {
		// 보여줬던 학생 리스트를 다시 찾아서
		List<Attendance> attendances = attendanceRepository.findByRegistration_Course_IdAndNum(courId, attNum);

		// 그 순서대로 출석 상태 변경 후 저장
		for (int i = 0; i < attendances.size(); ++i) {
			attendances.get(i).setState(1);
			attendanceRepository.save(attendances.get(i));
		}

		return "redirect:dateList?courId=" + courId;
	}

}