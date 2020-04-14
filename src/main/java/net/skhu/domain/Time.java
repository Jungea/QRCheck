package net.skhu.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = { "course" })
@EqualsAndHashCode(exclude = { "course" })
@Entity
public class Time {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	LocalDate startDate;
	LocalTime startTime;
	LocalTime endTime;
	int dayOfWeek;

	@ManyToOne
	@JoinColumn(name = "courId")
	Course course;

	public String getStringWeek(int dayOfWeek) {
		String[] week = { "월", "화", "수", "목", "금", "토", "일" };
		return week[dayOfWeek - 1];
	}
}
