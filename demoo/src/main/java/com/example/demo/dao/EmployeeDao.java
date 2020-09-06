package com.example.demo.dao;

import com.example.demo.model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeDao {
    List<Employee> selectAll(@Param("name") String name, @Param("shopId") int id);
    boolean updateEmployeeStatus(int id, boolean status);
    boolean addEmployee(Employee employee);
    Employee FindEmployeeById(int id);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(int id);
}
