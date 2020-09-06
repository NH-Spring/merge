package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.MenusDao;
import com.example.demo.model.Admin;
import com.example.demo.model.MenuClass;
import com.example.demo.model.Menus;
import com.example.demo.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

@Service
public class MenusImpl implements MenusService {
    @Autowired
    MenusDao menusDao;
    @Override
    public void addMenus(String req) {
        Menus menus = new Menus();
        JSONObject obj = (JSONObject) JSON.parse(req);
        String menusName = (String) obj.get("menusName");
        String introduce = (String) obj.get("introduce");
        String menusPicture = (String) obj.get("menusPicture");
        String menusPrices = (String) obj.get("menusPrice");
        double menusPrice = Double.parseDouble(menusPrices);
        int menusClassId = (int) obj.get("menusClassId");
        menus.setDeletestatus(true);
        menus.setIntroduce(introduce);
        menus.setMenusClassId(menusClassId);
        menus.setMenusPicture(menusPicture);
        menus.setMenusPrice(menusPrice);
        menus.setMenusName(menusName);
        menusDao.addMenus(menus);
    }

    @Override
    public List<Menus> findAll(String req) {
        return menusDao.findAll(req);
    }

    @Override
    public boolean deleteMens(String req) {
        Menus menus = JSONObject.parseObject(req, Menus.class);
        return menusDao.deleteMenus(menus.getId());
    }

    @Override
    public Menus findById(int id) {
        if (menusDao.FindmenusById(id) !=null)
            return menusDao.FindmenusById(id);
        return null;
    }

    @Override
    public boolean updatemenus(String req) {
        Menus menus = JSONObject.parseObject(req, Menus.class);
        return menusDao.updatemenus(menus);
    }
}
