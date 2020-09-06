package com.example.demo.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.RoleDao;
import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleImpl implements RoleService {
    @Autowired
    RoleDao roleDao;
    @Override
    public List<Role> roleChildrens() {
        return roleDao.roleChildrens();
    }

    @Override
    public boolean deleteRoleById(String req) {
        JSONObject obj = (JSONObject) JSON.parse(req);
        Integer roleId = (Integer) obj.get("roleId");
        Integer rightsId = (Integer) obj.get("rightsId");
        if (roleDao.deleteRoleById(roleId, rightsId)){
            return true;
        }
        return false;
    }

    @Override
    public Role roleChildrensByRid(String req) {
        JSONObject obj = (JSONObject) JSON.parse(req);
        Integer roleId = (Integer) obj.get("roleId");
        return roleDao.roleChildrensByRid(roleId);
    }

    @Override
    public boolean AddRoleAndRights(String req) {
        JSONObject obj = (JSONObject) JSON.parse(req);
        Integer roleId = (Integer) obj.get("roleId");
        String ridStr = obj.getString("rids");
        String [] rids = ridStr.split(",");
        roleDao.delectRole(roleId);
        for (String rid : rids){
            roleDao.AddRoleAndRights(roleId, Integer.parseInt(rid));
        }
        return true;
    }

    @Override
    public boolean deleteRoleAll(String req) {
        JSONObject obj = (JSONObject) JSON.parse(req);
        Integer roleId = (Integer) obj.get("roleId");
        roleDao.delectRole(roleId);
        roleDao.delectRole(roleId);
        return false;
    }

    @Override
    public boolean updateStatus(String req) {
        JSONObject obj = (JSONObject) JSON.parse(req);
        Integer roleId = (Integer) obj.get("id");

        return roleDao.updateStatus(roleId);
    }

    @Override
    public Role findRoleById(int id) {
        return roleDao.findRoleById(id);
    }

    @Override
    public boolean updateRole(String req) {
        JSONObject obj = (JSONObject) JSON.parse(req);
        Integer id = (Integer) obj.get("id");
        String roleName = (String) obj.get("roleName");
        String roleDesc = (String) obj.get("roleDesc");
        System.out.println(roleDesc+roleName+id);
        return roleDao.updateRole(id, roleName, roleDesc);
    }

    @Override
    public boolean addRole(String req) {
        JSONObject obj = (JSONObject) JSON.parse(req);
        String roleName = (String) obj.get("roleName");
        String roleDesc = (String) obj.get("roleDesc");
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleDesc(roleDesc);
        roleDao.addRole(role);

        int id = roleDao.selectMaxId();
        for (int i=1;i<21;i++){
            roleDao.AddRoleAndRights(id,i);
        }
        return true;
    }

}
