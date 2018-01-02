package com.shi.pojo;

import com.shi.bean.Order;

import java.util.List;

public class UserOrder extends Order {
    private List<UserOrderDetail> listData;

    public List<UserOrderDetail> getListData() {
        return listData;
    }

    public void setListData(List<UserOrderDetail> listData) {
        this.listData = listData;
    }
}
