package com.example.demo.service.Impl;

import com.example.demo.dao.RightsDao;
import com.example.demo.model.Rights;
import com.example.demo.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightsImpl implements RightsService {
    @Autowired
    RightsDao rightsDao;
    @Override
    public List<Rights> selectAll() {
        return rightsDao.selectAll();
    }

    @Override
    public List<Rights> getRightsTree() {
        return rightsDao.getRightsTree();
    }
}
