package com.shi.pojo;

import com.shi.bean.Order;
import com.shi.bean.OrderDetail;
import com.shi.bean.User;

import java.util.List;

public class OrderResultMap02 extends Order {

    User user;
    List<OrderDetail> listData;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetail> getListData() {
        return listData;
    }

    public void setListData(List<OrderDetail> listData) {
        this.listData = listData;
    }

    @Override
    public String toString() {
        return super.toString() + "OrderResultMap02{" +
                "user=" + user +
                ", listData=" + listData +
                '}';
    }
}