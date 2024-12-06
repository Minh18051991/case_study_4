package org.example.case_study_4.service.attendance;

import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IAttendanceService {
    List<Attendance> getAttendanceByClassAndDate(Classes classes, LocalDate date);

    void deleteAttendance(Classes classes, Student student, LocalDate parsedDate);

    void updateAttendance(Attendance attendance,String status);

    void saveAttendance(Classes classes, LocalDate parsedDate, List<Integer> presentStudents, Map<Integer, String> status, Map<Integer, String> note);

    Attendance getAttendanceById(Integer attendanceId);


    List<Attendance> getAttendanceByClassAndDate(Integer classId);



    List<Attendance> findAttendanceByClassAndDate(LocalDateTime createAt, Integer classesId);
}
