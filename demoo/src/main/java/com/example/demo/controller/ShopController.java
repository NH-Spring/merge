package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.Shop;
import com.example.demo.service.ShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShopController {
    @Autowired
    ShopService shopImpl;
    @RequestMapping(method = RequestMethod.GET, value = "/shops")
    @ResponseBody
    public Map<String, Object> getShopList(Integer pagenum, String query, Integer pagesize){
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(pagenum,pagesize);
        List<Shop> shopList = shopImpl.shopList(query);
        PageInfo<Shop> pageInfo = new PageInfo<Shop>(shopList);

        map.put("shopList",shopList);
        map.put("status", "200");
        map.put("total", pageInfo.getTotal());
        return map;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/shop")
    @ResponseBody
    public Map<String, Object> getShopListad(){
        Map<String, Object> map = new HashMap<>();

        List<Shop> shopList = shopImpl.shopList(null);


        map.put("shopList",shopList);
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/shopStatus", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> shopStatus(Integer id, boolean status) {
        Map<String, Object> map = new HashMap<>();
        if (!shopImpl.updateShopStatus(id,status)) {
            map.put("status", "400");
            return map;
        }
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/shopById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> FindShopById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("data",shopImpl.FindShopById(id));
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/deleteShop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteShop (@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if (!shopImpl.deleteShop(req))
            return map;
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/updateShop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateShop(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if (!shopImpl.updateShop(req))
            return map;
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/addShop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addShop(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if(!shopImpl.addShop(req))
        {
            map.put("status", "500");
            return map;
        }
        map.put("status", "200");
        return map;
    }
}
