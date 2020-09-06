package com.example.demo.dao;

import com.example.demo.model.MenusLeft;
import com.example.demo.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.BitSet;
import java.util.List;

@Mapper
public interface RoleDao {
    List<Role> roleChildrens();
    boolean deleteRoleById(@Param("roleId") int roleId, @Param("rightsId") int rightsId);
    Role roleChildrensByRid(int id);
    boolean AddRoleAndRights(@Param("roleId") int roleId,@Param("rids") int rids);
    boolean delectRole (int roleId);
    boolean deleteRoleAll(int roleId);
    boolean updateStatus(int roleId);
    Role findRoleById(int id);
    boolean updateRole(@Param("id")Integer id, @Param("roleName")String roleName, @Param("roleDesc")String roleDesc);
    void addRole(@Param("role")Role role);
    int selectMaxId();
    List<MenusLeft> getLeftmenus(int id);
}
