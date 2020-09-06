package com.example.demo.service;

import com.example.demo.model.Rights;

import java.util.List;

public interface RightsService {
    List<Rights> selectAll();
    List<Rights> getRightsTree();
}
