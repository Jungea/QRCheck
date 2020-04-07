package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.skhu.domain.Professor;
import net.skhu.domain.Student;
import net.skhu.repository.DepartmentRepository;
import net.skhu.repository.ProfessorRepository;
import net.skhu.repository.StudentRepository;

@Controller
@RequestMapping("professor")
public class ProfessorController {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	ProfessorRepository professorRepository;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {
		Student Student = new Student();
		model.addAttribute("Student", Student);
		return "login";
	}

	// 로그인 화면에서 로그인버튼 클릭
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public String login(Model model, Student Student, HttpServletRequest request) {
//		System.out.println(Student.getStudentId() + " " + Student.getStudentPass());
//		Student u = StudentRepository.findByStudentIdAndStudentPass(Student.getStudentId(), Student.getStudentPass()).get(0);
//		if (u != null) {
//			HttpSession session = request.getSession();
//			session.setAttribute("Student", u);
//			System.out.println(u);
//			return "redirect:list";
//		} else
//			System.out.println(u);
//		return "login/login";
//	}

//	// 페이지 내의 로그아웃 버튼을 클릭
//	@RequestMapping(value = "logout")
//	public String logout(Model model, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		session.removeAttribute("Student");
//		return "redirect:login";
//	}
	
	@RequestMapping("list")
	public String list(Model model) {
		Professor professor = professorRepository.findById(5).get();
		model.addAttribute("list", professor.getCourses());
		return "professor/list";
	}

}