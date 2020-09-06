package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.service.LoginService;
import com.example.demo.until.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    LoginService loginImpl;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> FormLogin(@RequestBody String req){
        System.out.println(req);
        Map<String, Object> map = new HashMap<>();
        Admin admin = loginImpl.getRoleId(req);

        if (admin == null){
            return map;
        }
        String token = jwtUtils.createJWT(String.valueOf(admin.getId()),"登录者",-1,admin);
        map.put("status","200");
        map.put("token",token);
        return map;
    }
}
