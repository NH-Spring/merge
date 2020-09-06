package com.example.demo.model;

public class Menus {
    private int id;
    private String menusName;
    private int menusClassId;
    private double menusPrice;
    private String menusPicture;
    private boolean deletestatus;
    private String introduce;

    @Override
    public String toString() {
        return "Menus{" +
                "id=" + id +
                ", menusName='" + menusName + '\'' +
                ", menusClassId=" + menusClassId +
                ", menusPrice=" + menusPrice +
                ", menusPicture='" + menusPicture + '\'' +
                ", deletestatus=" + deletestatus +
                ", introduce='" + introduce + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenusName() {
        return menusName;
    }

    public void setMenusName(String menusName) {
        this.menusName = menusName;
    }

    public int getMenusClassId() {
        return menusClassId;
    }

    public void setMenusClassId(int menusClassid) {
        this.menusClassId = menusClassid;
    }

    public double getMenusPrice() {
        return menusPrice;
    }

    public void setMenusPrice(double menusPrice) {
        this.menusPrice = menusPrice;
    }

    public String getMenusPicture() {
        return menusPicture;
    }

    public void setMenusPicture(String menusPicture) {
        this.menusPicture = menusPicture;
    }

    public boolean isDeletestatus() {
        return deletestatus;
    }

    public void setDeletestatus(boolean deletestatus) {
        this.deletestatus = deletestatus;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
