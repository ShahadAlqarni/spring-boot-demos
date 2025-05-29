package com.luv2code.springboot.thymeleafDemo.cntroller;

import com.luv2code.springboot.thymeleafDemo.entity.Employee;
import com.luv2code.springboot.thymeleafDemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    //constrctor injection
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel){
        //get the employees from db
        List<Employee> theEmployee = employeeService.findAll();

        //add to the spring model
        theModel.addAttribute("employees",theEmployee);

        return "employees/list-employees";
    }

    //add mapping for add new employee
    @GetMapping("/showFormForAdd")
    public String saveEmployees(Model theModel){

        //create model attribute to bind form data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){
        //get employee from service
        Employee theEmployee = employeeService.findById(theId);

        //set the employee in the model to prepopulate the form
        theModel.addAttribute("employee",theEmployee);

        //send over to our form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId){
        //delete the employee
        employeeService.deleteById(theId);

        //use a redirect to prevent duplicate information
        return "redirect:/employees/list";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        //save the employee
        employeeService.save(theEmployee);

        //use a redirect to prevent duplicate information
        return "redirect:/employees/list";
    }
}
