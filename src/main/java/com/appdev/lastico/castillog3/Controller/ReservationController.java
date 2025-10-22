package com.appdev.lastico.castillog3.Controller;

import com.appdev.lastico.castillog3.Model.ReservationEntity;
import com.appdev.lastico.castillog3.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    // Get all reservations
    @GetMapping
    public ResponseEntity<List<ReservationEntity>> getAllReservations() {
        List<ReservationEntity> reservations = reservationRepository.findAll();
        return ResponseEntity.ok(reservations);
    }

    // Get reservation by ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservationEntity> getReservationById(@PathVariable Long id) {
        Optional<ReservationEntity> reservation = reservationRepository.findById(id);
        return reservation.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    // Get reservations by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReservationEntity>> getReservationsByStatus(@PathVariable String status) {
        List<ReservationEntity> reservations = reservationRepository.findByReservationStatus(status);
        return ResponseEntity.ok(reservations);
    }

    // Get reservations by date range
    @GetMapping("/date-range")
    public ResponseEntity<List<ReservationEntity>> getReservationsByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<ReservationEntity> reservations = reservationRepository.findByReservationDateBetween(startDate, endDate);
        return ResponseEntity.ok(reservations);
    }

    // Create new reservation
    @PostMapping
    public ResponseEntity<ReservationEntity> createReservation(@RequestBody ReservationEntity reservation) {
        ReservationEntity createdReservation = reservationRepository.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    // Update reservation
    @PutMapping("/{id}")
    public ResponseEntity<ReservationEntity> updateReservation(@PathVariable Long id, @RequestBody ReservationEntity reservationDetails) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            ReservationEntity reservation = optionalReservation.get();
            reservation.setReservationDate(reservationDetails.getReservationDate());
            reservation.setReservationStatus(reservationDetails.getReservationStatus());
            reservation.setReasonForVisit(reservationDetails.getReasonForVisit());
            
            ReservationEntity updatedReservation = reservationRepository.save(reservation);
            return ResponseEntity.ok(updatedReservation);
        }
        return ResponseEntity.notFound().build();
    }

    // Update reservation status
    @PatchMapping("/{id}/status")
    public ResponseEntity<ReservationEntity> updateReservationStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest statusUpdate) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            ReservationEntity reservation = optionalReservation.get();
            reservation.setReservationStatus(statusUpdate.getStatus());
            
            ReservationEntity updatedReservation = reservationRepository.save(reservation);
            return ResponseEntity.ok(updatedReservation);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete reservation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Inner class for status update request
    public static class StatusUpdateRequest {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
