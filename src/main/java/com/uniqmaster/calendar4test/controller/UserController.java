package com.uniqmaster.calendar4test.controller;

import com.uniqmaster.calendar4test.dto.LoginRequest;
import com.uniqmaster.calendar4test.entity.Student;
import com.uniqmaster.calendar4test.entity.Teacher;
import com.uniqmaster.calendar4test.util.JwtUtils;
import com.uniqmaster.calendar4test.entity.UserEntity;
import com.uniqmaster.calendar4test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController()
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager manager;
    private final JwtUtils jwtUtils;


    public UserController(UserService userService, AuthenticationManager manager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.manager = manager;
        this.jwtUtils = jwtUtils;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/studentregister", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody Student student) throws Exception {
        try {
            return new ResponseEntity(userService.saveStudent(student), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/teacherregister", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody Teacher teacher) throws Exception {
        try {
            return new ResponseEntity(userService.saveTeacher(teacher), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/jwt/login")
    public @ResponseBody
    ResponseEntity<?> jwtLogin(@RequestBody LoginRequest jwtAuth, HttpServletResponse response) {

        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuth.getUsername(), jwtAuth.getPassword()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        response.addHeader("Auth", jwtUtils.generateToken(jwtAuth.getUsername()));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
