package com.example.demo.model;

import java.util.logging.Level;

public class Rights {
    private String authName;
    private int id;
    private String level;
    private int pid;

    @Override
    public String toString() {
        return "Rights{" +
                "authName='" + authName + '\'' +
                ", id=" + id +
                ", level='" + level + '\'' +
                ", pid=" + pid +
                '}';
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getAuthName() {
        return authName;
    }

    public int getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public int getPid() {
        return pid;
    }
}
