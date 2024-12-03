package org.example.case_study_4.service.attendance;

import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.model.Attendance.AttendanceStatus;
import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.repository.attendance.AttendanceRepository;
import org.example.case_study_4.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService implements IAttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private StudentRepository studentRepository;


    public List<Attendance> getAttendanceByClassAndDate(Classes classes, LocalDate date) {
        return attendanceRepository.findByClassEntityAndCreateAtBetween(
                classes,
                date.atStartOfDay(),
                date.plusDays(1).atStartOfDay()
        );
    }

    public void saveAttendance(Classes classes, LocalDate date, List<Integer> presentStudents, Map<Integer,String> status) {

        List<Student> students = studentRepository.findByClassEntity_Id(classes.getId());

        for (Student student : students) {
            Attendance attendance = new Attendance();
            attendance.setClassEntity(classes);
            attendance.setCreateAt(LocalDateTime.now());
            attendance.setStudent(student);

            if (presentStudents.contains(student.getId())) {
                attendance.setStatus(AttendanceStatus.CO_MAT);
            } else if (isLate(student)) {
                attendance.setStatus(AttendanceStatus.DI_MUON);
            } else if (isExcusedAbsent(student)) {
                attendance.setStatus(AttendanceStatus.VANG_CO_PHEP);
            } else {
                attendance.setStatus(AttendanceStatus.VANG_KHONG_PHEP);
            }

            attendanceRepository.save(attendance);
        }
    }

    public void updateAttendance(Classes classes, LocalDate date, Integer studentId, AttendanceStatus status) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Attendance attendance = attendanceRepository.findByClassEntityAndCreateAtAndStudent(classes, date.atStartOfDay(), student);

        if (attendance != null) {
            attendance.setStatus(status);
            attendanceRepository.save(attendance);
        }
    }

    public void deleteAttendance(Classes classes, LocalDate date) {
        attendanceRepository.deleteByClassEntityAndCreateAtBetween(
                classes,
                date.atStartOfDay(),
                date.plusDays(1).atStartOfDay()
        );
    }
    private boolean isLate(Student student) {
        return false;
    }
    private boolean isExcusedAbsent(Student student) {
        return false;
    }
}
