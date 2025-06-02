package com.springboot.lms.service;

import com.springboot.lms.model.Learner;
import com.springboot.lms.model.User;
import com.springboot.lms.repository.LearnerRepository;
import com.springboot.lms.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final LearnerRepository learnerRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LearnerRepository learnerRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.learnerRepository = learnerRepository;
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

            }
            default -> {
                return null;
            }
        }
        return null;

    }
}
