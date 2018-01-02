package com.shi.pojo;

import com.shi.bean.Order;
import com.shi.bean.User;

public class OrderResultMap extends Order {

    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return super.toString()+"OrderResultMap{" +
                "user=" + user +
                '}';
    }
}