package com.appdev.lastico.castillog3.Repository;

import com.appdev.lastico.castillog3.Model.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    // Find staff by username
    StaffEntity findByUsername(String username);
    
    // Find staff by email
    StaffEntity findByEmail(String email);
    
    // Check if staff exists by username
    boolean existsByUsername(String username);
    
    // Check if staff exists by email
    boolean existsByEmail(String email);
}
