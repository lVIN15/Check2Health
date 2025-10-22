package com.appdev.lastico.castillog3.Controller;

import com.appdev.lastico.castillog3.Model.PatientEntity;
import com.appdev.lastico.castillog3.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    // Get all patients
    @GetMapping
    public ResponseEntity<List<PatientEntity>> getAllPatients() {
        List<PatientEntity> patients = patientRepository.findAll();
        return ResponseEntity.ok(patients);
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientEntity> getPatientById(@PathVariable Long id) {
        Optional<PatientEntity> patient = patientRepository.findById(id);
        return patient.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // Get patient by username
    @GetMapping("/username/{username}")
    public ResponseEntity<PatientEntity> getPatientByUsername(@PathVariable String username) {
        PatientEntity patient = patientRepository.findByUsername(username);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        }
        return ResponseEntity.notFound().build();
    }

    // Get patient by email
    @GetMapping("/email/{email}")
    public ResponseEntity<PatientEntity> getPatientByEmail(@PathVariable String email) {
        PatientEntity patient = patientRepository.findByEmail(email);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        }
        return ResponseEntity.notFound().build();
    }

    // Get patients by gender
    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<PatientEntity>> getPatientsByGender(@PathVariable String gender) {
        List<PatientEntity> patients = patientRepository.findByGender(gender);
        return ResponseEntity.ok(patients);
    }

    // Create new patient
    @PostMapping
    public ResponseEntity<PatientEntity> createPatient(@RequestBody PatientEntity patient) {
        // Check if username already exists
        if (patientRepository.existsByUsername(patient.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        // Check if email already exists
        if (patientRepository.existsByEmail(patient.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        PatientEntity createdPatient = patientRepository.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    // Update patient
    @PutMapping("/{id}")
    public ResponseEntity<PatientEntity> updatePatient(@PathVariable Long id, @RequestBody PatientEntity patientDetails) {
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
            
            PatientEntity updatedPatient = patientRepository.save(patient);
            return ResponseEntity.ok(updatedPatient);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Check if username exists
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkUsernameExists(@PathVariable String username) {
        boolean exists = patientRepository.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }

    // Check if email exists
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean exists = patientRepository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    // Patient authentication (login)
    @PostMapping("/login")
    public ResponseEntity<PatientEntity> loginPatient(@RequestBody LoginRequest loginRequest) {
        PatientEntity patient = patientRepository.findByUsername(loginRequest.getUsername());
        if (patient != null && patient.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(patient);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Inner class for login request
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
