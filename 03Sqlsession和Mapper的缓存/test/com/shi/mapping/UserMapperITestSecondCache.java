package com.shi.mapping;

import com.shi.bean.User;
import com.shi.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 测试二级缓存
 * */
public class UserMapperITestSecondCache {


    @Test
    public void testCache() {
        //这里初次请求从数据库获取数据，并把数据插入到sqlSession的缓存中
        SqlSession sqlSession01 = MyBatisUtil.getSqlSession();
        SqlSession sqlSession02 = MyBatisUtil.getSqlSession();
        SqlSession sqlSession03 = MyBatisUtil.getSqlSession();
        UserMapperI userMapperI01 = sqlSession01.getMapper(UserMapperI.class);
        User mUser01  = userMapperI01.findUserById(2);
        System.out.println("第一次"+mUser01.toString());
        //这里执行关闭操作，将sqlsession的数据缓存到二级缓存中
        sqlSession01.close();

        UserMapperI userMapperI02 = sqlSession02.getMapper(UserMapperI.class);
        User mUser02 = userMapperI02.findUserById(2);
        System.out.println("第二次"+mUser01.toString());
//        sqlSession02.commit();
        sqlSession02.close();
//
//        UserMapperI userMapperI03 = sqlSession03.getMapper(UserMapperI.class);
//        User mUser03 = userMapperI03.findUserById(2);
//        System.out.println("第三次"+mUser01.toString());
//        sqlSession03.close();
    }

}