package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Admin findByAdminNumAndPassword(String adminNum, String password);
}
