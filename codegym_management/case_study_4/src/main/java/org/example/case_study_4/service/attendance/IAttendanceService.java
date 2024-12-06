package org.example.case_study_4.service.attendance;

import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.model.Classes;

import java.time.LocalDate;
import java.util.List;

public interface IAttendanceService {

    List<Attendance> createAttendanceByClassAndDate(Classes classEntity, LocalDate date);

    List<Attendance> viewAttendanceByClassAndDate(Integer classId, LocalDate date);

    Attendance updateAttendance(Attendance attendance);

    void deleteAttendance(Integer attendanceId);
}