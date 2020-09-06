package com.example.demo.model;

public class Shop {
    private int id;
    private String addTime;
    private boolean deleteStatus;
    private String address;
    private String shopName;

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", addTime='" + addTime + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", address='" + address + '\'' +
                ", shopName='" + shopName + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getId() {
        return id;
    }

    public String getAddTime() {
        return addTime;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public String getAddress() {
        return address;
    }

    public String getShopName() {
        return shopName;
    }
}
