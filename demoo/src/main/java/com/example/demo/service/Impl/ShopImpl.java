package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.ShopDao;
import com.example.demo.model.Employee;
import com.example.demo.model.Shop;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShopImpl implements ShopService {
    @Autowired
    ShopDao shopDao;
    @Override
    public List<Shop> shopList(String query) {
        return shopDao.shopList(query);
    }

    @Override
    public boolean updateShopStatus(int id, boolean status) {
        return shopDao.updateShopStatus(id, status);
    }

    @Override
    public Shop FindShopById(int id) {
        if (shopDao.FindShopById(id) !=null)
            return shopDao.FindShopById(id);
        return null;
    }

    @Override
    public boolean deleteShop(String req) {
        Shop shop = JSONObject.parseObject(req, Shop.class);
        return shopDao.deleteShop(shop.getId());
    }

    @Override
    public boolean updateShop(String req) {
        Shop shop = JSONObject.parseObject(req, Shop.class);
        return shopDao.updateShop(shop);
    }

    @Override
    public boolean addShop(String req) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Shop shop = JSONObject.parseObject(req, Shop.class);
        shop.setAddTime(date);
        return shopDao.addShop(shop);
    }
}
