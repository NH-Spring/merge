package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.Rights;
import com.example.demo.service.RightsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RightsController {
    @Autowired
    RightsService rightsIpml;
    @RequestMapping(value = "/rights", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRights(Integer pagenum, String query, Integer pagesize) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(pagenum,pagesize);
        List<Rights> rightsList = rightsIpml.selectAll();
        PageInfo<Rights> pageInfo = new PageInfo<Rights>(rightsList);

        map.put("status", "200");
        map.put("data", rightsList);
        map.put("total", pageInfo.getTotal());
        return map;
    }
    @RequestMapping(value = "/rightsTree", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRightsTree() {
        Map<String, Object> map = new HashMap<>();
        List<Rights> rightsList = rightsIpml.getRightsTree();
        map.put("status", "200");
        map.put("rightsList", rightsList);
        return map;
    }

}
