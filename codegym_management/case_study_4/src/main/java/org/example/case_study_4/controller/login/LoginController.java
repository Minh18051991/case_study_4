package org.example.case_study_4.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class LoginController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login/login";
    }

    @GetMapping("/login-success")
    public String showLoginSuccess() {
        return "login/loginSuccessful";
    }
}
