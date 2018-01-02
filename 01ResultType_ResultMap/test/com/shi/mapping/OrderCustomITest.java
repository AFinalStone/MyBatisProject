package com.shi.mapping;

import com.shi.bean.Order;
import com.shi.bean.OrderDetail;
import com.shi.mybatis.MyBatisUtil;
import com.shi.pojo.*;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class OrderCustomITest {

    SqlSession sqlSession;
    @Before
    public void setUp() throws Exception {
        sqlSession = MyBatisUtil.getSqlSession();
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.close();
    }

    @Test
    public void findAllOrdersByResultType() {
        OrderCustomI orderCustomI = sqlSession.getMapper(OrderCustomI.class);
        List<Order> listData = orderCustomI.findAllOrdersByResultType();
        for (Order data:listData) {
            System.out.println(data.toString());
        }
    }

    @Test
    public void findOrdersByResultTypeUser(){
        OrderCustomI orderCustomI = sqlSession.getMapper(OrderCustomI.class);
        List<OrderCustom> listData = orderCustomI.findOrdersByResultTypeUser();
        for (OrderCustom data:listData) {
            System.out.println(data.toString());
        }
    }

    @Test
    public void findOrdersByResultMapOrder(){
        OrderCustomI orderCustomI = sqlSession.getMapper(OrderCustomI.class);
        List<OrderResultMap> listData = orderCustomI.findOrdersByResultMapOrder();
        for (OrderResultMap data:listData) {
            System.out.println(data.toString());
        }
    }

    @Test
    public void findOrdersByResultMapOrder02(){
        OrderCustomI orderCustomI = sqlSession.getMapper(OrderCustomI.class);
        List<OrderResultMap02> listData = orderCustomI.findOrdersByResultMapOrder02();
        for (OrderResultMap02 data:listData) {
            for (OrderDetail orderDetail:data.getListData()){
                System.out.println(orderDetail.toString());
            }
            System.out.println(data.toString());
        }
    }

    @Test
    public void findOrdersByResultMapUser(){
        OrderCustomI orderCustomI = sqlSession.getMapper(OrderCustomI.class);
        List<UserResultMap> listData = orderCustomI.findOrdersByResultMapUser();
        for (UserResultMap data:listData) {
            System.out.println(data.toString());
            for (UserOrder userOrder:data.getListData()){
                System.out.println(userOrder.toString());
                for ( UserOrderDetail userOrderDetail: userOrder.getListData() ) {
                    System.out.println(userOrderDetail.toString());
                }
            }
        }
    }
}