package com.example.crudemo.service;

import com.example.crudemo.dao.EmployeeRepository;
import com.example.crudemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeDAO) {
        employeeRepository = theEmployeeDAO;
    }
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    //افهم هذ الميثود كويس
    //find id optinal for me
    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee employee = null;
        if (result.isPresent()) {
            employee = result.get();
        } else {
           // we didn't find the employee
            throw new RuntimeException("Employee not found");
        }

        return employee;
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
