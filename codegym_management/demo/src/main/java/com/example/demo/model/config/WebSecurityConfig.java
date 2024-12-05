

package com.example.demo.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    // xác thực
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    // phân quyền
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         cấu hình có thể logout
        http.csrf(AbstractHttpConfigurer::disable);
        // tạo token cho method post

//        http.csrf(Customizer.withDefaults());
//        http.csrf((csrf) -> csrf
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//        );
//        // các đường dẫn không phải login
//        http.authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers("/", "/login","login-success", "/logout", "/logoutSuccessful", "/403").permitAll());
//        // cấp quyền cho student


        // các đường dẫn không phải login
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers( "/login","login-success", "/logout", "/logoutSuccessful", "/403").permitAll().anyRequest().authenticated());
//                .requestMatchers("/", "/login","login-success", "/logout", "/logoutSuccessful", "/403").permitAll());
        // cấp quyền cho student

//        http.authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers("/create").hasRole("STUDENT"));
//        // cấp quyền cho user và admin
//        http.authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers("/admin").hasRole("ADMIN"));
//        //cấp quyền cho ao
//        http.authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers("/admin").hasRole("AO"));
//        //cấp quyền cho teacher
//        http.authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers("/admin").hasRole("TEACHER"));
//        // cấu hình form login
//        http.formLogin(form -> form
//                .loginPage("/login")
//                .loginProcessingUrl("/process-login") // đường dẫn trùng với url form login
//                .defaultSuccessUrl("/login-success")//
//                .failureUrl("/login")
//                .usernameParameter("username")//trùng với tên trong form đăng nhập
//                .passwordParameter("password")// trung với tên trong form đăng nhập
//        );
//        // cấu hình logout
//        http.logout(form -> form
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true) // Xóa session
//                .deleteCookies("JSESSIONID")); // Xóa cookie phiên làm việc
//
//        // cấu hình trả về trang 403 khi không có quyền (role) truy cập
//        http.exceptionHandling(ex -> ex.accessDeniedPage("/403"));
//
////        // cấu hình remember-me : lưu trạng thái đăng nhập khi tắt trình duyệt => mở lại không cần login
////        http.rememberMe(remember -> remember.tokenRepository(persistentTokenRepository()));
//        return http.build();
//
//    }
//
//
//}


        // cấu hình form login
        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/process-login") // đường dẫn trùng với url form login
                .defaultSuccessUrl("/login-success")//
                .failureUrl("/login")
                .usernameParameter("username")//trùng với tên trong form đăng nhập
                .passwordParameter("password")// trung với tên trong form đăng nhập
        );
        // cấu hình logout
        http.logout(form -> form
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true) // Xóa session
                .deleteCookies("JSESSIONID")); // Xóa cookie phiên làm việc

        // cấu hình trả về trang 403 khi không có quyền (role) truy cập
        http.exceptionHandling(ex -> ex.accessDeniedPage("/403"));

//        // cấu hình remember-me : lưu trạng thái đăng nhập khi tắt trình duyệt => mở lại không cần login
//        http.rememberMe(remember -> remember.tokenRepository(persistentTokenRepository()));
        return http.build();

    }


}
