package com.shi.pojo;

import com.shi.bean.Order;
import com.shi.bean.OrderDetail;
import com.shi.bean.User;

import java.util.List;

public class UserResultMap extends User {
    List<UserOrder> listData;

    public List<UserOrder> getListData() {
        return listData;
    }

    public void setListData(List<UserOrder> listData) {
        this.listData = listData;
    }
}