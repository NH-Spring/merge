package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> shopList(String query);
    boolean updateShopStatus(int id, boolean status);
    Shop FindShopById(int id);
    boolean deleteShop(String req);
    boolean updateShop(String req);
    boolean addShop(String req);
}
