package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> roleChildrens();
    boolean deleteRoleById(String req);
    Role roleChildrensByRid(String req);
    boolean AddRoleAndRights(String req);
    boolean deleteRoleAll(String req);
    boolean updateStatus(String req);
    Role findRoleById(int id);
    boolean updateRole(String req);
    boolean addRole(String req);

}
