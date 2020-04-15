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
@ToString(exclude = { "courses" })
@EqualsAndHashCode(exclude = { "courses" })
@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	int code;
	String name;

	@JsonIgnore
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	List<Course> courses;
}
