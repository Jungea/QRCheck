package net.skhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
	List<Registration> findByStudent_IdAndCourse_Id(int sid, int cid);
	
	
}
