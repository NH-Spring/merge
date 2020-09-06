package com.example.demo.model;

import java.util.List;

public class RightsOne {
    private String authName;
    private int id;
    private List<RightsTwo> childrenRigths;

    @Override
    public String toString() {
        return "RightsOne{" +
                "authName='" + authName + '\'' +
                ", id=" + id +
                ", childrenRigths=" + childrenRigths +
                '}';
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChildrenRigths(List<RightsTwo> childrenRigths) {
        this.childrenRigths = childrenRigths;
    }

    public String getAuthName() {
        return authName;
    }

    public int getId() {
        return id;
    }

    public List<RightsTwo> getChildrenRigths() {
        return childrenRigths;
    }
}


