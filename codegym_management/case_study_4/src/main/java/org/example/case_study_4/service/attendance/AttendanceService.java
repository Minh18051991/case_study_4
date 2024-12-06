package org.example.case_study_4.service.attendance;

import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.model.Classes;
import org.example.case_study_4.repository.attendance.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService implements IAttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public List<Attendance> createAttendanceByClassAndDate(Classes classEntity, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return attendanceRepository.findByClassEntityAndCreateAtBetween(classEntity, startOfDay, endOfDay);
    }

    @Override
    public List<Attendance> viewAttendanceByClassAndDate(Integer classId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return attendanceRepository.findAttendanceByClassAndDate(startOfDay, classId);
    }

    @Override
    public Attendance updateAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public void deleteAttendance(Integer attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }
}