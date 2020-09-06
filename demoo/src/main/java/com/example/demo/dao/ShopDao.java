package com.example.demo.dao;

import com.example.demo.model.EchartsModel;
import com.example.demo.model.Employee;
import com.example.demo.model.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopDao {
    List<Shop> shopList(String query);
    boolean updateShopStatus(int id, boolean status);
    Shop FindShopById(int id);
    boolean deleteShop(int id);
    boolean updateShop(Shop shop);
    boolean addShop(Shop shop);
    List<EchartsModel> findEcharts();
}
