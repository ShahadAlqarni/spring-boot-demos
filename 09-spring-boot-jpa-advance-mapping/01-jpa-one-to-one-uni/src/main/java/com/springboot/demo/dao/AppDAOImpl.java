package com.springboot.demo.dao;

import com.springboot.demo.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {
    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injections
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the Instructor
        Instructor tempInstructor = entityManager.find(Instructor.class,theId);

        // delete the Instructor
        entityManager.remove(tempInstructor);
    }
}
