package com.springboot.lms.service;

import com.springboot.lms.model.Author;
import com.springboot.lms.model.User;
import com.springboot.lms.repository.AuthorRepository;
import com.springboot.lms.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthorRepository authorRepository;

    public AuthorService(UserRepository userRepository, UserService userService, AuthorRepository authorRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.authorRepository = authorRepository;
    }

    public Author postAuthor(Author author) {
        User user = author.getUser();
        user.setRole("AUTHOR");
        user = userService.signUp(user);
        author.setUser(user);
        author.setActive(true);
        return authorRepository.save(author);
    }
}
