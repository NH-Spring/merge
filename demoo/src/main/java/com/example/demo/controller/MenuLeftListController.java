package com.example.demo.controller;

import com.example.demo.dao.RoleDao;
import com.example.demo.model.MenusLeft;
import com.example.demo.model.MenusLeftChildren;
import com.example.demo.until.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MenuLeftListController {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RoleDao roleDao;
    @RequestMapping(value = "/menusLeft", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMenusLeft(@RequestHeader MultiValueMap<String, String> headers) throws Exception {
        List<String> token =  headers.get("authorization");
        System.out.println(token);
        String tokens = token.get(0);
        Claims claims = jwtUtils.parseJWT(tokens);
        int roleId = (Integer) claims.get("roleId");
        /*System.out.println(token.size());
        String tokens = token.get(0);
        Integer roleId = Integer.parseInt(String.valueOf(tokens.charAt(tokens.length()-2)));*/
        System.out.println(roleId);
        List<MenusLeft> menusLeftList =  roleDao.getLeftmenus(roleId);
       Map<String, Object> map = new HashMap<>();
       /* MenusLeftChildren menusLeftChildren = new MenusLeftChildren();
        menusLeftChildren.setAuthName("职工列表");
        menusLeftChildren.setPath("employees");
        MenusLeftChildren menusLeftChildrens = new MenusLeftChildren();
        menusLeftChildrens.setAuthName("管理员");
        menusLeftChildrens.setPath("admins");

        List<MenusLeftChildren> menusLeftChildrenList = new ArrayList<>();
        menusLeftChildrenList.add(menusLeftChildren);
        menusLeftChildrenList.add(menusLeftChildrens);

        MenusLeft menusLeft = new MenusLeft();
        menusLeft.setAuthName("职员管理");
        menusLeft.setPath("employee");
        menusLeft.setMenusLeftChildren(menusLeftChildrenList);



        MenusLeft menusLeftsp = new MenusLeft();
        MenusLeftChildren menusLeftChildrenp = new MenusLeftChildren();
        menusLeftChildrenp.setAuthName("权限列表");
        menusLeftChildrenp.setPath("rights");
        MenusLeftChildren menusLeftChildrenps = new MenusLeftChildren();
        menusLeftChildrenps.setAuthName("角色列表");
        menusLeftChildrenps.setPath("role");
        List<MenusLeftChildren> menusLeftChildrenLists = new ArrayList<>();
        menusLeftChildrenLists.add(menusLeftChildrenp);
        menusLeftChildrenLists.add(menusLeftChildrenps);
        menusLeftsp.setAuthName("权限管理");
        menusLeftsp.setPath("power");
        menusLeftsp.setMenusLeftChildren(menusLeftChildrenLists);

        MenusLeft menusLeftspshop = new MenusLeft();
        menusLeftspshop.setAuthName("店铺管理");
        menusLeftspshop.setPath("shop");
        MenusLeftChildren menusLeftChildrenpshop = new MenusLeftChildren();
        menusLeftChildrenpshop.setAuthName("店铺列表");
        menusLeftChildrenpshop.setPath("shops");
        List<MenusLeftChildren> menusLeftChildrenListshop = new ArrayList<>();
        menusLeftChildrenListshop.add(menusLeftChildrenpshop);
        menusLeftspshop.setMenusLeftChildren(menusLeftChildrenListshop);


        MenusLeft menusLeftspshopfood = new MenusLeft();
        menusLeftspshopfood.setAuthName("菜单管理");
        menusLeftspshopfood.setPath("menu");
        MenusLeftChildren menusLeftChildrenpshopfood = new MenusLeftChildren();
        menusLeftChildrenpshopfood.setAuthName("菜单表");
        menusLeftChildrenpshopfood.setPath("menus");
        MenusLeftChildren menusLeftChildrenpshopfoodj = new MenusLeftChildren();
        menusLeftChildrenpshopfoodj.setAuthName("菜单分类");
        menusLeftChildrenpshopfoodj.setPath("menuClass");
        List<MenusLeftChildren> menusLeftChildrenListshopfood = new ArrayList<>();
        menusLeftChildrenListshopfood.add(menusLeftChildrenpshopfood);
        menusLeftChildrenListshopfood.add(menusLeftChildrenpshopfoodj);
        menusLeftspshopfood.setMenusLeftChildren(menusLeftChildrenListshopfood);

        MenusLeft menusLeftspshopfoodww = new MenusLeft();
        menusLeftspshopfoodww.setAuthName("数据统计");
        menusLeftspshopfoodww.setPath("statistics");
        MenusLeftChildren menusLeftChildrenpshopfoodww = new MenusLeftChildren();
        menusLeftChildrenpshopfoodww.setAuthName("统计表");
        menusLeftChildrenpshopfoodww.setPath("Chart");
        List<MenusLeftChildren> menusLeftChildrenListshopfoodww = new ArrayList<>();
        menusLeftChildrenListshopfoodww.add(menusLeftChildrenpshopfoodww);
        menusLeftspshopfoodww.setMenusLeftChildren(menusLeftChildrenListshopfoodww);

        List<MenusLeft> menusLeftList = new ArrayList<>();
        menusLeftList.add(menusLeft);
        menusLeftList.add(menusLeftsp);
        menusLeftList.add(menusLeftspshop);
        menusLeftList.add(menusLeftspshopfood);
        menusLeftList.add(menusLeftspshopfoodww);*/
        map.put("menusLeftlist", menusLeftList);
        map.put("status", "200");
//        menusLeftChildrenList.forEach(x -> {
//            System.out.println(x);
//        });
//        menusLeftList.forEach(x -> {
//            System.out.println(x);
//        });
        return map;
    }
}
