package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 账户模块
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @GetMapping("/getAccountAll")
    @LogAnnotation(operate = "获取所有账户列表")
    public Result getAccountAll() {
        return Result.Success(accountService.getAccountAll()).setMessage("查询账户信息成功");
    }

}
