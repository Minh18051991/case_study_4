package org.example.case_study_4.controller.attendance;

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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee/class/attendance")
public class AttendanceController {
    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);
    @Autowired
    private IAttendanceService attendanceService;
    @Autowired
    private IClassService classService;
    @Autowired
    private IStudent studentRepository;

    private LocalDate getDefaultDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return LocalDate.now();
        }
        if (dateStr.contains("T")) {
            dateStr = dateStr.split("T")[0];
        }
        return LocalDate.parse(dateStr);
    }
    @GetMapping("/attendance/list/{classId}")
    public String getAttendanceList(@PathVariable("classId") Integer classId, Model model) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByClassAndDate(classId);
        model.addAttribute("attendanceList", attendanceList);
        return "attendance/list";
    }
    @GetMapping
    public String getAttendance(@RequestParam(required = false) Integer classId,
                                @RequestParam(required = false) String date,
                                Model model) {
        if (classId == null) {
            model.addAttribute("error", "classId là bắt buộc!");
            return "error";
        }
        Classes classes = classService.getClassById(classId);
        if (classes == null) {
            model.addAttribute("error", "Không tìm thấy lớp học!");
            return "error";
        }
        List<Student> students = studentRepository.findByClassEntity_Id(classId);
        LocalDate parsedDate = getDefaultDate(date);
        List<Attendance> attendanceList = attendanceService.getAttendanceByClassAndDate(classes, parsedDate);
        List<Classes> allClasses = classService.getAllClasses();
        model.addAttribute("class", classes);
        model.addAttribute("students", students);
        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("attendanceDate", parsedDate);
        model.addAttribute("classes", allClasses);
        return "attendance/list";
    }

    @PostMapping("/create")
    public String createAttendance(@RequestParam Integer classId,
                                   @RequestParam(value = "attendanceDate", required = false) String attendanceDate,
                                   @RequestParam(required = false) List<Integer> presentStudents,
                                   @RequestParam Map<Integer, String> status,
                                   @RequestParam Map<Integer, String> note,
                                   Model model) {
        if (presentStudents == null) {
            presentStudents = List.of();
        }
        LocalDate parsedDate = getDefaultDate(attendanceDate);
        Classes classes = classService.getClassById(classId);
        if (classes == null) {
            model.addAttribute("error", "Không tìm thấy lớp học!");
            return "error";
        }
        try {
            attendanceService.saveAttendance(classes, parsedDate, presentStudents, status, note);
        } catch (Exception e) {
            logger.error("Lỗi khi lưu điểm danh cho lớp: {} vào ngày: {}", classes.getName(), parsedDate, e);
            model.addAttribute("error", "Lỗi khi lưu điểm danh. Vui lòng thử lại.");
            return "error";
        }
        return "redirect:/employee/class/attendance?classId=" + classId + "&date=" + parsedDate;

    }
    @GetMapping("/employee/class/{classId}/attendance/update/{attendanceId}")
        public String updateAttendance(@PathVariable Integer classId,
                                       @PathVariable Integer attendanceId,
                                       Model model){
        Attendance attendance = attendanceService.getAttendanceById(attendanceId);

        model.addAttribute("attendance",attendance);
        model.addAttribute("classId",classId);
        return "attendance/update";
    }
    @PostMapping("/update")
    public String updateAttendance(@RequestParam Integer classId,
                                   @ModelAttribute Attendance attendance,
                                   @RequestParam String status,
                                   Model model) {
        Classes classes = classService.getClassById(classId);
        if (classes == null) {
            model.addAttribute("error", "Lớp có ID " + classId + " không tìm thấy.");
            return "error";
        }
        if (attendance == null) {
            model.addAttribute("error", "điểm danh ko có id " + attendance.getId() + " không tìm thấy.");
            return "error";
        }
        try {
            attendanceService.updateAttendance(attendance , status);
        } catch (Exception e) {

            model.addAttribute("error", "Đã xảy ra lỗi khi cập nhật điểm danh. Vui lòng thử lại.");
            return "error";
        }
        LocalDateTime date = attendance.getCreateAt();
        List<Attendance> attendanceList =attendanceService.findAttendanceByClassAndDate( date, classId);
        model.addAttribute("attendanceList", attendanceList);
        return "redirect:/employee/class/attendance?classId=" + classId ;
    }
    @PostMapping("/employee/class/{classId}/attendance/delete/{attendanceId}")
    public String deleteAttendance(@PathVariable Integer classId,
                                   @PathVariable Integer attendanceId,
                                   @RequestParam(value = "attendanceDate", required = false) String attendanceDate,
                                   Model model) {
        LocalDate parsedDate = getDefaultDate(attendanceDate);
        Classes classes = classService.getClassById(classId);
        if (classes == null) {
            model.addAttribute("error", "Không tìm thấy lớp học!");
            return "error";
        }
        Attendance attendance = attendanceService.getAttendanceById(attendanceId);
        if (attendance == null) {
            model.addAttribute("error", "Không tìm thấy điểm danh!");
            return "error";
        }
        try {
            attendanceService.deleteAttendance(classes, attendance.getStudent(), parsedDate);
        } catch (Exception e) {
            logger.error("Lỗi khi xóa điểm danh cho lớp: {} vào ngày: {}", classes.getName(), parsedDate, e);
            model.addAttribute("error", "Lỗi khi xóa điểm danh. Vui lòng thử lại.");
            return "error";
        }
        return "redirect:/employee/class/attendance?classId=" + classId + "&date=" + parsedDate;
    }

}