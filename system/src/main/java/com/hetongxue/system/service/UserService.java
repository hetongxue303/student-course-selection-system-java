package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.User;

/**
 * 用户业务
 *
 * @author 何同学
 */
public interface UserService extends IService<User> {

    /**
     * 按用户名查询用户
     *
     * @param username 用户名
     * @return User
     */
    User selectOneByUsername(String username);

}