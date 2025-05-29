package com.example.speingsec3.dao;

import com.example.speingsec3.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer theId);

    List<Student> findAll();

    List<Student> findByLastName(String theLastName);
}
