package org.example.case_study_4.repository.attendance_status;

import org.example.case_study_4.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAttendanceStatusRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByStudentIdAndIsDeleteFalse(Integer studentId);
}
