package com.appdev.lastico.castillog3.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalTime;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private Long scheduleID;

	private String dayOfWeek;

	private LocalTime startTime;

	private LocalTime endTime;

	public ScheduleEntity() {}

	public Long getScheduleID() { return scheduleID; }
	public void setScheduleID(Long scheduleID) { this.scheduleID = scheduleID; }
	public String getDayOfWeek() { return dayOfWeek; }
	public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
	public LocalTime getStartTime() { return startTime; }
	public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
	public LocalTime getEndTime() { return endTime; }
	public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}
