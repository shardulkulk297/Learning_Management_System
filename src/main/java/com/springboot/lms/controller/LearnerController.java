package com.springboot.lms.controller;

import com.springboot.lms.model.Learner;
import com.springboot.lms.service.LearnerService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class LearnerController {

    @Autowired
    private LearnerService learnerService;


    @GetMapping("api/learner/HelloRestApi")
    public String helloRest(){
        return "Hello Rest API";
    }

    @PostMapping("/api/learner/add")
    public ResponseEntity<?> insertLearner(@RequestBody Learner learner){
        learnerService.insertLearner(learner);
        return ResponseEntity.status(HttpStatus.CREATED).body("Learner Inserted Successfully");

    }

    @GetMapping("/api/learner/getAll")
    public ResponseEntity<?> getAllLearners(){
        return ResponseEntity.status(HttpStatus.OK).body(learnerService.getAllLearners());
    }

    @DeleteMapping("/api/learner/delete/{id}")
    public ResponseEntity<?> deleteLearnerById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body("Learner Deleted Successfully");
    }

    @GetMapping("/api/learner/getLearner")
    public ResponseEntity<?> getLearnerByUsername(Principal principal){
        String username = principal.getName();
        return ResponseEntity.status(HttpStatus.OK).body(learnerService.getLearnerByUsername(username));
    }

    @PutMapping("/api/learner/updateById/{id}")
    public ResponseEntity<?>  updateLearnerById(@PathVariable int id, @RequestBody Learner updatedLearner){
        return ResponseEntity.status(HttpStatus.OK).body(learnerService.updateLearnerById(id, updatedLearner));

    }

}
