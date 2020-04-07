package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
