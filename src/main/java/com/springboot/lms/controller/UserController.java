package com.springboot.lms.controller;

import com.springboot.lms.model.User;
import com.springboot.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user/signup")
    public ResponseEntity<?> signUp(@RequestBody User user)
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(user));
    }

}
