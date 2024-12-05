package org.example.case_study_4.service.attendance;

import org.example.case_study_4.model.Attendance;
import org.example.case_study_4.model.Attendance.AttendanceStatus;
import org.example.case_study_4.model.Classes;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.repository.attendance.AttendanceRepository;
import org.example.case_study_4.repository.student.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AttendanceService implements IAttendanceService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Attendance> getAttendanceByClassAndDate(Classes classes, LocalDate date) {
        logger.debug("Đang lấy điểm danh cho lớp: {} vào ngày: {}", classes.getName(), date);
        return attendanceRepository.findByClassEntityAndCreateAtBetween(
                classes,
                date.atStartOfDay(),
                date.plusDays(1).atStartOfDay().minusSeconds(1)
        );
    }
    @Override
    @Transactional
    public void deleteAttendance(Classes classes, Student student, LocalDate date) {
        logger.debug("Đang thử xóa điểm danh cho lớp: {} vào ngày: {} cho sinh viên: {}",
                classes.getName(), date, student.getName());
        List<Attendance> attendanceList = attendanceRepository.findByClassEntityAndCreateAtBetween(
                classes,
                date.atStartOfDay(),
                date.plusDays(1).atStartOfDay().minusSeconds(1)
        );
        if (!attendanceList.isEmpty()) {
            attendanceRepository.deleteAll(attendanceList);
            logger.info("Đã xóa điểm danh thành công cho lớp {} vào ngày {}.", classes.getName(), date);
        } else {
            logger.warn("Không tìm thấy bản ghi điểm danh để xóa cho lớp {} vào ngày {}", classes.getName(), date);
        }
    }

    @Override
    @Transactional
    public void updateAttendance(Attendance attendance, String status) {
        if (attendance != null) {
            if (status != null && !status.isEmpty()) {
                try {
                    AttendanceStatus newStatus = AttendanceStatus.valueOf(status);
                    if (attendance.getStatus() != newStatus) {
                        attendance.setStatus(newStatus);
                    }
                } catch (IllegalArgumentException e) {
                    logger.error("Trạng thái không hợp lệ: {}", status, e);
                    throw new IllegalArgumentException("Trạng thái không hợp lệ: " + status);
                }
            }
            attendanceRepository.save(attendance);
        } else {
            logger.error("Không tìm thấy bản ghi điểm danh cho sinh viên ID {}");
            throw new NoSuchElementException("Không tìm thấy điểm danh cho sinh viên ID: ");
        }
    }

    @Override
    @Transactional
    public void saveAttendance(Classes classes, LocalDate attendanceDate,
                               List<Integer> presentStudents, Map<Integer, String> status,
                               Map<Integer, String> note) {
        List<Attendance> attendanceList = new ArrayList<>();

        for (Integer studentId : presentStudents) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new NoSuchElementException("Student not found"));

            Attendance attendance = new Attendance();
            attendance.setClassEntity(classes);
            attendance.setCreateAt(attendanceDate.atStartOfDay());
            attendance.setStudent(student);
            try {
                String studentStatus = status.get(studentId);
                if (studentStatus != null) {
                    switch (studentStatus) {
                        case "VANG_CO_PHEP":
                            attendance.setStatus(AttendanceStatus.VANG_CO_PHEP);
                            break;
                        case "CO_MAT":
                            attendance.setStatus(AttendanceStatus.CO_MAT);
                            break;
                        case "DI_MUON":
                            attendance.setStatus(AttendanceStatus.DI_MUON);
                            break;
                        case "VANG_KHONG_PHEP":
                            attendance.setStatus(AttendanceStatus.VANG_KHONG_PHEP);
                            break;
                        default:
                            attendance.setStatus(AttendanceStatus.CO_MAT);
                            logger.error("Invalid attendance status for studentId: {}", studentId);
                            break;
                    }
                } else {
                    attendance.setStatus(AttendanceStatus.CO_MAT);
                }
            } catch (IllegalArgumentException e) {
                logger.error("Invalid attendance status for studentId: {}", studentId);
                attendance.setStatus(AttendanceStatus.CO_MAT);
            }

            String studentNote = note.get(studentId);
            attendance.setNote(studentNote != null ? studentNote : "");

            attendanceList.add(attendance);
        }
        if (!attendanceList.isEmpty()) {
            attendanceRepository.saveAll(attendanceList);
            logger.info("Đã lưu {} bản ghi điểm danh cho lớp {} vào ngày {}.", attendanceList.size(), classes.getName(), attendanceDate);
        } else {
            logger.warn("Không có bản ghi điểm danh nào để lưu cho lớp: {}", classes.getName());
        }
    }

    @Override
    public Attendance getAttendanceById(Integer attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElse(new Attendance());
        return attendance;
    }

    @Override
    public List<Attendance> getAttendanceByClassAndDate(Integer classId) {
        return attendanceRepository.findAll();
    }


    private AttendanceStatus parseStatus(String status) {
        if (status == null || status.isEmpty()) {
            return AttendanceStatus.VANG_KHONG_PHEP;
        }
        try {
            return AttendanceStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            logger.error("Chuỗi trạng thái không hợp lệ: {}", status, e);
            return AttendanceStatus.VANG_KHONG_PHEP;
        }
    }


    public List<Attendance> findAttendanceByClassAndDate(LocalDateTime createAt, Integer classesId) {
        return attendanceRepository.findAttendanceByClassAndDate(createAt, classesId);

    }
}
