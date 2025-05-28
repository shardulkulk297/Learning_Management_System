package com.springboot.lms.service;

import com.springboot.lms.exception.ResourceNotFoundException;
import com.springboot.lms.model.Course;
import com.springboot.lms.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public void postCourse(Course course){
        courseRepository.save(course);
    }

    public Course getCourseById(int id){
        return courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course not found for the given Id"));

    }

}
