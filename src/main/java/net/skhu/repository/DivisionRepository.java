package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Division;

public interface DivisionRepository extends JpaRepository<Division, Integer> {
}
