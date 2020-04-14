package net.skhu.domain;

import java.util.List;

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
@ToString(exclude = { "student", "course", "division", "attendances" })
@EqualsAndHashCode(exclude = { "student", "course", "division", "attendances" })
@Entity
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@ManyToOne
	@JoinColumn(name = "stuId")
	Student student;

	@ManyToOne
	@JoinColumn(name = "courId")
	Course course;

	@ManyToOne
	@JoinColumn(name = "divId")
	Division division;

	@JsonIgnore
	@OneToMany(mappedBy = "registration", fetch = FetchType.LAZY)
	List<Attendance> attendances;
}
