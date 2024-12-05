package org.example.case_study_4.service.attendance_status;

import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.repository.attendance_status.IAttendanceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceStatusService implements IAttendanceStatusService {
    @Autowired
    private IAttendanceStatusRepository attendanceStatusRepository;
    @Override
    public List<Attendance> findByStudentIdAndIsDeleteFalse(Integer studentId) {
        return attendanceStatusRepository.findByStudentIdAndIsDeleteFalse(studentId);
    }
}
