package com.appdev.lastico.castillog3.Service;

import com.appdev.lastico.castillog3.Model.DoctorEntity;
import com.appdev.lastico.castillog3.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Get all doctors
    public List<DoctorEntity> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    public Optional<DoctorEntity> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Get doctor by email
    public DoctorEntity getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    // Get doctors by medical role
    public List<DoctorEntity> getDoctorsByRole(String medicalRole) {
        return doctorRepository.findByMedicalRole(medicalRole);
    }

    // Create new doctor
    public DoctorEntity createDoctor(DoctorEntity doctor) {
        return doctorRepository.save(doctor);
    }

    // Update existing doctor
    public DoctorEntity updateDoctor(Long id, DoctorEntity doctorDetails) {
        Optional<DoctorEntity> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            DoctorEntity doctor = optionalDoctor.get();
            doctor.setFirstName(doctorDetails.getFirstName());
            doctor.setLastName(doctorDetails.getLastName());
            doctor.setMedicalRole(doctorDetails.getMedicalRole());
            doctor.setPhoneNumber(doctorDetails.getPhoneNumber());
            doctor.setEmail(doctorDetails.getEmail());
            return doctorRepository.save(doctor);
        }
        return null;
    }

    // Delete doctor
    public boolean deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Check if email exists
    public boolean emailExists(String email) {
        return doctorRepository.existsByEmail(email);
    }
}
