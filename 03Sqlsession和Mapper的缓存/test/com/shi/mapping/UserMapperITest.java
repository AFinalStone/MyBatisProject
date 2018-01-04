package com.shi.mapping;

import com.shi.bean.User;
import com.shi.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserMapperITest {

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
        List<User> listData = userMapperI.findUserById(2);
        for (User user : listData) {
            user.toString();
        }
        //由于之前的请求把数据插入到了缓存中，这里从缓存中获取数据
        List<User> listData2 = userMapperI.findUserById(2);
    }

    @Test
    public void testCacheByCommit() {
        //这里初次请求从数据库获取数据，并把数据插入到sqlSession的缓存中
        UserMapperI userMapperI = sqlSession.getMapper(UserMapperI.class);
        List<User> listData = userMapperI.findUserById(2);
        for (User user : listData) {
            user.toString();
        }
        //这里主动调用sqlSession的commit方法，清空当前sqlSession的缓存
        sqlSession.commit();
        //由于调用了commit清空了数据缓存，这里请求的数据是从数据库获取的
        List<User> listData2 = userMapperI.findUserById(2);
    }
}