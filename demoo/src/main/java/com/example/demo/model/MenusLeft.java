package com.example.demo.model;

import java.util.List;

public class MenusLeft {
    private String authName;
    private String path;
    private List<MenusLeftChildren> menusLeftChildren;

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMenusLeftChildren(List<MenusLeftChildren> menusLeftChildren) {
        this.menusLeftChildren = menusLeftChildren;
    }

    public String getAuthName() {
        return authName;
    }

    public String getPath() {
        return path;
    }

    public List<MenusLeftChildren> getMenusLeftChildren() {
        return menusLeftChildren;
    }

    @Override
    public String toString() {
        return "MenusLeft{" +
                "authName='" + authName + '\'' +
                ", path='" + path + '\'' +
                ", menusLeftChildren=" + menusLeftChildren +
                '}';
    }


}
