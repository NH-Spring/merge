package com.example.demo.service;

import com.example.demo.model.MenuClass;

import java.util.List;

public interface MenuClassService {
    List<MenuClass> findAll();
    int pidSize();
    List<MenuClass> getParent();
    boolean addMenuClass(String req);
    boolean deleteMenuClass(String req);
    boolean updateMenuClass(String req);
}
