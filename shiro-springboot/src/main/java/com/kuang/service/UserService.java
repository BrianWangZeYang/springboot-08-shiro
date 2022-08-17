package com.kuang.service;

import com.kuang.pojo.User;

/**
 * @author xxx
 * @version 1.0
 * @Description
 * @date 2022/8/16 20:37
 */
public interface UserService {
    public User queryUserByName(String name);
}
