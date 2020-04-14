package net.skhu.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = {})
@EqualsAndHashCode(exclude = {})
@Entity
public class Semester {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	int year;
	int sem;
	LocalDate startDate;
	LocalDate endDate;
	int weekNum;

	@JsonIgnore
	@OneToOne(mappedBy="semester")
	Admin admin;

	@JsonIgnore
	@OneToMany(mappedBy="semester", fetch = FetchType.LAZY)
	List<Course> courses;

}
