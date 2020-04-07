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
@ToString(exclude={"department","registrations"})
@EqualsAndHashCode(exclude={"department","registrations"})
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "studentNumber")
    String studentNo;

	String name;
	int year;
	
	@ManyToOne
	@JoinColumn(name = "departmentId")
	Department department;
	
	@JsonIgnore
	@OneToMany(mappedBy="student", fetch = FetchType.LAZY)
	List<Registration> registrations;
}
