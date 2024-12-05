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

    @GetMapping("/list")
    public String getAttendanceList(@RequestParam Integer classId, Model model) {
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
            model.addAttribute("error", "Không tìm thấy lớp học với ID: " + classId);
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

    @GetMapping("/create")
    public String createAttendanceForm(@RequestParam Integer classId, Model model) {
        Classes classes = classService.getClassById(classId);
        if (classes == null) {
            model.addAttribute("error", "Không tìm thấy lớp học!");
            return "error";
        }
        List<Student> students = studentRepository.findByClassEntity_Id(classId);
        model.addAttribute("class", classes);
        model.addAttribute("students", students);
        return "attendance/create";
    }

    // Method to handle form submission
    @PostMapping("/create")
    public String createAttendance(@RequestParam Integer classId,
                                   @RequestParam(value = "attendanceDate") String attendanceDate,
                                   @RequestParam(required = false) List<Integer> presentStudents,
                                   @RequestParam Map<Integer, String> status,  // Nhận trạng thái
                                   @RequestParam Map<Integer, String> note,    // Nhận ghi chú
                                   Model model) {
        if (presentStudents == null) {
            presentStudents = List.of();
        }

        // Chuyển đổi ngày điểm danh từ String sang LocalDate
        LocalDate parsedDate = LocalDate.parse(attendanceDate);

        // Tìm lớp học dựa trên classId
        Classes classes = classService.getClassById(classId);
        if (classes == null) {
            model.addAttribute("error", "Không tìm thấy lớp học!");
            return "error";
        }

        // Kiểm tra xem nếu map status và note có dữ liệu không
        if (status.isEmpty() || note.isEmpty()) {
            model.addAttribute("error", "Chưa có thông tin về trạng thái và ghi chú của sinh viên.");
            return "error";
        }

        // Tiến hành lưu điểm danh vào cơ sở dữ liệu
        try {
            attendanceService.saveAttendance(classes, parsedDate, presentStudents, status, note);
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lưu điểm danh. Vui lòng thử lại.");
            return "error";
        }

        return "redirect:/employee/class/attendance?classId=" + classId + "&date=" + parsedDate;
    }

    // Controller GET request để hiển thị trang chỉnh sửa
    // Controller GET request để hiển thị trang chỉnh sửa
    @GetMapping("/update/{classId}/{attendanceId}")
    public String updateAttendance(@PathVariable Integer classId,
                                   @PathVariable Integer attendanceId,
                                   Model model) {
        // Lấy thông tin điểm danh từ service
        Attendance attendance = attendanceService.getAttendanceById(attendanceId);

        // Kiểm tra nếu không tìm thấy điểm danh
        if (attendance == null) {
            model.addAttribute("error", "Không tìm thấy điểm danh với ID " + attendanceId);
            return "error";
        }

        // Thêm dữ liệu vào model để render trên view
        model.addAttribute("attendance", attendance);
        model.addAttribute("classId", classId);
        return "attendance/update"; // Trả về view "attendance/update" để chỉnh sửa
    }

    // Controller POST request để xử lý form và cập nhật điểm danh
    @PostMapping("/update")
    public String updateAttendance(@RequestParam Integer classId,
                                   @ModelAttribute Attendance attendance,
                                   @RequestParam String status,
                                   Model model) {
        // Kiểm tra lớp học có tồn tại không
        Classes classes = classService.getClassById(classId);
        if (classes == null) {
            model.addAttribute("error", "Lớp có ID " + classId + " không tìm thấy.");
            return "error"; // Trả về trang lỗi nếu lớp không tồn tại
        }

        // Kiểm tra điểm danh có tồn tại không
        if (attendance == null || attendance.getId() == null) {
            model.addAttribute("error", "Điểm danh không hợp lệ.");
            return "error"; // Trả về trang lỗi nếu điểm danh không hợp lệ
        }

        try {
            // Cập nhật điểm danh
            attendanceService.updateAttendance(attendance, status);
        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi khi cập nhật điểm danh. Vui lòng thử lại.");
            return "error"; // Trang lỗi nếu có ngoại lệ
        }

        // Sau khi cập nhật, lấy lại danh sách điểm danh theo lớp và ngày
        LocalDateTime date = attendance.getCreateAt();
        List<Attendance> attendanceList = attendanceService.findAttendanceByClassAndDate(date, classId);
        model.addAttribute("attendanceList", attendanceList);

        // Redirect đến trang danh sách điểm danh của lớp
        return "redirect:/employee/class/attendance?classId=" + classId;
    }


    @PostMapping("/delete")
    public String deleteAttendance(@RequestParam Integer classId,
                                   @RequestParam Integer attendanceId,
                                   Model model) {
        LocalDate parsedDate = LocalDate.now(); // Bạn có thể thay thế bằng logic lấy ngày thực tế
        Classes classes = classService.getClassById(classId);

        // Thêm log kiểm tra giá trị các tham số
        logger.info("Xóa điểm danh cho lớp: {} vào ngày: {}", classes.getName(), parsedDate);

        if (classes == null) {
            model.addAttribute("error", "Không tìm thấy lớp học!");
            return "error";
        }

        Attendance attendance = attendanceService.getAttendanceById(attendanceId);
        if (attendance == null) {
            model.addAttribute("error", "Không tìm thấy điểm danh!");
            return "error";
        }

        // Thêm log kiểm tra thông tin điểm danh trước khi xóa
        logger.info("Thông tin điểm danh cần xóa: {}", attendance);

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



