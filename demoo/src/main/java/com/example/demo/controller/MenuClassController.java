package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.model.MenuClass;
import com.example.demo.service.MenuClassService;
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
public class MenuClassController {
    @Autowired
    MenuClassService menuClassImpl;
    @RequestMapping(value = "/getmenuClassList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getmenuClassList() {
        Map<String, Object> map = new HashMap<>();
        //PageHelper.startPage(pagenum,pagesize);
        List<MenuClass> menuClassList = menuClassImpl.findAll();
        //PageInfo<MenuClass> pageInfo = new PageInfo<MenuClass>(menuClassList);

        map.put("menuClassList",menuClassList);
        map.put("status", "200");
        map.put("total", menuClassImpl.pidSize());
        return map;
    }
    @RequestMapping(value = "/getparent", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getParentList() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", menuClassImpl.getParent());
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/addMenuClass", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addMenuClass(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();

        if (menuClassImpl.addMenuClass(req)){
            map.put("status", "200");
            return map;
        }
        return map;
    }
    @RequestMapping(value = "/deleteMenuClass", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteMenuClass(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        menuClassImpl.deleteMenuClass(req);
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/updateMenuClass", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateMenuClass(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if (menuClassImpl.updateMenuClass(req)) {
            map.put("status", "200");
            return map;
        }
        return map;
    }
}

