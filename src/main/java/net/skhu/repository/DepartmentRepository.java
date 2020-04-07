package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
