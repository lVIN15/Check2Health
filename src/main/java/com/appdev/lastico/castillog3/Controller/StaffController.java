package com.appdev.lastico.castillog3.Controller;

import com.appdev.lastico.castillog3.Model.StaffEntity;
import com.appdev.lastico.castillog3.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    private StaffService staffService;

    // Get all staff members
    @GetMapping
    public ResponseEntity<List<StaffEntity>> getAllStaff() {
        List<StaffEntity> staff = staffService.getAllStaff();
        return ResponseEntity.ok(staff);
    }

    // Get staff by ID
    @GetMapping("/{id}")
    public ResponseEntity<StaffEntity> getStaffById(@PathVariable Long id) {
        Optional<StaffEntity> staff = staffService.getStaffById(id);
        return staff.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Get staff by username
    @GetMapping("/username/{username}")
    public ResponseEntity<StaffEntity> getStaffByUsername(@PathVariable String username) {
        Optional<StaffEntity> staff = staffService.getStaffByUsername(username);
        return staff.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Get staff by email
    @GetMapping("/email/{email}")
    public ResponseEntity<StaffEntity> getStaffByEmail(@PathVariable String email) {
        Optional<StaffEntity> staff = staffService.getStaffByEmail(email);
        return staff.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Create new staff member
    @PostMapping
    public ResponseEntity<StaffEntity> createStaff(@RequestBody StaffEntity staff) {
        // Check if username already exists
        if (staffService.usernameExists(staff.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        // Check if email already exists
        if (staffService.emailExists(staff.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        StaffEntity createdStaff = staffService.createStaff(staff);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStaff);
    }

    // Update staff member
    @PutMapping("/{id}")
    public ResponseEntity<StaffEntity> updateStaff(@PathVariable Long id, @RequestBody StaffEntity staffDetails) {
        StaffEntity updatedStaff = staffService.updateStaff(id, staffDetails);
        if (updatedStaff != null) {
            return ResponseEntity.ok(updatedStaff);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete staff member
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        boolean deleted = staffService.deleteStaff(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Check if username exists
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkUsernameExists(@PathVariable String username) {
        boolean exists = staffService.usernameExists(username);
        return ResponseEntity.ok(exists);
    }

    // Check if email exists
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean exists = staffService.emailExists(email);
        return ResponseEntity.ok(exists);
    }

    // Staff authentication (login)
    @PostMapping("/login")
    public ResponseEntity<StaffEntity> loginStaff(@RequestBody LoginRequest loginRequest) {
        Optional<StaffEntity> staff = staffService.authenticateStaff(loginRequest.getUsername(), loginRequest.getPassword());
        if (staff.isPresent()) {
            return ResponseEntity.ok(staff.get());
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
