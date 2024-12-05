package org.example.case_study_4.controller.activity;


import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.service.attendance.IAttendanceService;
import org.example.case_study_4.service.classes.IClassService;
import org.example.case_study_4.service.student.IStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
public class ActivityController {

}
//package org.example.case_study_4.controller.attendance;
//
//import org.example.case_study_4.model.Attendance;
//import org.example.case_study_4.model.Classes;
//import org.example.case_study_4.model.Student;
//import org.example.case_study_4.service.attendance.IAttendanceService;
//import org.example.case_study_4.service.classes.IClassService;
//import org.example.case_study_4.service.student.IStudent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//        import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/employee/class/attendance")
//public class AttendanceController {
//
//    private static final Logger logger = LoggerFactory.getLogger(org.example.case_study_4.controller.attendance.AttendanceController.class);
//
//    @Autowired
//    private IAttendanceService attendanceService;
//
//    @Autowired
//    private IClassService classService;
//
//    @Autowired
//    private IStudent studentRepository;
//
//    private LocalDate getDefaultDate(String dateStr) {
//        if (dateStr == null || dateStr.isEmpty()) {
//            return LocalDate.now();  // Mặc định là ngày hiện tại nếu không có ngày
//        }
//
//        if (dateStr.contains("T")) {
//            dateStr = dateStr.split("T")[0];  // Xử lý định dạng ngày giờ và bỏ phần giờ
//        }
//
//        return LocalDate.parse(dateStr);  // Chuyển chuỗi ngày thành LocalDate
//    }
//
//    // Lấy danh sách điểm danh cho một lớp học và ngày cụ thể
//    @GetMapping("/attendance/list/{classId}")
//    public String getAttendanceList(@PathVariable("classId") Integer classId, Model model) {
//        List<Attendance> attendanceList = attendanceService.getAttendanceByClassAndDate(classId);
//        model.addAttribute("attendanceList", attendanceList);
//        return "attendance/list";  // Trả về view danh sách điểm danh
//    }
//
//    @GetMapping
//    public String getAttendance(@RequestParam(required = false) Integer classId,
//                                @RequestParam(required = false) String date,
//                                Model model) {
//        if (classId == null) {
//            model.addAttribute("error", "classId là bắt buộc!");
//            return "error";  // Lỗi nếu thiếu classId
//        }
//
//        Classes classes = classService.getClassById(classId);
//        if (classes == null) {
//            model.addAttribute("error", "Không tìm thấy lớp học!");
//            return "error";  // Lỗi nếu không tìm thấy lớp học
//        }
//
//        List<Student> students = studentRepository.findByClassEntity_Id(classId);
//        LocalDate parsedDate = getDefaultDate(date);  // Phân tích ngày được cung cấp hoặc mặc định là ngày hôm nay
//        List<Attendance> attendanceList = attendanceService.getAttendanceByClassAndDate(classes, parsedDate);
//        List<Classes> allClasses = classService.getAllClasses();
//
//        model.addAttribute("class", classes);
//        model.addAttribute("students", students);
//        model.addAttribute("attendanceList", attendanceList);
//        model.addAttribute("attendanceDate", parsedDate);
//        model.addAttribute("classes", allClasses);
//
//        return "attendance/list";
//    }
//
//    @PostMapping("/create")
//    public String createAttendance(@RequestParam Integer classId,
//                                   @RequestParam(value = "attendanceDate", required = false) String attendanceDate,
//                                   @RequestParam(required = false) List<Integer> presentStudents,
//                                   @RequestParam Map<Integer, String> status,
//                                   @RequestParam Map<Integer, String> note,
//                                   Model model) {
//
//        if (presentStudents == null) {
//            presentStudents = List.of();
//        }
//        LocalDate parsedDate = getDefaultDate(attendanceDate);  // Lấy ngày điểm danh
//        Classes classes = classService.getClassById(classId);
//        if (classes == null) {
//            model.addAttribute("error", "Không tìm thấy lớp học!");
//            return "error";
//        }
//        try {
//            attendanceService.saveAttendance(classes, parsedDate, presentStudents, status, note);  // Lưu điểm danh
//        } catch (Exception e) {
//            logger.error("Lỗi khi lưu điểm danh cho lớp: {} vào ngày: {}", classes.getName(), parsedDate, e);
//            model.addAttribute("error", "Lỗi khi lưu điểm danh. Vui lòng thử lại.");
//            return "error";
//        }
//        return "redirect:/employee/class/attendance?classId=" + classId + "&date=" + parsedDate;  // Chuyển hướng tới trang điểm danh đã được cập nhật
//    }
//
//    @PostMapping("/employee/class/{classId}/attendance/update/{attendanceId}")
//    public String updateAttendance(@PathVariable Integer classId,
//                                   @PathVariable Integer attendanceId,
//                                   @RequestParam(value = "attendanceDate", required = false) String attendanceDate,
//                                   @RequestParam String status,
//                                   @RequestParam(value = "note", required = false) String note,
//                                   Model model) {
//
//        // Parse the date, defaulting to current date if not provided or invalid
//        LocalDate parsedDate = getDefaultDate(attendanceDate);
//
//        // Get class details using classId
//        Classes classes = classService.getClassById(classId);
//        if (classes == null) {
//            model.addAttribute("error", "Class with ID " + classId + " not found.");
//            return "error";  // Return an error page if class not found
//        }
//
//        // Get attendance details using attendanceId
//        Attendance attendance = attendanceService.getAttendanceById(attendanceId);
//        if (attendance == null) {
//            model.addAttribute("error", "Attendance record with ID " + attendanceId + " not found.");
//            return "error";  // Return an error page if attendance record not found
//        }
//
//        // Ensure that note is never null by defaulting to an empty string
//        note = Optional.ofNullable(note).orElse("");
//
//        // Try to update the attendance record
//        try {
//            attendanceService.updateAttendance(classes, parsedDate, attendanceId, status, note);
//        } catch (Exception e) {
//            logger.error("Error updating attendance for class: {} on date: {}", classes.getName(), parsedDate, e);
//            model.addAttribute("error", "Error occurred while updating attendance. Please try again.");
//            return "error";
//        }
//        model.addAttribute("success", "Attendance has been successfully updated.");
//        return "redirect:/employee/class/attendance?classId=" + classId + "&date=" + parsedDate;  // Redirect to the updated attendance page
//    }
//
//
//    @PostMapping("/employee/class/{classId}/attendance/delete/{attendanceId}")
//    public String deleteAttendance(@PathVariable Integer classId,
//                                   @PathVariable Integer attendanceId,
//                                   @RequestParam(value = "attendanceDate", required = false) String attendanceDate,
//                                   Model model) {
//        // Lấy ngày điểm danh nếu có, nếu không có thì dùng ngày mặc định
//        LocalDate parsedDate = getDefaultDate(attendanceDate);
//
//        // Lấy thông tin lớp học từ classId
//        Classes classes = classService.getClassById(classId);
//        if (classes == null) {
//            model.addAttribute("error", "Không tìm thấy lớp học!");
//            return "error";  // Trả về trang lỗi nếu lớp học không tồn tại
//        }
//
//        // Lấy thông tin điểm danh từ attendanceId
//        Attendance attendance = attendanceService.getAttendanceById(attendanceId);
//        if (attendance == null) {
//            model.addAttribute("error", "Không tìm thấy điểm danh!");
//            return "error";  // Trả về trang lỗi nếu không tìm thấy điểm danh
//        }
//
//        try {
//            // Thực hiện xóa điểm danh
//            attendanceService.deleteAttendance(classes, attendance.getStudent(), parsedDate);
//        } catch (Exception e) {
//            logger.error("Lỗi khi xóa điểm danh cho lớp: {} vào ngày: {}", classes.getName(), parsedDate, e);
//            model.addAttribute("error", "Lỗi khi xóa điểm danh. Vui lòng thử lại.");
//            return "error";  // Trả về trang lỗi nếu có sự cố khi xóa điểm danh
//        }
//
//        // Sau khi xóa thành công, chuyển hướng về trang danh sách điểm danh
//        return "redirect:/employee/class/attendance?classId=" + classId + "&date=" + parsedDate;
//    }
//}