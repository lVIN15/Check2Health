package com.appdev.lastico.castillog3.Service;

import com.appdev.lastico.castillog3.Model.ScheduleEntity;
import com.appdev.lastico.castillog3.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // Get all schedules
    public List<ScheduleEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    // Get schedule by ID
    public Optional<ScheduleEntity> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    // Get schedules by day of week
    public List<ScheduleEntity> getSchedulesByDay(String dayOfWeek) {
        return scheduleRepository.findByDayOfWeek(dayOfWeek);
    }

    // Create new schedule
    public ScheduleEntity createSchedule(ScheduleEntity schedule) {
        return scheduleRepository.save(schedule);
    }

    // Update existing schedule
    public ScheduleEntity updateSchedule(Long id, ScheduleEntity scheduleDetails) {
        Optional<ScheduleEntity> optionalSchedule = scheduleRepository.findById(id);
        if (optionalSchedule.isPresent()) {
            ScheduleEntity schedule = optionalSchedule.get();
            schedule.setDayOfWeek(scheduleDetails.getDayOfWeek());
            schedule.setStartTime(scheduleDetails.getStartTime());
            schedule.setEndTime(scheduleDetails.getEndTime());
            return scheduleRepository.save(schedule);
        }
        return null;
    }

    // Delete schedule
    public boolean deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
