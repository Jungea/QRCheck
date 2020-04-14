package net.skhu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.skhu.domain.Attendance;
import net.skhu.domain.Registration;
import net.skhu.domain.Student;
import net.skhu.repository.AttendanceRepository;
import net.skhu.repository.CourseRepository;
import net.skhu.repository.DivisionRepository;
import net.skhu.repository.ProfessorRepository;
import net.skhu.repository.RegistrationRepository;
import net.skhu.repository.StudentRepository;

@RestController
@RequestMapping("super")
public class SuperController {
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
	@RequestMapping(value = "logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userNum");
		return "redirect:/login";
	}

	
	@RequestMapping("insertRegi/{stuId}/{courId}/{divId}")
	public void insertRegi(@PathVariable("stuId") int stuId, @PathVariable("courId") int courId, @PathVariable("divId") int divId) {
		Registration r = new Registration();
		r.setStudent(studentRepository.findById(stuId).get());
		r.setCourse(courseRepository.findById(courId).get());
		r.setDivision(divisionRepository.findById(divId).get());
		r=registrationRepository.save(r);
		
		for(int i=1; i<16; ++i) {
			Attendance a = new Attendance();
			a.setNum(i);
			a.setState(0);
			a.setRegistration(registrationRepository.findById(2).get());
			attendanceRepository.save(a);
		}
		
	}

}
