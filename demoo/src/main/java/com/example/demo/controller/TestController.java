package com.example.demo.controller;

import com.example.demo.dao.TestDao;
import com.example.demo.model.Test;
import com.example.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLOutput;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    TestDao testDao;
    @Autowired
    private MailService mailService;
    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        List<Test> testList = testDao.selectAll();
        testList.forEach(x -> System.out.println(x));
        return "sss";
    }
    @ResponseBody
    @RequestMapping("/1")
    public String testSimpleMail() throws Exception {
        mailService.sendSimpleMail("2830100967@qq.com",
                "header",
                "错误！");
        return "ss";
    }
}
