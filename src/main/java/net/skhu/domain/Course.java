package net.skhu.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude= {"department", "professor", "registrations"})
@EqualsAndHashCode(exclude = {"department", "professor",  "registrations"})
@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int unit;
	Date startDate;
	
    @Column(name = "courseName")
    String name;

	@ManyToOne
	@JoinColumn(name="departmentId")
	Department department;
	
	@ManyToOne
	@JoinColumn(name="professorId")
	Professor professor;
	
	@JsonIgnore
	@OneToMany(mappedBy="course", fetch = FetchType.LAZY)
	List<Registration> registrations;
	
}