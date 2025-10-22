package com.appdev.lastico.castillog3.Controller;

import com.appdev.lastico.castillog3.Model.DoctorEntity;
import com.appdev.lastico.castillog3.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    // Get all doctors
    @GetMapping
    public ResponseEntity<List<DoctorEntity>> getAllDoctors() {
        List<DoctorEntity> doctors = doctorRepository.findAll();
        return ResponseEntity.ok(doctors);
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<DoctorEntity> getDoctorById(@PathVariable Long id) {
        Optional<DoctorEntity> doctor = doctorRepository.findById(id);
        return doctor.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Get doctors by medical role
    @GetMapping("/role/{medicalRole}")
    public ResponseEntity<List<DoctorEntity>> getDoctorsByRole(@PathVariable String medicalRole) {
        List<DoctorEntity> doctors = doctorRepository.findByMedicalRole(medicalRole);
        return ResponseEntity.ok(doctors);
    }

    // Get doctor by email
    @GetMapping("/email/{email}")
    public ResponseEntity<DoctorEntity> getDoctorByEmail(@PathVariable String email) {
        DoctorEntity doctor = doctorRepository.findByEmail(email);
        if (doctor != null) {
            return ResponseEntity.ok(doctor);
        }
        return ResponseEntity.notFound().build();
    }

    // Create new doctor
    @PostMapping
    public ResponseEntity<DoctorEntity> createDoctor(@RequestBody DoctorEntity doctor) {
        // Check if email already exists
        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        DoctorEntity createdDoctor = doctorRepository.save(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
    }

    // Update doctor
    @PutMapping("/{id}")
    public ResponseEntity<DoctorEntity> updateDoctor(@PathVariable Long id, @RequestBody DoctorEntity doctorDetails) {
        Optional<DoctorEntity> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            DoctorEntity doctor = optionalDoctor.get();
            doctor.setFirstName(doctorDetails.getFirstName());
            doctor.setLastName(doctorDetails.getLastName());
            doctor.setMedicalRole(doctorDetails.getMedicalRole());
            doctor.setPhoneNumber(doctorDetails.getPhoneNumber());
            doctor.setEmail(doctorDetails.getEmail());
            
            DoctorEntity updatedDoctor = doctorRepository.save(doctor);
            return ResponseEntity.ok(updatedDoctor);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Check if email exists
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean exists = doctorRepository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
}
