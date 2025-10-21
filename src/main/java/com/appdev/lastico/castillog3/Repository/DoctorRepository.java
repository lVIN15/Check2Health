package com.appdev.lastico.castillog3.Repository;

import com.appdev.lastico.castillog3.Model.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    // Find doctor by email
    DoctorEntity findByEmail(String email);
    
    // Find doctors by medical role
    List<DoctorEntity> findByMedicalRole(String medicalRole);
    
    // Find doctors by first name
    List<DoctorEntity> findByFirstName(String firstName);
    
    // Find doctors by last name
    List<DoctorEntity> findByLastName(String lastName);
    
    // Find doctors by first name and last name
    List<DoctorEntity> findByFirstNameAndLastName(String firstName, String lastName);
    
    // Check if doctor exists by email
    boolean existsByEmail(String email);
}
