package com.appdev.lastico.castillog3.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor")
public class DoctorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private Long doctorID;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	private String medicalRole;

	private String phoneNumber;

	private String email;

	public DoctorEntity() {}

	public Long getDoctorID() { return doctorID; }
	public void setDoctorID(Long doctorID) { this.doctorID = doctorID; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getMedicalRole() { return medicalRole; }
	public void setMedicalRole(String medicalRole) { this.medicalRole = medicalRole; }
	public String getPhoneNumber() { return phoneNumber; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
}
