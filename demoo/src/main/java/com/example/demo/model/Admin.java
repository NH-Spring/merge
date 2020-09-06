package com.example.demo.model;

public class Admin {
    private int id;
    private String addTime;
    private boolean deleteStatus;
    private String userName;
    private String password;
    private String email;
    private int roleId;
    private int shopId;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", addTime='" + addTime + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roleId=" + roleId +
                ", shopId=" + shopId +
                '}';
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleId() {
        return roleId;
    }
}
