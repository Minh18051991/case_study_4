package org.example.case_study_4.controller.attendance;

import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.model.Attendance.AttendanceStatus;
import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.service.attendance.AttendanceService;
import org.example.case_study_4.service.classes.ClassService;
import org.example.case_study_4.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee/class/{classId}/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private ClassService classService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public String getAttendance(@PathVariable Integer classId, @RequestParam(required = false) String date, Model model) {
        Classes classObj = classService.getClassById(classId);
        LocalDate attendanceDate = (date == null) ? LocalDate.now() : LocalDate.parse(date);
        List<Attendance> attendanceList = attendanceService.getAttendanceByClassAndDate(classObj, attendanceDate);
        List<Student> students = studentRepository.findByClassEntity_Id(classId);

        model.addAttribute("class", classObj);
        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("attendanceDate", attendanceDate);
        model.addAttribute("students", students);
        return "attendance/list";
    }


    @PostMapping("/create")
    public String createAttendance(@PathVariable Integer classId,
                                   @RequestParam("attendanceDate") String attendanceDate,
                                   @RequestParam List<Integer> presentStudents,
                                   @RequestParam Map<Integer, String> status) {
        Classes classObj = classService.getClassById(classId);

        attendanceService.saveAttendance(classObj, LocalDate.parse(attendanceDate), presentStudents, status);

        return "redirect:/employee/class/{classId}/attendance?date=" + attendanceDate;
    }


    @PostMapping("/update/{studentId}")
    public String updateAttendance(@PathVariable Integer classId, @PathVariable Integer studentId, @RequestParam("attendanceDate") String attendanceDate, @RequestParam("status") String status) {
        Classes classObj = classService.getClassById(classId);
        AttendanceStatus attendanceStatus = AttendanceStatus.valueOf(status);
        attendanceService.updateAttendance(classObj, LocalDate.parse(attendanceDate), studentId, attendanceStatus);
        return "redirect:/employee/class/{classId}/attendance?date=" + attendanceDate;
    }

    @PostMapping("/delete")
    public String deleteAttendance(@PathVariable Integer classId, @RequestParam("attendanceDate") String attendanceDate) {
        Classes classObj = classService.getClassById(classId);
        attendanceService.deleteAttendance(classObj, LocalDate.parse(attendanceDate));
        return "redirect:/employee/class/{classId}/attendance?date=" + attendanceDate;
    }
}
