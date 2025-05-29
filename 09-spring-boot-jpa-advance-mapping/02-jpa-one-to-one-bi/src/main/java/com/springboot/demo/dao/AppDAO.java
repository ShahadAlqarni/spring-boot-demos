package com.springboot.demo.dao;

import com.springboot.demo.entity.Instructor;
import com.springboot.demo.entity.InstructorDetail;

public interface AppDAO {
    void  save (Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

}
