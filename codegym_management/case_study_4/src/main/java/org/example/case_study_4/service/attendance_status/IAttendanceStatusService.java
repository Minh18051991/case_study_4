package org.example.case_study_4.service.attendance_status;

import org.example.case_study_4.model.Attendance;

import java.util.List;

public interface IAttendanceStatusService {
    List<Attendance> findByStudentIdAndIsDeleteFalse(Integer studentId);
}
