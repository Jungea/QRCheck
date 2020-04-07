package net.skhu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.skhu.domain.Course;
import net.skhu.domain.Department;
import net.skhu.domain.Professor;
import net.skhu.domain.Registration;
import net.skhu.domain.Student;
import net.skhu.repository.DepartmentRepository;
import net.skhu.repository.ProfessorRepository;
import net.skhu.repository.RegistrationRepository;
import net.skhu.repository.StudentRepository;

@RestController
@RequestMapping("api")
public class APIController {
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ProfessorRepository professorRepository;
	@Autowired
	RegistrationRepository registrationRepository;

	@RequestMapping("departments")
	public List<Department> departments() {
		return departmentRepository.findAll();
	}

	@RequestMapping("department/{id}")
	public Department department(@PathVariable("id") int id) {
		return departmentRepository.findById(id).get();
	}
	
	@RequestMapping("student/{id}")
	public Student student(@PathVariable("id") int id) {
		return studentRepository.findById(id).get();
	}

	@RequestMapping("department/{id}/students")
	public List<Student> departmentStudents(@PathVariable("id") int id) {
		Department department = departmentRepository.findById(id).get();
		return department.getStudents();
	}

	@RequestMapping("department/{id}/professors")
	public List<Professor> departmentProfessors(@PathVariable("id") int id) {
		Department department = departmentRepository.findById(id).get();
		return department.getProfessors();
	}

	@RequestMapping("student/{id}/registrations")
	public List<Registration> studentRegistrations(@PathVariable("id") int id) {
		Student student = studentRepository.findById(id).get();
		return student.getRegistrations();
	}

	@RequestMapping("student/{id}/courses")
	public List<Course> studentCourses(@PathVariable("id") int id) {
		Student student = studentRepository.findById(id).get();
		List<Course> list = new ArrayList<Course>();
		for (Registration r : student.getRegistrations())
			list.add(r.getCourse());
		return list;
	}

	//service클래스생성
	@RequestMapping("professor/{id}/courses")
	public List<Course> professorcourses(@PathVariable("id") int id) {
		Professor professor = professorRepository.findById(id).get();
		return professor.getCourses();
	}
	
	@RequestMapping("registration/{sid}/{cid}")
	public Registration registration(@PathVariable("sid") int sid, @PathVariable("cid") int cid) {
		System.out.println("[내꺼] 실행되긴 하니?");
		Registration registration = registrationRepository.findByStudent_IdAndCourse_Id(sid, cid).get(0);		
		registration = registrationRepository.findById(registration.getId()).get();
		registration.setState(1);
		registrationRepository.save(registration);
		return registration;
	}
	
	@RequestMapping("students")
	public List<Student> students() {
		return studentRepository.findAll();
	}

	@RequestMapping("professors")
	public List<Professor> professors() {
		return professorRepository.findAll();
	}

}
