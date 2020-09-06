package com.example.demo.model;

public class MenusLeftChildren {
    private String authName;
    private String path;

    @Override
    public String toString() {
        return "MenusLeftChildren{" +
                "authName='" + authName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAuthName() {
        return authName;
    }

    public String getPath() {
        return path;
    }
}
