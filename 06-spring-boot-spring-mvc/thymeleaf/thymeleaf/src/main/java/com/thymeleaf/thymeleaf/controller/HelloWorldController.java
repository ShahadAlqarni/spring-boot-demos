package com.thymeleaf.thymeleaf.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class HelloWorldController {
    // need a controller method to show initial HTML form
    @RequestMapping("/showform")
    public String showForm() {
        return "helloworld-form";
    }

    // need a controller method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    // need a controller method to read form data and add data to the model
    @RequestMapping("/processFormVersion2")
    public String letsShowData(HttpServletRequest request, Model model) {
        //read the request parameter from the html form
        String name = request.getParameter("StudentName");

        //convert the data to all caps
        name = name.toUpperCase();

        //create the message
        String result = "Hello " + name + "!";

        //add message to the model
        model.addAttribute("message", result);

        return "helloworld";

    }

    // need a controller method to read form data and add data to the model using @RequestParam("StudentName")
    @PostMapping("/processFormVersion3")
    public String processFormVersion3(@RequestParam("StudentName") String theName, Model model) {

        //convert the data to all caps
        theName = theName.toUpperCase();

        //create the message
        String result = "Yoo " + theName + "!";

        //add message to the model
        model.addAttribute("message", result);

        return "helloworld";

    }


}
