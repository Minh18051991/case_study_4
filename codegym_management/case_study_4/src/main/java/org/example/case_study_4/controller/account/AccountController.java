package org.example.case_study_4.controller.account;

import jakarta.transaction.Transactional;
import org.example.case_study_4.repository.otp.IOtpRepository;
import org.example.case_study_4.service.account.IAccountService;
import org.example.case_study_4.service.otp.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// imporrt các lớp để lấy quyền account
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private IOtpRepository otpRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private IAccountService accountService;


    @GetMapping("/enter-otp")
    public String updatePassword(Principal principal) {
        String username = principal.getName();
        otpService.sendOtpToEmail(username);
        return "account/verifyOtp";
    }

    @PostMapping("/confirm-otp")
    public String confirmOtp(@RequestParam String otp, Principal principal, Model model) {
        String username = principal.getName();

        if (otpService.verifyOtp(username, otp)) {
            model.addAttribute("message", "Mã OTP đã chính xác!");
            return "account/updateForm";
        } else {
            model.addAttribute("error", "Mã OTP không chính xác!");
            return "account/verifyOtp";
        }

    }

    @Transactional
    @PostMapping("/update-password")
    public String updatePassword(Principal principal, @RequestParam String newPassword,Model model) {
        String username = principal.getName();
        accountService.updateAccount(username, newPassword);

        // Lấy quyền của người đăng nhập hiện tại
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");


        model.addAttribute("userRole", role);

        // Xóa OTP sau khi sử dụng
        otpRepository.deleteByUsername(username);


        return "account/updatePassword";
    }


}
