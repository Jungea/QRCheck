package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
	Registration findByStudent_IdAndCourse_Id(int sid, int cid);
	
	
}
