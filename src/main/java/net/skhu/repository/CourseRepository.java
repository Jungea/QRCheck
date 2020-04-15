package net.skhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.skhu.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByRoom_Code(int roomCode);

	@Query("SELECT c FROM Course c JOIN c.times t WHERE c.room.code = ?1 AND t.dayOfWeek = ?2 AND c.semester.id= ?3")
	List<Course> findCourseByRoomCodeAndDayOfWeekAndSemesterId(int roomCode, int dayOfWeek, int semId);

	List<Course> findByProfessor_IdAndSemester_Id(int profId, int semId);

}
