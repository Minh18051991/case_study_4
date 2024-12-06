package com.example.demo.controller.attendance;

import com.example.demo.dto.AttendanceRequest;
import com.example.demo.model.Attendance;
import com.example.demo.service.attendance.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Hiển thị trang điểm danh
    @GetMapping("/")
    public String showAttendancePage(Model model) {
        model.addAttribute("attendanceRequest", new AttendanceRequest());  // Tạo mới đối tượng AttendanceRequest
        return "index";  // Trả về trang index
    }

    // Xử lý điểm danh học viên
    @PostMapping("/markAttendance")
    public String markAttendance(@ModelAttribute AttendanceRequest request, Model model) {
        Attendance attendance = attendanceService.markAttendance(request);  // Điểm danh học viên
        model.addAttribute("attendanceRequest", new AttendanceRequest());  // Reset form
        return "index";  // Quay lại trang index
    }

    // Cập nhật điểm danh
    @PostMapping("/updateAttendance/{attendanceId}")
    public String updateAttendance(@PathVariable Integer attendanceId, @ModelAttribute AttendanceRequest request, Model model) {
        Attendance updatedAttendance = attendanceService.updateAttendance(attendanceId, request);  // Cập nhật điểm danh
        model.addAttribute("attendanceRequest", new AttendanceRequest());  // Reset form
        return "index";  // Quay lại trang index
    }

    // Xoá điểm danh (soft delete)
    @PostMapping("/deleteAttendance/{attendanceId}")
    public String deleteAttendance(@PathVariable Integer attendanceId, Model model) {
        attendanceService.deleteAttendance(attendanceId);  // Xoá điểm danh
        return "index";  // Quay lại trang index
    }

    // Lấy điểm danh theo khoảng thời gian
    @GetMapping("/getAttendanceByDate")
    public String getAttendanceByDate(@RequestParam("start") LocalDateTime start,
                                      @RequestParam("end") LocalDateTime end,
                                      Model model) {
        List<Attendance> attendances = attendanceService.getAttendanceByDate(start, end);  // Lấy điểm danh theo ngày
        model.addAttribute("attendances", attendances);  // Đưa danh sách điểm danh vào model
        return "attendanceList";  // Trả về trang xem điểm danh
    }
}
