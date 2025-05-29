package com.springboot.demo.dao;

import com.springboot.demo.entity.Course;
import com.springboot.demo.entity.Instructor;
import com.springboot.demo.entity.InstructorDetail;
import com.springboot.demo.entity.Student;

import java.util.List;

public interface AppDAO {
    void  save (Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCourseByInstructorId(int theId);

    Instructor findInstructorByJoinFetch(int theId);

    void update(Instructor theInstructor);

    void update(Course theCourse);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentByCourseId(int theId);

    Student findStudentAndCourseByCourseId(int theId);

   //add more courses for student
    void update(Student tempStudent);

    void deleteStudentById(int theId);
}
