package com.example.springRest.rest;

import com.example.springRest.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

   private List<Student> theStudents;

    //define @PostConstruct to load the student data Once.
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Shahad", "Alqarni"));
        theStudents.add(new Student("Deema", "Alqarni"));
        theStudents.add(new Student("Mario", "Alqarni"));
    }

    //define endpoint for "/student" return a list of student
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    //define endpoint or "/students/{studentsId}" to return student at index
    @GetMapping("/students/{studentsId}")
    public Student getStudent(@PathVariable int studentsId) {

        //check the id
        if (studentsId >= theStudents.size() || studentsId < 0) {
            throw new StudentNotFoundException("Student not found - "+studentsId);
        }
        return theStudents.get(studentsId);
    }

}
