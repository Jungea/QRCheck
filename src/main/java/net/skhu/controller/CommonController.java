package net.skhu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.domain.Admin;
import net.skhu.domain.Student;
import net.skhu.repository.AdminRepository;
import net.skhu.repository.ProfessorRepository;
import net.skhu.repository.RegistrationRepository;
import net.skhu.repository.StudentRepository;

@Controller
public class CommonController {
	
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ProfessorRepository professorRepository;
	@Autowired
	RegistrationRepository registrationRepository;

	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
 
	// 로그인 화면에서 로그인버튼 클릭
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Model model,  @RequestParam("num") String num, @RequestParam("pass") String pass, HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(num + " " + pass);
		
		if(adminRepository.findByAdminNumAndPassword(num, pass) != null) {
			System.out.println("관리자");
			session.setAttribute("userNum", num);
			return "redirect:professor/list";
		} else if(professorRepository.findByProfNumAndPassword(num, pass)!= null){
			System.out.println("교수");
			session.setAttribute("userNum", num);
			return "redirect:professor/list";
		}else if(studentRepository.findByStuNumAndPassword(num, pass)!=null) {
			System.out.println("학생");
			session.setAttribute("userNum", num);
			return "redirect:professor/list";	
		}else {
			System.out.println("잘못된 입력");
			return "login";
		}
	}
	

	
	@RequestMapping("insertRegi/{stuId}/{courId}/{divId}")
	public Student student(@PathVariable("id") int id) {
		return studentRepository.findById(id).get();
	}

}
