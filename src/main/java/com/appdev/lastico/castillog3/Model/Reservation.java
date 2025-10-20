package com.appdev.lastico.castillog3.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private Long reservationID;

	private LocalDateTime reservationDate;

	private String reservationStatus;

	private String reasonForVisit;

	public Reservation() {}

	public Long getReservationID() { return reservationID; }
	public void setReservationID(Long reservationID) { this.reservationID = reservationID; }
	public LocalDateTime getReservationDate() { return reservationDate; }
	public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }
	public String getReservationStatus() { return reservationStatus; }
	public void setReservationStatus(String reservationStatus) { this.reservationStatus = reservationStatus; }
	public String getReasonForVisit() { return reasonForVisit; }
	public void setReasonForVisit(String reasonForVisit) { this.reasonForVisit = reasonForVisit; }
}
