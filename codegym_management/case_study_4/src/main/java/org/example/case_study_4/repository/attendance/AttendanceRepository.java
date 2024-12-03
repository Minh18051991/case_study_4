package org.example.case_study_4.repository.attendance;

import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByClassEntityAndCreateAtBetween(Classes classes, LocalDateTime localDateTime, LocalDateTime localDateTime1);

    Attendance findByClassEntityAndCreateAtAndStudent(Classes classObj, LocalDateTime localDateTime, Student student);

    void deleteByClassEntityAndCreateAtBetween(Classes classObj, LocalDateTime localDateTime, LocalDateTime localDateTime1);
}
