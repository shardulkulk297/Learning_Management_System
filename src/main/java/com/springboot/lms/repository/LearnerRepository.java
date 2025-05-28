package com.springboot.lms.repository;

import com.springboot.lms.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Integer> {

}
