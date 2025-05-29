package com.thymeleaf.thymeleaf.controller;

import com.thymeleaf.thymeleaf.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller("/showStudentForm")
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @GetMapping("/showStudentForm")
    public String showform(Model model) {
        //create student object
        Student thestudent = new Student();

        //add student object to the mode
        model.addAttribute("student", thestudent);

        //add list of countries to the model
        model.addAttribute("countries", countries);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student student) {
        //log the input dada
        System.out.println("student" + student.getFirstName() + " " + student.getLastName() );

        return "student-Confirmation";
    }


}
