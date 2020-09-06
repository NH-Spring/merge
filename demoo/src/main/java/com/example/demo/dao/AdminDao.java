package com.example.demo.dao;

import com.example.demo.model.Admin;
import com.example.demo.model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminDao {
    List<Admin> selectAll(String query);
    boolean updateAdminStatus(int id, boolean status);
    boolean addAdmin(Admin admin);
    Admin FindAdminById(int id);
    boolean updateAdmin(Admin admin);
    boolean deleteAdmin(int id);
    boolean updateAdminRoleId(@Param("id") int id, @Param("rid") int rid);
    Admin getRoleId(@Param("username") String username, @Param("password") String password);
    Admin findPas(String username);
    boolean updatePas(@Param("username")String username, @Param("password")String password);
}
