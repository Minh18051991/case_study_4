package com.example.demo.repository.attendance;

import com.example.demo.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByCreateAtBetweenAndIsDeleteFalse(LocalDateTime start, LocalDateTime end);
}
