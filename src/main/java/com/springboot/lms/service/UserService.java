package com.springboot.lms.service;

import com.springboot.lms.model.Author;
import com.springboot.lms.model.Learner;
import com.springboot.lms.model.User;
import com.springboot.lms.repository.AuthorRepository;
import com.springboot.lms.repository.LearnerRepository;
import com.springboot.lms.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final LearnerRepository learnerRepository;
    private final AuthorRepository authorRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LearnerRepository learnerRepository, AuthorRepository authorRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.learnerRepository = learnerRepository;
        this.authorRepository = authorRepository;
    }

    public User signUp(User user){

        String password = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public Object getLoggedInUser(String username) {

        User user = userRepository.getByUsername(username);

        switch (user.getRole().toUpperCase()){
            case "LEARNER"->{
                Learner learner = learnerRepository.getByUsername(username);
                return learner;
            }
            case "AUTHOR"->{

                Author author = authorRepository.getByUsername(username);
                if(author.isActive())
                {
                    return author;
                }
                else{
                    throw new RuntimeException("Author Not Active");
                }

            }
            default -> {
                return null;
            }
        }


    }
}
