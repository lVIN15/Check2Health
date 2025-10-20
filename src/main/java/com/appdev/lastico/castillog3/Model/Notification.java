package com.appdev.lastico.castillog3.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id")
	private Long notificationID;

	private String message;

	public Notification() {}

	public Long getNotificationID() { return notificationID; }
	public void setNotificationID(Long notificationID) { this.notificationID = notificationID; }
	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }
}
