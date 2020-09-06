package com.example.demo.service;

import com.example.demo.model.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> selectAll(String qurey);
    boolean updateAdminStatus(int id, boolean status);
    boolean addAdmin(String req);
    Admin FindAdminById(int id);
    boolean updateAdmin(String req);
    boolean deleteAdmin(String req);
    boolean updateAdminRoleId(String req);
}
