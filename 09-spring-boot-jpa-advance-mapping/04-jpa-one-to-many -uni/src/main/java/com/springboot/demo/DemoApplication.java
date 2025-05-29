package com.springboot.demo;

import com.springboot.demo.dao.AppDAO;
import com.springboot.demo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {
            //createCourseAndStudents(appDAO);
            //findCourseAndStudents(appDAO);
            //findStudentAndCourse(appDAO);
            //addMoreCoursesForStudent(appDAO);
            //deleteCourse(appDAO);
            deleteStudent(appDAO);
        };
    }

    private void deleteStudent(AppDAO appDAO) {
        int theId = 2;
        appDAO.deleteStudentById(theId);
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        int theId=2;
        Student tempStudent = appDAO.findStudentAndCourseByCourseId(theId);

        //crete more courses
        Course tempCourse1 = new Course("Math - learning Math");
        Course tempCourse2 = new Course("How to speed cube");

        //add course to student
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        appDAO.update(tempStudent);
    }

    private void findStudentAndCourse(AppDAO appDAO) {
        int theId=1;
        Student tempStudent = appDAO.findStudentAndCourseByCourseId(theId);
    }

    private void findCourseAndStudents(AppDAO appDAO) {
        int theId = 11;
        Course tempCourse = appDAO.findCourseAndStudentByCourseId(theId);
    }

    private void createCourseAndStudents(AppDAO appDAO) {
        //create a course
        Course tempCourse = new Course("Programming - learning code");

        //create the student
        Student tempStudent1 = new Student("Shahad","Saad","sssb@heh");
        Student tempStudent2 = new Student("Nony","Alqari","weghvdfv@sdkj");

        //add student to the course
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        //save the course and associated student
        appDAO.save(tempCourse);
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int theId = 10;
        //this line will delete course and associated reviews, due to CascadeType.ALL
        appDAO.deleteCourseById(theId);
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        //get the course and reviews
        int theId=10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReview());
    }

    private void createCourseAndReviwes(AppDAO appDAO) {
        //create a course
        Course tempCourse = new Course("Programming - learning code");

        //add some reviews
        tempCourse.addReview(new Review("Great course . . . loved it!"));
        tempCourse.addReview(new Review("Cool course!!"));
        tempCourse.addReview(new Review("!!!!!!!!!!"));

        //save the course and leverage the cascade all
        appDAO.save(tempCourse);
    }

    private void deleteCourse(AppDAO appDAO) {
        int theId = 13;
        appDAO.deleteCourseById(theId);
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 10;

        //find the Course
        Course tempCourse = appDAO.findCourseById(theId);

        // update the Instructor
        tempCourse.setTitle("Enjoy the Simple Things");
        appDAO.update(tempCourse);
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;

        //find the instructor
        Instructor temptheInstructor = appDAO.findInstructorById(theId);

        // update the Instructor
        temptheInstructor.setLastName("SAAD");
        appDAO.update(temptheInstructor);
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorByJoinFetch(theId);
        System.out.println(tempInstructor);

        //associate the object
        // tempInstructor.setCourses(courses);
        System.out.println(tempInstructor.getCourses());
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println(tempInstructor);

        // find courses for Instructor
        List<Course> courses = appDAO.findCourseByInstructorId(theId);

        //associate the object
        tempInstructor.setCourses(courses);
        System.out.println(tempInstructor.getCourses());
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println(tempInstructor);
        System.out.println(tempInstructor.getCourses());

    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        //create the Instructor
        Instructor tempInstructor = new Instructor("Nony", "Alqarni", "Nony@euteuteu");

        // create the instructor derail
        InstructorDetail tempInstructorDetail = new InstructorDetail("testNony", "testNNN");

        //associate the object
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //create some courses
        Course tempCourse1 = new Course("Programming - learning code");
        Course tempCourse2 = new Course("Air Guitar - Yhe Ultimate Guide");

        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        //save the courses
        appDAO.save(tempInstructor);


    }

    private void deletetempInstructorDetail(AppDAO appDAO) {
        int theId = 3;
        appDAO.deleteInstructorDetailById(theId);
    }

    private void findInstructorDetail(AppDAO appDAO) {

        //get the instructor detail object
        int theId = 2;
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        //print the instructor detail
        System.out.println(tempInstructorDetail);

        //print the associated instructor
        System.out.println(tempInstructorDetail.getInstructor());
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;
        appDAO.deleteInstructorById(theId);
    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor" + tempInstructor);
        System.out.println(tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {

		/*
		//create the Instructor
		Instructor tempInstructor = new Instructor("shahad","Alqarni","shahas@euteuteu");

		// create the instructor derail
		InstructorDetail tempInstructorDetail = new InstructorDetail("dbbndfbndbnd","test");

		//associate the object
		tempInstructor.setInstructorDetail(tempInstructorDetail);
*/

        //create the Instructor
        Instructor tempInstructor = new Instructor("Deema", "Alqarni", "Deema@euteuteu");

        // create the instructor derail
        InstructorDetail tempInstructorDetail = new InstructorDetail("test2", "test2");

        //associate the object
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //save the instructor
        //
        //Note: this will ALSO save the details object
        //because of CascadeType.ALL
        System.out.println("Saving instructor: " + tempInstructor);
        appDAO.save(tempInstructor);
        System.out.println("Done!");
    }

}
