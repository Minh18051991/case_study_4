package org.example.case_study_4.controller.logout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logoutSuccessful() {
        return "login/login";
    }
    @GetMapping("/admin")
    public String adminLogoutSuccessful() {
        return "redirect:/login";
    }
}
