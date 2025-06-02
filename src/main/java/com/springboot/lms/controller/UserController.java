package com.springboot.lms.controller;

import com.springboot.lms.model.User;
import com.springboot.lms.service.UserService;
import com.springboot.lms.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user/signup")
    public ResponseEntity<?> signUp(@RequestBody User user)
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(user));
    }

    @GetMapping("/api/user/token")
    public ResponseEntity<?> getToken(Principal principal) {
        System.out.println("I am in the API method");

        JwtUtil jwtUtil = new JwtUtil();
        try {
            String token =jwtUtil.createToken(principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/api/user/details")
    public Object getUserDetails(Principal principal){
        String username = principal.getName();
        return userService.getLoggedInUser(username);
    }

}
