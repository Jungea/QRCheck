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
@ToString(exclude = { "course", "registrations" })
@EqualsAndHashCode(exclude = { "course", "registrations" })
@Entity
public class Division {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String code;
	String deptName;

	@ManyToOne
	@JoinColumn(name = "courId")
	Course course;

	@JsonIgnore
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	List<Registration> registrations;
}
