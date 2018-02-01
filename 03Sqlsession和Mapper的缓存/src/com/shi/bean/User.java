package com.shi.bean;

import java.io.Serializable;

/**
 * @author AfinalStone
 * users表所对应的实体类
 */
public class User implements Serializable{
    private static final long serialVersionUID = -8143828116053212870L;
    //实体类的属性和表的字段名称一一对应
    private int id;
    private String userName;
    private char sex;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                '}';
    }
}