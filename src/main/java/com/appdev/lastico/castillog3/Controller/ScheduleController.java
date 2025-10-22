package com.appdev.lastico.castillog3.Controller;

import com.appdev.lastico.castillog3.Model.ScheduleEntity;
import com.appdev.lastico.castillog3.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules")
@CrossOrigin(origins = "*")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // Get all schedules
    @GetMapping
    public ResponseEntity<List<ScheduleEntity>> getAllSchedules() {
        List<ScheduleEntity> schedules = scheduleRepository.findAll();
        return ResponseEntity.ok(schedules);
    }

    // Get schedule by ID
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable Long id) {
        Optional<ScheduleEntity> schedule = scheduleRepository.findById(id);
        return schedule.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Get schedules by day of week
    @GetMapping("/day/{dayOfWeek}")
    public ResponseEntity<List<ScheduleEntity>> getSchedulesByDay(@PathVariable String dayOfWeek) {
        List<ScheduleEntity> schedules = scheduleRepository.findByDayOfWeek(dayOfWeek);
        return ResponseEntity.ok(schedules);
    }

    // Create new schedule
    @PostMapping
    public ResponseEntity<ScheduleEntity> createSchedule(@RequestBody ScheduleEntity schedule) {
        ScheduleEntity createdSchedule = scheduleRepository.save(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
    }

    // Update schedule
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleEntity> updateSchedule(@PathVariable Long id, @RequestBody ScheduleEntity scheduleDetails) {
        Optional<ScheduleEntity> optionalSchedule = scheduleRepository.findById(id);
        if (optionalSchedule.isPresent()) {
            ScheduleEntity schedule = optionalSchedule.get();
            schedule.setDayOfWeek(scheduleDetails.getDayOfWeek());
            schedule.setStartTime(scheduleDetails.getStartTime());
            schedule.setEndTime(scheduleDetails.getEndTime());
            
            ScheduleEntity updatedSchedule = scheduleRepository.save(schedule);
            return ResponseEntity.ok(updatedSchedule);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete schedule
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
