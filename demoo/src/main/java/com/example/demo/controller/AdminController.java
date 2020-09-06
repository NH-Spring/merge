package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;
import com.example.demo.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    AdminService adminIpml;
    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAdminList(Integer pagenum, String query, Integer pagesize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(pagenum,pagesize);
        List<Admin> adminList = adminIpml.selectAll(query);
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(adminList);

        map.put("adminList",adminList);
        map.put("status", "200");
        map.put("total", pageInfo.getTotal());
        return map;
    }
    @RequestMapping(value = "/adminStatus", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> adminStatus(Integer id, boolean status) {
        Map<String, Object> map = new HashMap<>();
        if (!adminIpml.updateAdminStatus(id,status)) {
            map.put("status", "400");
            return map;
        }
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addAdmin(@RequestBody String req) {
        System.out.println(req);
        Map<String, Object> map = new HashMap<>();
        if(!adminIpml.addAdmin(req))
        {
            map.put("status", "500");
            return map;
        }
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/adminById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> FindAdminById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("data",adminIpml.FindAdminById(id));
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateAdmin(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if (!adminIpml.updateAdmin(req))
            return map;
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteAdmin (@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if (!adminIpml.deleteAdmin(req))
            return map;
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/updateAdminRoleId", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateAdminRoleId(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if(adminIpml.updateAdminRoleId(req)){
            map.put("status", "200");
        }
        return map;
    }
}
