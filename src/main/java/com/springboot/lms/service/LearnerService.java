package com.springboot.lms.service;

import com.springboot.lms.model.Learner;
import com.springboot.lms.repository.LearnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnerService {

    private LearnerRepository learnerRepository;

    public LearnerService(LearnerRepository learnerRepository){
        this.learnerRepository = learnerRepository;
    }

    public Learner insertLearner(Learner learner){
        return learnerRepository.save(learner);
    }

    public List<Learner> getAllLearners(){
        return learnerRepository.findAll();
    }

    public void deleteLearnerById(int id){
        learnerRepository.deleteById(id);
    }

    public Learner getLearnerById(int id){
        return learnerRepository.findById(id).orElseThrow(()->new RuntimeException("Learner Not found"));
    }

    public Learner updateLearnerById(int id, Learner learner){
        Learner learnerToUpdate = learnerRepository.findById(id).orElseThrow(()->new RuntimeException("Learner Not found"));

        if(learner.getName() != null){
            learnerToUpdate.setName(learner.getName());
        }
        if(learner.getContact()!= null){
            learnerToUpdate.setContact(learner.getContact());
        }
        if(learner.getEmail() != null){
            learnerToUpdate.setContact(learner.getContact());
        }

        return learnerRepository.save(learnerToUpdate);

     }






}
