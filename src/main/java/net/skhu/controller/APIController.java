package net.skhu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.skhu.domain.Course;
import net.skhu.domain.Professor;
import net.skhu.domain.Registration;
import net.skhu.domain.Student;
import net.skhu.etc.Att;
import net.skhu.repository.AttendanceRepository;
import net.skhu.repository.ProfessorRepository;
import net.skhu.repository.RegistrationRepository;
import net.skhu.repository.StudentRepository;

@RestController
@RequestMapping("api")
public class APIController {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ProfessorRepository professorRepository;
	@Autowired
	RegistrationRepository registrationRepository;
	@Autowired
	AttendanceRepository attendanceRepository;

	@RequestMapping("login/{stuNum}/{password}")
	public Student login(@PathVariable("stuNum") String stuNum, @PathVariable("password") String password) {
		return studentRepository.findByStuNumAndPassword(stuNum, password);
	}

	@RequestMapping("student/{id}")
	public Student student(@PathVariable("id") int id) {
		return studentRepository.findById(id).get();
	}

	@RequestMapping("student/{id}/registrations")
	public List<Registration> studentRegistrations(@PathVariable("id") int id) {
		Student student = studentRepository.findById(id).get();
		return student.getRegistrations();
	}

	@RequestMapping("student/{id}/attendances")
	public List<Att> studentAttendances(@PathVariable("id") int id) {
		Student student = studentRepository.findById(id).get();
		List<Registration> registrations = student.getRegistrations();

		List<Att> attList = new ArrayList<Att>();
		for (Registration r : registrations) {
			Att att = new Att();
			att.setName(r.getCourse().getName());
			List<Integer[]> groupList = attendanceRepository.findAttendanceCountOfStateByRegistration_Id(r.getId());
			for (int i = 0; i < groupList.size(); ++i)
				att.getState().set(groupList.get(i)[0] - 1, groupList.get(i)[1]);

			attList.add(att);
		}

		return attList;
	}

	@RequestMapping("student/{id}/courses")
	public List<Course> studentCourses(@PathVariable("id") int id) {
		Student student = studentRepository.findById(id).get();
		List<Course> list = new ArrayList<Course>();
		for (Registration r : student.getRegistrations())
			list.add(r.getCourse());
		return list;
	}

	// service클래스생성
	@RequestMapping("professor/{id}/courses")
	public List<Course> professorcourses(@PathVariable("id") int id) {
		Professor professor = professorRepository.findById(id).get();
		return professor.getCourses();
	}

	@RequestMapping("registration/{sid}/{cid}")
	public List<Student> registration(@PathVariable("sid") int sid, @PathVariable("cid") int cid) {
		System.out.println("[내꺼] 실행되긴 하니?");
		Registration registration = registrationRepository.findByStudent_IdAndCourse_Id(sid, cid).get(0);
		registration = registrationRepository.findById(registration.getId()).get();
//		registration.setState(1);
		registrationRepository.save(registration);
		System.out.println("출석체크" + sid + " " + cid);
//		return registration;
		return studentRepository.findAll();
	}

	@RequestMapping("students")
	public List<Student> students() {
		return studentRepository.findAll();
	}

	@RequestMapping("professors")
	public List<Professor> professors() {
		return professorRepository.findAll();
	}

	@RequestMapping("registrations")
	public List<Registration> registrations() {
		return registrationRepository.findAll();
	}

}
