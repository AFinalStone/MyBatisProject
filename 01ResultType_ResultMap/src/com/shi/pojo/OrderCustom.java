package com.shi.pojo;

import com.shi.bean.Order;

public class OrderCustom extends Order {
    private String username;
    private char sex;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString()+"OrderCustom{" +
                "username='" + username + '\'' +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                '}';
    }
}