package com.example.demo.dao;

import com.example.demo.model.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDao {
    List<Test> selectAll();
}
