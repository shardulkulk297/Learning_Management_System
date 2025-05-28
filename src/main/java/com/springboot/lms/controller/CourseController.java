package com.springboot.lms.controller;

import com.springboot.lms.model.Course;
import com.springboot.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;



    @PostMapping("/add")
    public ResponseEntity<?> postCourse(@RequestBody Course course){
        courseService.postCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course Posted Successfully");
    }

    @GetMapping("/getById/{id}")
    public Course getCourseById(@PathVariable int id){
        return courseService.getCourseById(id);
    }

}
