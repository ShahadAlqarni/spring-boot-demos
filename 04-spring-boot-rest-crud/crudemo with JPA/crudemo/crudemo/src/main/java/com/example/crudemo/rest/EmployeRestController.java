package com.example.crudemo.rest;

import com.example.crudemo.entity.Employee;
import com.example.crudemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeRestController {
    private final ObjectMapper objectMapper;
    private EmployeeService employeeService;

    // quick and dirty : inject employee dao
    @Autowired
    public EmployeRestController(EmployeeService employeeService, ObjectMapper theObjectMapper) {
        this.employeeService = employeeService;
        objectMapper = theObjectMapper;
    }

    //expose "/employee" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    //add mapping fie GET "/employees{employeeId}"
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee with id " + employeeId + " not found");
        }
        return theEmployee;
    }

    //add mapping fie POST "/employees"
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        Employee newEmployee = employeeService.save(theEmployee);
        return newEmployee;
    }

    //add mapping fie Put "/employees{employeeId}"
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee newEmployee = employeeService.save(theEmployee);
        return newEmployee;
    }

    //add mapping for Patch /"employees{employeeId}" - partial update
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String,Object> patchPayload) {
       Employee tempEmployee = employeeService.findById(employeeId);
       //throw exception when id = null
       if (tempEmployee == null) {
           throw new RuntimeException("Employee with id " + employeeId + " not found");
       }
        //throw exception when id continue in bode
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed to be updated");
        }
        Employee patchedEmployee = apply(patchPayload,tempEmployee);

        //save all new update at db
        Employee dbEmployee = employeeService.save(patchedEmployee);
        return dbEmployee;
    }

    private Employee apply(Map<String,Object> patchPayload,Employee tempEmployee) {
        //convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        //convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        // Merge the patch update into the employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

    //add mapping for delete "/employees{employeeId}"
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee deleteEmployee = employeeService.findById(employeeId);
        if (deleteEmployee == null) {
            throw new RuntimeException("Employee with id " + employeeId + " not found");
        }
        employeeService.deleteById(employeeId);

        return "Employee with id " + employeeId + " deleted";
    }
}
