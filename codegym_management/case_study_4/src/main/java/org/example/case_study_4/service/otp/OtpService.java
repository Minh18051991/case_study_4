package org.example.case_study_4.service.otp;

import jakarta.transaction.Transactional;
import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.Employee;
import org.example.case_study_4.model.Otp;
import org.example.case_study_4.model.Student;
import org.example.case_study_4.repository.employee.EmployeeRepository;
import org.example.case_study_4.repository.otp.IOtpRepository;
import org.example.case_study_4.repository.student.IStudentRepository;
import org.example.case_study_4.service.EmailService;
import org.example.case_study_4.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IOtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private IAccountService accountService;

    public void sendOtpToEmail(String username) {
        String otp = generateOTP(); // Tạo mã OTP
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5); // Thời gian hết hạn

        // Lưu OTP vào cơ sở dữ liệu
        Otp otpData = new Otp();
        otpData.setUsername(username);
        otpData.setOtp(otp);
        otpData.setExpiresAt(expiresAt);
        otpRepository.save(otpData);

        Account account = accountService.findAccountByUsername(username);

        if (account == null) {
            throw new IllegalArgumentException("Tài khoản không tồn tại!");
        }

        String email = "";
        String name = "";

        Student student = studentRepository.findByAccount(account);
        if (student != null) {
            email = student.getEmail();
            name = student.getName();
        }

        Employee employee = employeeRepository.findByAccount(account);
        if (employee != null) {
            email = employee.getEmail();
            name = employee.getName();
        }

        // Gửi OTP qua email
//        emailService.sendOtpEmail(email, otp);

        emailService.sendResetPasswordEmail(email, name, otp);
    }

    public boolean verifyOtp(String username, String otp) {
        Otp otpData = otpRepository.findByUsername(username);

        if (otpData == null || otpData.getExpiresAt().isBefore(LocalDateTime.now()) || !otpData.getOtp().equals(otp)) {
            return false;
        }
        return true;

    }


    //tạo mã otp
    public static String generateOTP() {
        int otpLength = 6; // Độ dài của OTP
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < otpLength; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }


    // Chạy mỗi 15p để xóa các OTP hết hạn
    @Scheduled(cron = "0 */15 * * * ?") // Chạy vào mỗi 15p
    @Transactional
    public void cleanupExpiredOtps() {
        otpRepository.deleteExpiredOtps();
        System.out.println("Đã xóa các OTP hết hạn.");
    }
}
