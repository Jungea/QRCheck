package net.skhu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.skhu.domain.Registration;
import net.skhu.domain.Student;
import net.skhu.repository.AdminRepository;
import net.skhu.repository.RegistrationRepository;
import net.skhu.repository.StudentRepository;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	RegistrationRepository registrationRepository;

	public int getSemId() {
		return adminRepository.findById(1).get().getSemester().getId();
	}
	
	// 페이지 내의 로그아웃 버튼을 클릭
	@RequestMapping(value = "logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userNum");
		return "redirect:/login";
	}

	@RequestMapping("list/{stuId}")
	public String list(Model model, @PathVariable("stuId") int stuId) {
		List<Registration> list = registrationRepository.findByStudent_IdAndCourse_Semester_Id(stuId, getSemId());
		model.addAttribute("list", list);
		return "student/list";
	}
	
	@RequestMapping("check")
	public String check(Model model, int studentId, int courseId) {
		List<Student> list = studentRepository.findAll();
		model.addAttribute("list", list);
		return "student/list";
	}
	
	@RequestMapping("insertRegistration")
	public String insertRegistration(Model model, int studentId, int courseId) {
		List<Student> list = studentRepository.findAll();
		model.addAttribute("list", list);
		return "student/list";
	}

}