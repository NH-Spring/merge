package com.example.demo.controller;

import com.example.demo.model.Menus;
import com.example.demo.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Controller
public class MenusController {
    @Autowired
    MenusService menusImpl;
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map<String, Object> imageUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
//目前这里是写死的本地硬盘路径
        String path = "D:/img";

        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR)) + String.valueOf((currTime.get(Calendar.MONTH) + 1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));

        suffix = suffix.toLowerCase();
        if (suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png")/* || suffix.equals(".gif")*/) {
            fileName = UUID.randomUUID().toString() + suffix;
            File targetFile = new File(path, fileName);
            if (!targetFile.getParentFile().exists()) {    //注意，判断父级路径是否存在
                targetFile.getParentFile().mkdirs();
            }
            long size = 0;
            //保存
            try {
                file.transferTo(targetFile);
                size = file.getSize();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("上传失败！");
            }
            //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；
            String fileUrl = "http://localhost:8081";
            //文件获取路径
            fileUrl = fileUrl + request.getContextPath() + "/img/" + fileName;
            map.put("path", fileUrl);
            map.put("status", "200");
            return map;

        } else {
            //return R.error("图片格式有误，请上传.jpg、.png、.jpeg格式的文件");

            map.put("msg", "格式错误");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/addmenus", method = RequestMethod.POST)
    public Map<String, Object> addmenus(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        menusImpl.addMenus(req);
        map.put("status", "200");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/getMenus", method = RequestMethod.GET)
    public Map<String, Object> getMenus(Integer pagenum, String query, Integer pagesize) {
        Map<String, Object> map = new HashMap<>();
        List<Menus> menusList = menusImpl.findAll(query);
        map.put("status", "200");
        map.put("menusList", menusList);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/deletemenu", method = RequestMethod.POST)
    public Map<String, Object> deletemenu(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if (!menusImpl.deleteMens(req))
            return map;
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/menusByid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> menusByid(Integer id) {
        Map<String, Object> map = new HashMap<>();

        map.put("data",menusImpl.findById(id));
        map.put("status", "200");
        return map;
    }
    @RequestMapping(value = "/updatemenus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updatemenus(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if (!menusImpl.updatemenus(req))
            return map;
        map.put("status", "200");
        return map;
    }
}
