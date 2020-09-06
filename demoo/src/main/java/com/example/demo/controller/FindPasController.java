package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.AdminDao;
import com.example.demo.dao.CodeDao;
import com.example.demo.model.Code;
import com.example.demo.service.DeleteService;
import com.example.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.util.Length;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class FindPasController {
    @Autowired
    DeleteService deleteService;
    @Autowired
    CodeDao codeDao;
    @Autowired
    private MailService mailService;
    @Autowired
    AdminDao adminDao;
    @ResponseBody
    @RequestMapping(value = "/findPas", method = RequestMethod.POST)
    public Map<String, Object> findPas(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        JSONObject obj = (JSONObject) JSON.parse(req);
        String username = (String) obj.get("username");
        if (adminDao.findPas(username) == null) {
            return map;
        }
        String uuid = UUID.randomUUID().toString().substring(UUID.randomUUID().toString().length()-6);
        Code code = new Code();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        code.setAddtime(date);
        code.setCodeString(uuid);
        code.setUsername(username);
        codeDao.insertCode(code);
        mailService.sendSimpleMail(adminDao.findPas(username).getEmail(), "主题：系统找回密码邮件", uuid);
        deleteService.deleteCode(username);
        map.put("status", "200");
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/updatePas", method = RequestMethod.POST)
    public Map<String, Object> updatePas(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        JSONObject obj = (JSONObject) JSON.parse(req);
        String password = (String) obj.get("password");
        String codeString = (String) obj.get("codeString");
        if (codeDao.selectName(codeString) == null) {
            return map;
        }
        String username = codeDao.selectName(codeString).getUsername();
        adminDao.updatePas(username, password);
        map.put("status", "200");
        return map;
    }
}
