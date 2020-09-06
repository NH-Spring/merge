package com.example.demo.model;

import java.util.List;

public class RightsTwo {
    private String authName;
    private int id;
    private List<Rights> childrenRigths;

    @Override
    public String toString() {
        return "RightsTwo{" +
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

    public void setChildrenRigths(List<Rights> childrenRigths) {
        this.childrenRigths = childrenRigths;
    }

    public String getAuthName() {
        return authName;
    }

    public int getId() {
        return id;
    }

    public List<Rights> getChildrenRigths() {
        return childrenRigths;
    }
}
