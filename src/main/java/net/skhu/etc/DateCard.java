package net.skhu.etc;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class DateCard {
	int attNum;
	LocalDate date;
	LocalTime startTime;
	LocalTime endTime;
	int checkNum;
	int totalNum;
	int rightNum;
	
	public String getStringWeek(int dayOfWeek) {
		String[] week = { "월", "화", "수", "목", "금", "토", "일" };
		return week[dayOfWeek - 1];
	}
}
