package com.example.demo.model;

public class MenuClassChildren {
    private int id;
    private String menuClassName;
    private boolean deleteStatus;
    private int level;
    private int pid;

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPid() {
        return pid;
    }
    @Override
    public String toString() {
        return "MenuClassChildren{" +
                "id=" + id +
                ", menuClassName='" + menuClassName + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", level=" + level +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMenuClassName(String menuClassName) {
        this.menuClassName = menuClassName;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getMenuClassName() {
        return menuClassName;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public int getLevel() {
        return level;
    }
}
