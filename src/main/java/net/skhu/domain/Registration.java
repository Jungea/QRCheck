package net.skhu.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude={"student","course"})
@EqualsAndHashCode(exclude={"student","course"})
@Entity
@Table(name = "register")
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	Integer grade;
	Date createDate;
	int state;

	@ManyToOne
	@JoinColumn(name = "studentId")
	Student student;

	@ManyToOne
	@JoinColumn(name = "courseId")
	Course course;
}
