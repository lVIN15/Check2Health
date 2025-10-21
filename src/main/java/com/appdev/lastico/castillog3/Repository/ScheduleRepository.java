package com.appdev.lastico.castillog3.Repository;

import com.appdev.lastico.castillog3.Model.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    // Find schedules by day of week
    List<ScheduleEntity> findByDayOfWeek(String dayOfWeek);
    
    // Find schedules by start time
    List<ScheduleEntity> findByStartTime(LocalTime startTime);
    
    // Find schedules by end time
    List<ScheduleEntity> findByEndTime(LocalTime endTime);
    
    // Find schedules by day of week and start time
    List<ScheduleEntity> findByDayOfWeekAndStartTime(String dayOfWeek, LocalTime startTime);
    
    // Find schedules by day of week and end time
    List<ScheduleEntity> findByDayOfWeekAndEndTime(String dayOfWeek, LocalTime endTime);
    
    // Find schedules within time range
    List<ScheduleEntity> findByStartTimeBetween(LocalTime startTime, LocalTime endTime);
}
