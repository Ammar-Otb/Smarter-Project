package com.example.capstoneproject.config;

import com.example.capstoneproject.student.StudentService;
import com.example.capstoneproject.tutor.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TutorService tutorService;
    private final StudentService studentService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(tutorService).passwordEncoder(new BCryptPasswordEncoder());
        auth.userDetailsService(studentService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/tutor/register","/api/v1/student/register","/api/v1/course/","/api/v1/tutor/","/api/v1/student/","/api/v1/request/").permitAll()
                .antMatchers("/api/v1/tutor/course/add/{courseId}/{tutorId}","/api/v1/tutor/create/session/{tutorId}","/api/v1/tutor/add/student/{studentId}/{sessionId}",
                        "/api/v1/tutor/delete/session/{id}","/api/v1/tutor/report/{sessionId}","/api/v1/request/accept/{tutorId}/{requestId}").hasRole("TUTOR")
                .antMatchers("/api/v1/request/send", "/api/v1/student/update/email/{studentId}/{email}","/api/v1/student/courses/{name}","/api/v1/student/courses" ).hasRole("STUDENT")
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}
