package com.shi.pojo;

import com.shi.bean.Item;
import com.shi.bean.OrderDetail;

public class UserOrderDetail extends OrderDetail{
    private Item listData;

    public Item getListData() {
        return listData;
    }

    public void setListData(Item listData) {
        this.listData = listData;
    }

    @Override
    public String toString() {
        return "UserOrderDetail{" +
                "listData=" + listData +
                '}';
    }
}
