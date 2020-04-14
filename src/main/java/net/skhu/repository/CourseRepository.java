package net.skhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
