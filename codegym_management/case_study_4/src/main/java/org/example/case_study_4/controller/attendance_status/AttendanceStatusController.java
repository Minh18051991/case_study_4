package org.example.case_study_4.controller.attendance_status;

import org.example.case_study_4.dto.attendanceStatusDto.AttendanceStatusCount;
import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.service.account.IAccountSer;
import org.example.case_study_4.service.attendance_status.IAttendanceStatusService;
import org.example.case_study_4.service.studentL.IStudentSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/attendances")
public class AttendanceStatusController {
    @Autowired
    private IAttendanceStatusService attendanceStatusService;
    @Autowired
    private IStudentSer studentSer;
    @Autowired
    private IAccountSer accountSer;

    @GetMapping("/status")
    public String attendanceStatus(Model model, Principal principal) {
        String username = principal.getName();

        Account account = accountSer.getAccount(username);
        Student student = studentSer.findByAccount(account);

        Integer studentId = student.getId();

        List<Attendance> attendanceList = attendanceStatusService.findByStudentIdAndIsDeleteFalse(studentId);
        model.addAttribute("attendanceList", attendanceList);

        int absenceWithPermission = 0;
        int absenceWithoutPermission = 0;
        int late = 0;

        for (Attendance attendance : attendanceList) {
            if (attendance.getStatus() == Attendance.AttendanceStatus.VANG_CO_PHEP){
                absenceWithPermission++;
            } else if (attendance.getStatus() == Attendance.AttendanceStatus.VANG_KHONG_PHEP){
                absenceWithoutPermission++;
            } else if (attendance.getStatus() == Attendance.AttendanceStatus.DI_MUON){
                late++;
            }
        }

        AttendanceStatusCount attendanceStatusCount = new AttendanceStatusCount(absenceWithPermission, absenceWithoutPermission, late);
        model.addAttribute("attendanceStatusCount", attendanceStatusCount);
        model.addAttribute("student", student);
        return "attendance/status";
    }
}
