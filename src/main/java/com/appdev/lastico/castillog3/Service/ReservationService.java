package com.appdev.lastico.castillog3.Service;

import com.appdev.lastico.castillog3.Model.ReservationEntity;
import com.appdev.lastico.castillog3.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // Get all reservations
    public List<ReservationEntity> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Get reservation by ID
    public Optional<ReservationEntity> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    // Get reservations by status
    public List<ReservationEntity> getReservationsByStatus(String status) {
        return reservationRepository.findByReservationStatus(status);
    }

    // Get reservations by date range
    public List<ReservationEntity> getReservationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return reservationRepository.findByReservationDateBetween(startDate, endDate);
    }

    // Create new reservation
    public ReservationEntity createReservation(ReservationEntity reservation) {
        return reservationRepository.save(reservation);
    }

    // Update existing reservation
    public ReservationEntity updateReservation(Long id, ReservationEntity reservationDetails) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            ReservationEntity reservation = optionalReservation.get();
            reservation.setReservationDate(reservationDetails.getReservationDate());
            reservation.setReservationStatus(reservationDetails.getReservationStatus());
            reservation.setReasonForVisit(reservationDetails.getReasonForVisit());
            return reservationRepository.save(reservation);
        }
        return null;
    }

    // Update reservation status only
    public ReservationEntity updateReservationStatus(Long id, String status) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            ReservationEntity reservation = optionalReservation.get();
            reservation.setReservationStatus(status);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    // Delete reservation
    public boolean deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
