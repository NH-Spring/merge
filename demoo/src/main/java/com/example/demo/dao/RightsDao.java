package com.example.demo.dao;

import com.example.demo.model.Rights;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RightsDao {
    List<Rights> selectAll();
    List<Rights> getRightsTree();
}
