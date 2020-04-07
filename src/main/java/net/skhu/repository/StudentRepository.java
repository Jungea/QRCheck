package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
