package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    EmployeeDao employeeDao;
    @Override
    public List<Employee> selectAll(String name, int shopId) {
        List <Employee> employeeList = employeeDao.selectAll(name, shopId);
        return employeeList;
    }

    @Override
    public boolean updateEmployeeStatus(int id, boolean status) {
        return employeeDao.updateEmployeeStatus(id,status);
    }

    @Override
    public boolean addEmployee(String req,int shopId) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Employee employee = JSONObject.parseObject(req, Employee.class);
        employee.setAddTime(date);
        employee.setShopId(shopId);
        return employeeDao.addEmployee(employee);
    }

    @Override
    public Employee FindEmployeeById(int id) {
        if (employeeDao.FindEmployeeById(id) !=null)
            return employeeDao.FindEmployeeById(id);
        return null;
    }

    @Override
    public boolean updateEmployee(String req) {
        Employee employee = JSONObject.parseObject(req, Employee.class);
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(String req) {
        Employee employee = JSONObject.parseObject(req, Employee.class);
        return employeeDao.deleteEmployee(employee.getId());
    }
}
