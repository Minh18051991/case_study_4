package com.example.demo.service.attendance;

import com.example.demo.dto.AttendanceRequest;
import com.example.demo.model.Attendance;


import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceService {

    // Điểm danh cho học viên
    Attendance markAttendance(AttendanceRequest request);

    // Cập nhật điểm danh
    Attendance updateAttendance(Integer attendanceId, AttendanceRequest request);

    // Xóa điểm danh (soft delete)
    void deleteAttendance(Integer attendanceId);

    // Lấy tất cả điểm danh theo khoảng thời gian
    List<Attendance> getAttendanceByDate(LocalDateTime start, LocalDateTime end);
}
