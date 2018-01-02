package com.shi.mapping;

import com.shi.bean.Order;
import com.shi.bean.User;
import com.shi.pojo.OrderResultMap;

import java.util.List;

public interface OrderCustomI {
    public List<OrderResultMap> findOrdersUserLazyLoading();
    public List<User> findUserById(int id);
}



