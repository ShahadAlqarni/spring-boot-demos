package com.example.crudemo.dao;

import com.example.crudemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //we do not need any code

}
