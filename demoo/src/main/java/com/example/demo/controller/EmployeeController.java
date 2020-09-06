package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.until.JwtUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    EmployeeService employeeIpml;
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmployeeList(Integer pagenum, String query, Integer pagesize, @RequestHeader MultiValueMap<String, String> headers) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<String> token =  headers.get("authorization");
        String tokens = token.get(0);
        Claims claims = jwtUtils.parseJWT(tokens);
        int shopId = (Integer) claims.get("shopId");
        PageHelper.startPage(pagenum,pagesize);
        List<Employee> employeeList = employeeIpml.selectAll(query, shopId);
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employeeList);
/*        employeeList.forEach(x -> System.out.println(x));
        System.out.println(pagenum+pagesize);*/
        map.put("employeeList",employeeList);
        map.put("status", "200");
        map.put("total", pageInfo.getTotal());
        return map;
    }
    @RequestMapping(value = "/employeeStatus", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> employeeStatus(Integer id, boolean status) {
        Map<String, Object> map = new HashMap<>();
        if (!employeeIpml.updateEmployeeStatus(id,status)) {
            map.put("status", "400");
            return map;
        }
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addEmployee(@RequestBody String req, @RequestHeader MultiValueMap<String, String> headers) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<String> token =  headers.get("authorization");
        String tokens = token.get(0);
        Claims claims = jwtUtils.parseJWT(tokens);
        int shopId = (Integer) claims.get("shopId");

        if(!employeeIpml.addEmployee(req,shopId))
        {
            map.put("status", "500");
            return map;
        }
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/employeeById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> FindEmployeeById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(id);
        map.put("data",employeeIpml.FindEmployeeById(id));
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateEmployee(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if (!employeeIpml.updateEmployee(req))
            return map;
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteEmployee (@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if (!employeeIpml.deleteEmployee(req))
            return map;
        map.put("status", "200");
        return map;
    }
}
