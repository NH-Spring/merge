package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.AdminDao;
import com.example.demo.dao.RoleDao;
import com.example.demo.model.Admin;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginImpl implements LoginService {
    @Autowired
    AdminDao adminDao;
    @Override
    public Admin getRoleId(String req) {
        JSONObject obj = (JSONObject) JSON.parse(req);
        String username = (String) obj.get("username");
        String password = (String) obj.get("password");
        return adminDao.getRoleId(username, password);
    }
}
