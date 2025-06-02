package com.springboot.lms.controller;

import com.springboot.lms.model.Author;
import com.springboot.lms.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/api/author/add")
    public ResponseEntity<?> postAuthor(@RequestBody Author author){
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.postAuthor(author));
    }

}
