package org.example.case_study_4.service.attendance;

import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.model.Classes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IAttendanceService {
    void saveAttendance(Classes classes, LocalDate date, List<Integer> presentStudents,Map<Integer, String> status);
    void updateAttendance(Classes classes, LocalDate date, Integer studentId, Attendance.AttendanceStatus status);
    List<Attendance> getAttendanceByClassAndDate(Classes classes, LocalDate date);

}
