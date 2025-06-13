package com.springboot.lms.service;

import com.springboot.lms.exception.ResourceNotFoundException;
import com.springboot.lms.model.Author;
import com.springboot.lms.model.Course;
import com.springboot.lms.repository.AuthorRepository;
import com.springboot.lms.repository.CourseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final AuthorRepository authorRepository;
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository, AuthorRepository authorRepository){
        this.courseRepository = courseRepository;
        this.authorRepository = authorRepository;
    }

    public void postCourse(Course course, String username){
        Author author = authorRepository.getByUsername(username);
        course.setAuthor(author);
        courseRepository.save(course);
    }

    public Course getCourseById(int id){
        return courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course not found for the given Id"));

    }

    public List<Course> getAllCourses(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findAll(pageable).getContent();
    }

    public List<Course> getCoursesByAuthor(String username){
        List<Course> courses = courseRepository.getByUsername(username);
        return courses;
    }

}
