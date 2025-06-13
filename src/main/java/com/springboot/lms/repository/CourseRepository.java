package com.springboot.lms.repository;

import com.springboot.lms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("Select c from Course c WHERE c.author.user.username = ?1")
    List<Course> getByUsername(String username);
}
