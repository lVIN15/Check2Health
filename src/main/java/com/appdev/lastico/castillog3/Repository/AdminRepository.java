package com.appdev.lastico.castillog3.Repository;

import com.appdev.lastico.castillog3.Model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    // Find admin by username
    AdminEntity findByUsername(String username);
    
    // Find admin by email
    AdminEntity findByEmail(String email);
    
    // Check if admin exists by username
    boolean existsByUsername(String username);
    
    // Check if admin exists by email
    boolean existsByEmail(String email);
}
