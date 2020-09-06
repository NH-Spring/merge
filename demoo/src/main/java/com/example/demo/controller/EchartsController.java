package com.example.demo.controller;

import com.example.demo.dao.ShopDao;
import com.example.demo.model.EchartsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EchartsController {
    @Autowired
    ShopDao shopDao;
    @RequestMapping(value = "/getEcha", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEcha(){
        Map<String, Object> map = new HashMap<>();
        List<EchartsModel> echartsModels = shopDao.findEcharts();
        List<Integer> num = new ArrayList<>();
        List<String> time = new ArrayList<>();
        echartsModels.forEach(x->{
            num.add(x.getNums());
            time.add(x.getAddtime());
                }
                );
        map.put("time", time);
        map.put("num", num);
        map.put("status", "200");
        return map;
    }
}
