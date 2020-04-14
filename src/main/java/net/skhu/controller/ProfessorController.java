package net.skhu.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.domain.Course;
import net.skhu.domain.Professor;
import net.skhu.domain.Time;
import net.skhu.etc.DateCard;
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
		model.addAttribute("list", professor.getCourses());
		return "professor/list";
	}

	@RequestMapping("dateList")
	public String dateList(Model model, @RequestParam("id") int id, HttpServletRequest request) {
		
		List<DateCard> list = new ArrayList<>();
		Course c = courseRepository.findById(id).get();

		List<Time> times = c.getTimes();

		int attNum = 0;
		for (int i = 0; i < 15; ++i) {
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
			card.setCheckNum(attendanceRepository.findByNumAndStateNotAndRegistration_Course_id(attNum, 0, id).size());

			// 총인원
			card.setTotalNum(c.getRegistrations().size());

			list.add(card);
		}
		
		HttpSession session = request.getSession();
		String userNum = (String) session.getAttribute("userNum");
		Professor professor = professorRepository.findByProfNum(userNum);
		model.addAttribute("user", professor);

		model.addAttribute("list", list);
		
		model.addAttribute("courId", id);
		return "professor/dataList";
	}

	@RequestMapping("personList")
	public String personList(Model model, @RequestParam("id") int id, HttpServletRequest request) {
		System.out.println(id);
		HttpSession session = request.getSession();
		String userNum = (String) session.getAttribute("userNum");
		Professor professor = professorRepository.findByProfNum(userNum);
		model.addAttribute("user", professor);
		model.addAttribute("list", professor.getCourses());
		return "professor/list";
	}

}