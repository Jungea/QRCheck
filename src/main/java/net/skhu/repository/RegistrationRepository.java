package net.skhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	Registration findByStudent_IdAndCourse_Id(int sid, int cid);

	// 이번학기 수강 신청
	Registration findByStudent_IdAndCourse_IdAndCourse_Semester_Id(int stuId, int courId, int semId);

	List<Registration> findByStudent_IdAndCourse_Semester_Id(int stuId, int semId);
//	List<Registration> findByCourse_Id(int id);

}
