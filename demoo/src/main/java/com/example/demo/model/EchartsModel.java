package com.example.demo.model;

import java.util.List;

public class EchartsModel {
    private int nums;
    private String addtime;

    @Override
    public String toString() {
        return "EchartsModel{" +
                "nums=" + nums +
                ", addtime='" + addtime + '\'' +
                '}';
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
