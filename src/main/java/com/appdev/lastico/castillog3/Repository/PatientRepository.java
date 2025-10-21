package com.appdev.lastico.castillog3.Repository;

import com.appdev.lastico.castillog3.Model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    // Find patient by username
    PatientEntity findByUsername(String username);
    
    // Find patient by email
    PatientEntity findByEmail(String email);
    
    // Find patients by first name
    List<PatientEntity> findByFirstName(String firstName);
    
    // Find patients by last name
    List<PatientEntity> findByLastName(String lastName);
    
    // Find patients by first name and last name
    List<PatientEntity> findByFirstNameAndLastName(String firstName, String lastName);
    
    // Find patients by gender
    List<PatientEntity> findByGender(String gender);
    
    // Find patients by age
    List<PatientEntity> findByAge(Integer age);
    
    // Find patients by date of birth
    List<PatientEntity> findByDateOfBirth(LocalDate dateOfBirth);
    
    // Find patients by municipality
    List<PatientEntity> findByMunicipality(String municipality);
    
    // Find patients by province
    List<PatientEntity> findByProvince(String province);
    
    // Check if patient exists by username
    boolean existsByUsername(String username);
    
    // Check if patient exists by email
    boolean existsByEmail(String email);
}
