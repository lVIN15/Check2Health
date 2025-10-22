package com.appdev.lastico.castillog3.Service;

import com.appdev.lastico.castillog3.Model.StaffEntity;
import com.appdev.lastico.castillog3.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    // Get all staff members
    public List<StaffEntity> getAllStaff() {
        return staffRepository.findAll();
    }

    // Get staff by ID
    public Optional<StaffEntity> getStaffById(Long id) {
        return staffRepository.findById(id);
    }

    // Get staff by username
    public Optional<StaffEntity> getStaffByUsername(String username) {
        return Optional.ofNullable(staffRepository.findByUsername(username));
    }

    // Get staff by email
    public Optional<StaffEntity> getStaffByEmail(String email) {
        return Optional.ofNullable(staffRepository.findByEmail(email));
    }

    // Create new staff member
    public StaffEntity createStaff(StaffEntity staff) {
        return staffRepository.save(staff);
    }

    // Update existing staff member
    public StaffEntity updateStaff(Long id, StaffEntity staffDetails) {
        Optional<StaffEntity> optionalStaff = staffRepository.findById(id);
        if (optionalStaff.isPresent()) {
            StaffEntity staff = optionalStaff.get();
            staff.setFirstName(staffDetails.getFirstName());
            staff.setLastName(staffDetails.getLastName());
            staff.setPhoneNumber(staffDetails.getPhoneNumber());
            staff.setEmail(staffDetails.getEmail());
            staff.setUsername(staffDetails.getUsername());
            staff.setPassword(staffDetails.getPassword());
            return staffRepository.save(staff);
        }
        return null;
    }

    // Delete staff member
    public boolean deleteStaff(Long id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Check if username exists
    public boolean usernameExists(String username) {
        return staffRepository.existsByUsername(username);
    }

    // Check if email exists
    public boolean emailExists(String email) {
        return staffRepository.existsByEmail(email);
    }

    // Authenticate staff (login)
    public Optional<StaffEntity> authenticateStaff(String username, String password) {
        Optional<StaffEntity> staff = getStaffByUsername(username);
        if (staff.isPresent() && staff.get().getPassword().equals(password)) {
            return staff;
        }
        return Optional.empty();
    }
}
