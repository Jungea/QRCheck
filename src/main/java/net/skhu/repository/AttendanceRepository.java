package net.skhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.skhu.domain.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	List<Attendance> findByNumAndStateNotAndRegistration_Course_id(int num, int state, int courId);
	
	List<Attendance> findByNumAndStateAndRegistration_Course_id(int num, int state, int courId);

	// [앱] 출석 테이블
	// 강의 신청 별 출석 상태들의 개수
	@Query("SELECT a.state, COUNT(a) FROM Attendance a WHERE a.registration.id = ?1 AND a.state != 0 GROUP BY a.state")
	List<Integer[]> findAttendanceCountOfStateByRegistration_Id(int registrationId);

	Attendance findByRegistration_IdAndNum(int regiId, int num);

//	Attendance findByRegistration_Course_IdAndNums(int courId, int num);

	// 해당 강의의 n차시 출석 상태 리스트
	List<Attendance> findByRegistration_Course_IdAndNum(int courId, int num);

	// 해당 강의의 n차시 미입력 출석 정보 리스트
	List<Attendance> findByRegistration_Course_IdAndNumAndState(int courId, int num, int state);

}
