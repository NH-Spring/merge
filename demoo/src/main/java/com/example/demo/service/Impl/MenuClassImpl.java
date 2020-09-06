package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.MenuClassDao;
import com.example.demo.model.Employee;
import com.example.demo.model.MenuClass;
import com.example.demo.service.MenuClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuClassImpl implements MenuClassService {
    @Autowired
    MenuClassDao menuClassDao;
    @Override
    public List<MenuClass> findAll() {

        return menuClassDao.findAll();
    }

    @Override
    public int pidSize() {
        return menuClassDao.pidSize();
    }

    @Override
    public List<MenuClass> getParent() {
        return menuClassDao.getparent();
    }

    @Override
    public boolean addMenuClass(String req) {
        MenuClass menuClass = JSONObject.parseObject(req, MenuClass.class);
        menuClass.setDeleteStatus(true);
        return menuClassDao.addMenuClass(menuClass);
    }

    @Override
    public boolean deleteMenuClass(String req) {
        JSONObject obj = (JSONObject) JSON.parse(req);
        int id = (Integer) obj.get("id");
        menuClassDao.deleteMenuClass(id);
        menuClassDao.deleteMenuClassByPid(id);
        return true;
    }

    @Override
    public boolean updateMenuClass(String req) {
        MenuClass menuClass = JSONObject.parseObject(req, MenuClass.class);
        return menuClassDao.updateMenuClass(menuClass);
    }
}
