package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    List<Employee> selectAll(String name, int shopId);
    boolean updateEmployeeStatus(int id, boolean status);
    boolean addEmployee(String req,int shopId);
    Employee FindEmployeeById(int id);
    boolean updateEmployee(String req);
    boolean deleteEmployee(String req);
}
