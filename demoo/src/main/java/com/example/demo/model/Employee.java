package com.example.demo.model;

public class Employee {
    private int id;
    private String name;
    private String addTime;
    private boolean deleteStatus;
    private int shopId;
    private String position;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addTime='" + addTime + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", shopId=" + shopId +
                ", position='" + position + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddTime() {
        return addTime;
    }

    public boolean getDeleteStatus() {
        return deleteStatus;
    }

    public int getShopId() {
        return shopId;
    }

    public String getPosition() {
        return position;
    }
}
