package com.springboot.demo.dao;

import com.springboot.demo.entity.Instructor;

public interface AppDAO {
    void  save (Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

}
