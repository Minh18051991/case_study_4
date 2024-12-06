package org.example.case_study_4.controller.login;

import org.example.case_study_4.controller.my_module.ModuleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller

public class LoginController {

    @Autowired
    private ModuleController moduleController;
    @GetMapping("/login")
    public String showLoginForm() {
        return "login/login";

    }

    @GetMapping("/login-success")
    public String showLoginSuccess(Principal principal) {
        // Lấy quyền của người đăng nhập hiện tại
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");


//        if (role.equals("ROLE_ADMIN")) {
//            return "admin/home";
//        }
//        if (role.equals("ROLE_TEACHER")) {
//            return "teacher/home";
//        }
//        if (role.equals("ROLE_AO")) {
//            return "ao/home";
//        }


            return "/admin/home";



    }
}
