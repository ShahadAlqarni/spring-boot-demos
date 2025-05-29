package com.springboot.demo;

import com.springboot.demo.dao.AppDAO;
import com.springboot.demo.entity.Instructor;
import com.springboot.demo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {
           // createInstructor(appDAO);
            // findInstructor(appDAO);
            // deleteInstructor(appDAO);
           // findInstructorDetail(appDAO);
            deletetempInstructorDetail(appDAO);
        };
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
