package net.skhu.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.skhu.domain.Attendance;
import net.skhu.domain.Course;
import net.skhu.domain.Professor;
import net.skhu.domain.Registration;
import net.skhu.domain.Student;
import net.skhu.domain.Time;
import net.skhu.etc.Att;
import net.skhu.etc.Message;
import net.skhu.repository.AttendanceRepository;
import net.skhu.repository.CourseRepository;
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
	@Autowired
	CourseRepository courseRepository;

	// [앱]로그인
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

	// [앱]출석 테이블
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

	// [앱]출석체크
	@RequestMapping("check/{courId}/{stuId}")
	public Message checkQR(@PathVariable("courId") int courId, @PathVariable("stuId") int stuId) {
		System.out.println(courId + " " + stuId);
		LocalDateTime nowDateTime = LocalDateTime.now();
//		LocalDateTime nowDateTime = LocalDateTime.of(2020, 03, 16, 12, 00);
		LocalTime now = nowDateTime.toLocalTime();

		Course course = courseRepository.findById(courId).get();
		List<Time> timeList = course.getTimes();
		int timeListIndex = -1; // 몇번 째 시간과 일치하는 지 저장

		// 수강 중인 과목인가?
		Registration registration = registrationRepository.findByStudent_IdAndCourse_Id(stuId, courId);
		if (registration == null) {
			System.out.println("수강 중인 강의가 아닙니다.");
			return new Message("수강 중인 강의가 아닙니다.");

		} else { // 출석 중인가?
			if (course.getShow() == false) {
				System.out.println("출석 중이 아닙니다.");
				return new Message("출석 중이 아닙니다.");

			} else { // 요일과 시간이 일치하는가?
				int nowDayOfWeek = nowDateTime.getDayOfWeek().getValue();

				for (int i = 0; i < timeList.size(); ++i) {
					if (timeList.get(i).getDayOfWeek() == nowDayOfWeek) {

						if (timeList.get(i).getStartTime().isBefore(now)
								&& now.isBefore(timeList.get(i).getEndTime())) {
							timeListIndex = i;
							break;
						}

					}
				}

				if (timeListIndex == -1) { // 요일과 시간이 일치하지 않음.
					System.out.println("강의 시간이 아닙니다.");
					return new Message("강의 시간이 아닙니다.");

				} else { // 몇주가 지났는지
					int x = timeList.get(timeListIndex).getStartDate().until(nowDateTime.toLocalDate()).getDays() / 7;
					int attedanceNum; // 차시

					if (timeList.size() == 1) // 분할 강의가 아닐 때
						attedanceNum = x + 1;
					else
						attedanceNum = 2 * x + timeListIndex + 1;

					System.out.println(
							attendanceRepository.findByRegistration_IdAndNum(registration.getId(), attedanceNum));
					Attendance a = attendanceRepository.findByRegistration_IdAndNum(registration.getId(), attedanceNum);
					int state = a.getState();
					if (state == 1) {
						System.out.println("이미 출석 하였습니다.");
						return new Message("이미 출석 하였습니다.");
					} else {
						a.setState(1);
						System.out.println(a);
//						attendanceRepository.save(a);
						return new Message("출석하였습니다.");
					}
				}

			}
		}

	}

	// [앱]지각체크
	@RequestMapping("late/{roomCode}/{stuId}")
	public Message lateQR(@PathVariable("roomCode") int roomCode, @PathVariable("stuId") int stuId) {
		System.out.println(roomCode + " " + stuId);

		return new Message("지각성공");
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
		Registration registration = registrationRepository.findByStudent_IdAndCourse_Id(sid, cid);
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
