package com.example.demo.controller;

import com.example.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Emailcontroller {
    @Autowired
    private MailService mailService;

    //接收邮箱地址
    private String to = "2830100967@qq.com";
    @GetMapping("/toEmail")
    public void sendSimpleMail() {
        mailService.sendSimpleMail(to, "主题：系统找回密码邮件", "找回密码链接：");
        System.out.println("修改密码邮件已发送");
    }
}
