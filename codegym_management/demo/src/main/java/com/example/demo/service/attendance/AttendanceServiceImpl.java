package com.example.demo.service.attendance;

import com.example.demo.dto.AttendanceRequest;
import com.example.demo.model.Attendance;
import com.example.demo.model.Classes;
import com.example.demo.model.Employee;
import com.example.demo.model.Student;
import com.example.demo.repository.attendance.AttendanceRepository;
import com.example.demo.repository.classes.ClassesRepository;
import com.example.demo.repository.employee.EmployeeRepository;
import com.example.demo.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ClassesRepository classesRepository;  // Đảm bảo sử dụng đúng repository

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Attendance markAttendance(AttendanceRequest request) {

        // Lấy thông tin học viên, lớp học, giảng viên từ request
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Classes classEntity = classesRepository.findById(request.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Tạo đối tượng điểm danh mới
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setClassEntity(classEntity);
        attendance.setEmployee(employee);
        attendance.setStatus(Attendance.AttendanceStatus.valueOf(request.getStatus()));  // Chuyển đổi trạng thái
        attendance.setNote(request.getNote());  // Ghi chú nếu có
        attendance.setCreateAt(LocalDateTime.now());  // Gán thời gian điểm danh
        attendance.setIsDelete(false);  // Đánh dấu là chưa bị xoá

        // Lưu điểm danh vào cơ sở dữ liệu
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance updateAttendance(Integer attendanceId, AttendanceRequest request) {
        // Tìm bản ghi điểm danh theo ID
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        // Cập nhật trạng thái và ghi chú điểm danh
        attendance.setStatus(Attendance.AttendanceStatus.valueOf(request.getStatus()));  // Cập nhật trạng thái
        attendance.setNote(request.getNote());  // Cập nhật ghi chú

        // Lưu lại điểm danh đã được cập nhật
        return attendanceRepository.save(attendance);
    }

    @Override
    public void deleteAttendance(Integer attendanceId) {
        // Tìm bản ghi điểm danh theo ID
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        // Thực hiện soft delete (đánh dấu là đã xóa)
        attendance.setIsDelete(true);  // Đánh dấu là đã xóa
        attendanceRepository.save(attendance);  // Cập nhật lại trong cơ sở dữ liệu
    }

    @Override
    public List<Attendance> getAttendanceByDate(LocalDateTime start, LocalDateTime end) {
        // Lấy điểm danh theo khoảng thời gian (chỉ lấy điểm danh không bị xóa)
        return attendanceRepository.findByCreateAtBetweenAndIsDeleteFalse(start, end);  // Chỉ lấy bản ghi chưa bị xóa
    }
}
