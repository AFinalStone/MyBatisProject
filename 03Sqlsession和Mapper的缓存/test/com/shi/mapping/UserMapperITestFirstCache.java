package com.shi.mapping;

import com.shi.bean.User;
import com.shi.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 测试一级缓存
 * */
public class UserMapperITestFirstCache {

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
    public void testCache() {
        //这里初次请求从数据库获取数据，并把数据插入到sqlSession的缓存中
        UserMapperI userMapperI = sqlSession.getMapper(UserMapperI.class);
        User mUser = userMapperI.findUserById(2);
        System.out.println(mUser.toString());
        //由于之前的请求把数据插入到了缓存中，这里从缓存中获取数据
        User mUser2 = userMapperI.findUserById(2);
        System.out.println("第二次："+mUser2.toString());

    }

    @Test
    public void testCacheByCommit() {
        //这里初次请求从数据库获取数据，并把数据插入到sqlSession的缓存中
        UserMapperI userMapperI = sqlSession.getMapper(UserMapperI.class);
        User mUser = userMapperI.findUserById(2);
        System.out.println(mUser.toString());
        //这里主动调用sqlSession的commit方法，清空当前sqlSession的缓存
        sqlSession.commit();
        //由于调用了commit清空了数据缓存，这里请求的数据是从数据库获取的
        User mUser2 = userMapperI.findUserById(2);
        System.out.println("第二次"+mUser2.toString());
    }
}