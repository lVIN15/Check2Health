package com.appdev.lastico.castillog3.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class PatientEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private Long patientID;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	private LocalDate dateOfBirth;

	private Integer age;

	private String gender;

	private String email;

	private String street;

	private String barangay;

	private String municipality;

	private String province;

	@Column(unique = true)
	private String username;

	private String password;

	private String phoneNumber;

	public PatientEntity() {}

	public Long getPatientID() { return patientID; }
	public void setPatientID(Long patientID) { this.patientID = patientID; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public LocalDate getDateOfBirth() { return dateOfBirth; }
	public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
	public Integer getAge() { return age; }
	public void setAge(Integer age) { this.age = age; }
	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getStreet() { return street; }
	public void setStreet(String street) { this.street = street; }
	public String getBarangay() { return barangay; }
	public void setBarangay(String barangay) { this.barangay = barangay; }
	public String getMunicipality() { return municipality; }
	public void setMunicipality(String municipality) { this.municipality = municipality; }
	public String getProvince() { return province; }
	public void setProvince(String province) { this.province = province; }
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getPhoneNumber() { return phoneNumber; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
