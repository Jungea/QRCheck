package net.skhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.skhu.domain.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	List<Attendance> findByNumAndStateNotAndRegistration_Course_id(int num, int state, int courId);

	@Query("SELECT a.state, COUNT(a) FROM Attendance a WHERE a.registration.id = ?1 AND a.state != 0 GROUP BY a.state")
	List<Integer[]> findAttendanceCountOfStateByRegistration_Id(int registrationId);

	Attendance findByRegistration_IdAndNum(int regiId, int num);
	
//	Attendance findByRegistration_Course_IdAndNums(int courId, int num);
	
	List<Attendance> findByRegistration_Course_IdAndNum(int courId, int num);


}
