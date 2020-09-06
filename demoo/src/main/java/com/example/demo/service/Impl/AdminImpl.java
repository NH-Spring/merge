package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.AdminDao;
import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdminImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Override
    public List<Admin> selectAll(String query) {
        List <Admin> adminList = adminDao.selectAll(query);
        return adminList;
    }

    @Override
    public boolean updateAdminStatus(int id, boolean status) {
        return adminDao.updateAdminStatus(id,status);
    }

    @Override
    public boolean addAdmin(String req) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Admin admin = JSONObject.parseObject(req, Admin.class);
        admin.setAddTime(date);
        return adminDao.addAdmin(admin);
    }

    @Override
    public Admin FindAdminById(int id) {
        if (adminDao.FindAdminById(id) !=null)
            return adminDao.FindAdminById(id);
        return null;
    }

    @Override
    public boolean updateAdmin(String req) {
        Admin admin = JSONObject.parseObject(req, Admin.class);
        return adminDao.updateAdmin(admin);
    }

    @Override
    public boolean deleteAdmin(String req) {
        Admin admin = JSONObject.parseObject(req, Admin.class);
        return adminDao.deleteAdmin(admin.getId());
    }

    @Override
    public boolean updateAdminRoleId(String req) {
        JSONObject obj = (JSONObject) JSON.parse(req);
        Integer id = (Integer) obj.get("id");
        Integer rid = (Integer) obj.get("rid");
        return adminDao.updateAdminRoleId(id, rid);
    }
}
