package net.skhu.domain;

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
@ToString(exclude = { "semester", "professor", "room", "registrations", "divisions", "times" })
@EqualsAndHashCode(exclude = { "semester", "professor", "room", "registrations", "divisions", "times" })
@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String name;
	Boolean split;
	int joint;
	@Column(name ="see")
	Boolean show;

	@ManyToOne
	@JoinColumn(name = "semId")
	Semester semester;

	@ManyToOne
	@JoinColumn(name = "profId")
	Professor professor;

	@ManyToOne
	@JoinColumn(name = "roomId")
	Room room;

	@JsonIgnore
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	List<Registration> registrations;

	@JsonIgnore
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	List<Division> divisions;

	@JsonIgnore
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	List<Time> times;

}