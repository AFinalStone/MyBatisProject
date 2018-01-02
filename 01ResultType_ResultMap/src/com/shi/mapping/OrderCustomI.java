package com.shi.mapping;

import com.shi.bean.Order;
import com.shi.pojo.OrderCustom;
import com.shi.pojo.OrderResultMap;
import com.shi.pojo.OrderResultMap02;
import com.shi.pojo.UserResultMap;

import java.util.List;

public interface OrderCustomI {
    public List<Order> findAllOrdersByResultType();
    public List<OrderCustom> findOrdersByResultTypeUser();
    public List<OrderResultMap> findOrdersByResultMapOrder();
    public List<OrderResultMap02> findOrdersByResultMapOrder02();
    public List<UserResultMap> findOrdersByResultMapUser();
}



