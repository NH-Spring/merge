package com.example.demo.dao;

import com.example.demo.model.MenuClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuClassDao {
    List<MenuClass> findAll();
    int pidSize();
    List<MenuClass> getparent();
    boolean addMenuClass(MenuClass menuClass);
    boolean deleteMenuClass(int id);
    boolean deleteMenuClassByPid(int id);
    boolean updateMenuClass(MenuClass menuClass);
}
