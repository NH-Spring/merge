package com.example.demo.dao;

import com.example.demo.model.Code;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeDao {
    void insertCode(Code code);
    void deleteCode(String username);
    Code selectName(String code);
}
