package com.example.speingsec3;

import com.example.speingsec3.dao.StudentDAO;
import com.example.speingsec3.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Speingsec3Application {

	public static void main(String[] args) {
		SpringApplication.run(Speingsec3Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//createStudent(studentDAO);
			//createMultipleStudent(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);

			queryForStudentsByLastName(studentDAO);
		};
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Doe");

		// display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
//ابغى اسوي هنا لسته واطلعهم كلهم فيديو 80
		//retrieve student based on the id: primary key
		Student student = studentDAO.findById(1);
		System.out.println(student);
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("create 3 students object:");
		Student student1 = new Student("Deema", "saa", "SSS-33@NBN.CM");
		Student student2 = new Student("Shosho", "saa", "SSS-33@NBN.CM");
		Student student3 = new Student("Nony", "saa", "SSS-33@NBN.CM");

		//save the student object
		System.out.println("Saving the students object:");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);

		//display id for the saved student
		System.out.println("Saved student. Get the student Id:" + student1.getId());
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("create the student object:");
		Student crueentstudent = new Student("shahad", "saa", "SSS-33@NBN.CM");

		//save the student object
		System.out.println("Saving the student object:");
		studentDAO.save(crueentstudent);

		//display id for the saved student
		System.out.println("Saved student. Get the student Id:" + crueentstudent.getId());
	}

}
