package com.example.demo.service;

import com.example.demo.dao.CodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
    @Autowired
    CodeDao codeDao;
    @Async
    public void deleteCode(String username) {
        try {
            Thread.sleep(30000);
            codeDao.deleteCode(username);
            System.out.println("删除code");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
