package org.example.case_study_4.repository.attendance;

import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {


    List<Attendance> findByClassEntityAndCreateAtBetween(Classes classEntity, LocalDateTime startDateTime, LocalDateTime endDateTime);
    @Query(value = "SELECT * FROM attendance WHERE create_at LIKE :createAt AND class_id = :classId", nativeQuery = true)
    List<Attendance> findAttendanceByClassAndDate(@Param("createAt") LocalDateTime createAt, @Param("classId") Integer classId);

}
