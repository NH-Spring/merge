package com.example.demo.dao;

import com.example.demo.model.Employee;
import com.example.demo.model.Menus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenusDao {
    void addMenus (Menus menus);
    List<Menus> findAll(String req);
    boolean deleteMenus(int id);
    Menus FindmenusById(int id);
    boolean updatemenus(Menus menus);
}
