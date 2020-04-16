package net.skhu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

import net.skhu.domain.Attendance;
import net.skhu.domain.Registration;
import net.skhu.domain.Room;
import net.skhu.etc.QRCodeGenerator;
import net.skhu.repository.AttendanceRepository;
import net.skhu.repository.CourseRepository;
import net.skhu.repository.DivisionRepository;
import net.skhu.repository.ProfessorRepository;
import net.skhu.repository.RegistrationRepository;
import net.skhu.repository.RoomRepository;
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
	@Autowired
	RoomRepository roomRepository;

	// 페이지 내의 로그아웃 버튼을 클릭
	@RequestMapping(value = "logout")
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userNum");
		return "redirect:/login";
	}

	// 학생이 강의 신청 &
	@RequestMapping("insertRegi/{stuId}/{courId}/{divId}")
	public void insertRegi(@PathVariable("stuId") int stuId, @PathVariable("courId") int courId,
			@PathVariable("divId") int divId) {
		Registration r = new Registration();
		r.setStudent(studentRepository.findById(stuId).get());
		r.setCourse(courseRepository.findById(courId).get());
		r.setDivision(divisionRepository.findById(divId).get());
		r = registrationRepository.save(r);

		for (int i = 1; i < 16; ++i) {
			Attendance a = new Attendance();
			a.setNum(i);
			a.setState(0);
			a.setRegistration(registrationRepository.findById(2).get());
			attendanceRepository.save(a);
		}

	}

	@RequestMapping("insert")
	public void insert() {
		int courId = 10;
		int divId = 16;
		for (int j = 64; j < 74; ++j) {
			Registration r = new Registration();
			r.setStudent(studentRepository.findById(j).get());
			r.setCourse(courseRepository.findById(courId).get());
			r.setDivision(divisionRepository.findById(divId).get());
			r = registrationRepository.save(r);

			for (int i = 1; i < 16; ++i) {
				Attendance a = new Attendance();
				a.setNum(i);
				a.setState(0);
				a.setRegistration(registrationRepository.findById(2).get());
				attendanceRepository.save(a);
			}
		}
	}

	@RequestMapping("update")
	public void update() {
		int courId = 8;
		int divId = 13;
		for (int j = 14; j < 24; ++j) {
			Registration r = registrationRepository.findByStudent_IdAndCourse_Id(j, courId);
			r.setDivision(divisionRepository.findById(divId).get());
			registrationRepository.save(r);

		}
	}

	@RequestMapping("insertAttendance")
	public void insertAttendance() {
		List<Registration> registration = registrationRepository.findAll();
		int weekSize;
		for (int j = 0; j < registration.size(); ++j) {
			weekSize = (registration.get(j).getCourse().getTimes().size() == 1) ? 16 : 31;
			for (int i = 1; i < weekSize; ++i) {
				Attendance a = new Attendance();
				a.setNum(i);
				a.setState(0);
				a.setRegistration(registration.get(j));
				attendanceRepository.save(a);
			}

		}
	}

	@RequestMapping("make/room/{roomCode}")
	public void makeRoom(@PathVariable("roomCode") int roomCode) {
		try {
			QRCodeGenerator.generateQRCodeImage("late", roomCode, 350, 350, 1);
			Room r = new Room();
			r.setCode(roomCode);
			roomRepository.save(r);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("make/course/{courId}")
	public void makeCourse(@PathVariable("courId") int courId) {
		try {
			QRCodeGenerator.generateQRCodeImage("check", courId, 350, 350, 2);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
