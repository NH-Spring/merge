package com.example.demo.service;

import com.example.demo.model.Menus;

import java.util.List;

public interface MenusService {
    void addMenus(String req);
    List<Menus> findAll(String req);
    boolean deleteMens(String req);
    Menus findById(int id);
    boolean updatemenus(String req);
}
