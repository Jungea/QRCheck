package net.skhu.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude={"courses"})
@EqualsAndHashCode(exclude={"courses"})
@Entity
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String profNum;
	String name;
	String password;
	String deptName;

	@JsonIgnore
	@OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
	List<Course> courses;
}
