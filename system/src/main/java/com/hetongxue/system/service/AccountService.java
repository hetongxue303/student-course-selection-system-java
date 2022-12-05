package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Account;

import java.util.List;

/**
 * 账户业务
 *
 * @author 何同学
 */
public interface AccountService extends IService<Account> {

    /**
     * 根据用户名查询账户信息
     *
     * @param username 用户名
     * @return com.hetongxue.system.domain.Account
     */
    Account selectOneByUsername(String username);

    /**
     * 获取账户列表 - All
     *
     * @return java.util.List<com.hetongxue.system.domain.Account>
     */
    List<Account> getAccountAll();

}