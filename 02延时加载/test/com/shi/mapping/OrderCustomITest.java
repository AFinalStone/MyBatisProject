package com.shi.mapping;

import com.shi.bean.User;
import com.shi.mybatis.MyBatisUtil;
import com.shi.pojo.OrderResultMap;
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
    public void findOrdersUserLazyLoading() {
        OrderCustomI orderCustomI = sqlSession.getMapper(OrderCustomI.class);
        List<OrderResultMap> listData = orderCustomI.findOrdersUserLazyLoading();
        for (OrderResultMap data:listData) {
            System.out.println(data.toString());
        }
    }

}