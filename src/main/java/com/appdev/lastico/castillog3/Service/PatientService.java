package com.appdev.lastico.castillog3.Service;

import com.appdev.lastico.castillog3.Model.PatientEntity;
import com.appdev.lastico.castillog3.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Get all patients
    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    // Get patient by ID
    public Optional<PatientEntity> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Get patient by username
    public PatientEntity getPatientByUsername(String username) {
        return patientRepository.findByUsername(username);
    }

    // Get patient by email
    public PatientEntity getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    // Get patients by gender
    public List<PatientEntity> getPatientsByGender(String gender) {
        return patientRepository.findByGender(gender);
    }

    // Create new patient
    public PatientEntity createPatient(PatientEntity patient) {
        return patientRepository.save(patient);
    }

    // Update existing patient
    public PatientEntity updatePatient(Long id, PatientEntity patientDetails) {
        Optional<PatientEntity> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            PatientEntity patient = optionalPatient.get();
            patient.setFirstName(patientDetails.getFirstName());
            patient.setLastName(patientDetails.getLastName());
            patient.setDateOfBirth(patientDetails.getDateOfBirth());
            patient.setAge(patientDetails.getAge());
            patient.setGender(patientDetails.getGender());
            patient.setEmail(patientDetails.getEmail());
            patient.setStreet(patientDetails.getStreet());
            patient.setBarangay(patientDetails.getBarangay());
            patient.setMunicipality(patientDetails.getMunicipality());
            patient.setProvince(patientDetails.getProvince());
            patient.setUsername(patientDetails.getUsername());
            patient.setPassword(patientDetails.getPassword());
            patient.setPhoneNumber(patientDetails.getPhoneNumber());
            return patientRepository.save(patient);
        }
        return null;
    }

    // Delete patient
    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Check if username exists
    public boolean usernameExists(String username) {
        return patientRepository.existsByUsername(username);
    }

    // Check if email exists
    public boolean emailExists(String email) {
        return patientRepository.existsByEmail(email);
    }

    // Authenticate patient (login)
    public PatientEntity authenticatePatient(String username, String password) {
        PatientEntity patient = getPatientByUsername(username);
        if (patient != null && patient.getPassword().equals(password)) {
            return patient;
        }
        return null;
    }
}
