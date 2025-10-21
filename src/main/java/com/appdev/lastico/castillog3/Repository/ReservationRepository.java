package com.appdev.lastico.castillog3.Repository;

import com.appdev.lastico.castillog3.Model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    // Find reservations by status
    List<ReservationEntity> findByReservationStatus(String reservationStatus);
    
    // Find reservations by date
    List<ReservationEntity> findByReservationDate(LocalDateTime reservationDate);
    
    // Find reservations by date range
    List<ReservationEntity> findByReservationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find reservations by reason for visit
    List<ReservationEntity> findByReasonForVisit(String reasonForVisit);
    
    // Find reservations by status and date range
    List<ReservationEntity> findByReservationStatusAndReservationDateBetween(
        String reservationStatus, LocalDateTime startDate, LocalDateTime endDate);
}
