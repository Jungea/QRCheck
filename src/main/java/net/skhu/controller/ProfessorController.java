package net.skhu.controller;

import java.time.LocalDate;
import java.time.LocalTime;
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

	// 페이지 내의 로그아웃 버튼을 클릭
	@RequestMapping("logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userNum");
		return "redirect:/login";
	}

	@RequestMapping("list")
	public String list(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userNum = (String) session.getAttribute("userNum");
		Professor professor = professorRepository.findByProfNum(userNum);
		model.addAttribute("user", professor);
		model.addAttribute("list", courseRepository.findByProfessor_IdAndSemester_Id(professor.getId(), getSemId()));
		return "professor/list";
	}

	@RequestMapping(value = "dateList")
	public String dateList(Model model, @RequestParam("courId") int courId, HttpServletRequest request) {

		List<DateCard> list = new ArrayList<>();
		Course c = courseRepository.findById(courId).get();

		List<Time> times = c.getTimes();

		int attNum = 0;
		int i = 0;
		for (; i < 15; ++i) {
			DateCard card = new DateCard();

			// 차시
			card.setAttNum(++attNum);

			if (c.getSplit() == false || attNum % 2 == 1) {
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

		model.addAttribute("course", c);
		model.addAttribute("list", list);

		model.addAttribute("courId", courId);
		return "professor/dataList";
	}

	// QR코드 Modal 열고 닫을 때 course.show 변화
	// 특히 닫아질 때는 state가 1이 아닌 학생 다 결석으로
	// attendance.registration.course.id =3 and state=0 setState(2);

	@RequestMapping(value = "personList", method = RequestMethod.POST)
	public String personList(Model model, @RequestParam("courId") int courId, @RequestParam("attNum") int attNum,
			@RequestParam("state") int[] state, HttpServletRequest request) {

		List<Attendance> attendances = attendanceRepository.findByRegistration_Course_IdAndNum(courId, attNum);

		for (int i = 0; i < attendances.size(); ++i) {
			attendances.get(i).setState(state[i]);
			attendanceRepository.save(attendances.get(i));
		}

		return "redirect:dateList?courId=" + courId;
	}

	@RequestMapping(value = "personList", method = RequestMethod.GET)
	public String personList(Model model, @RequestParam("courId") int courId, @RequestParam("attNum") int attNum,
			HttpServletRequest request) {
		System.out.println(courId + " " + attNum);

		List<Attendance> attendances = attendanceRepository.findByRegistration_Course_IdAndNum(courId, attNum);
		model.addAttribute("modalList", attendances);

		dateList(model, courId, request);

		return "professor/dataList";
	}

}