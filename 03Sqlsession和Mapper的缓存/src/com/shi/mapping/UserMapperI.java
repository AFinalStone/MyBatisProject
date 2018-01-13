package com.shi.mapping;

import com.shi.bean.User;

import java.util.List;

public interface UserMapperI {
    public int updateUser(User user);
    public User findUserById(int id);
}



