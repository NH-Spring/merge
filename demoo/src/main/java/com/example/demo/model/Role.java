package com.example.demo.model;

import java.util.List;

public class Role {
    private int id;
    private String roleName;
    private String roleDesc;
    private List<RightsOne> childrenRigths;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", childrenRigths=" + childrenRigths +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public void setChildrenRigths(List<RightsOne> childrenRigths) {
        this.childrenRigths = childrenRigths;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public List<RightsOne> getChildrenRigths() {
        return childrenRigths;
    }
}
