package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色模块
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/getRoleAll")
    @LogAnnotation(operate = "获取角色列表All")
    public Result getRoleAll() {
        return Result.Success(roleService.getRoleAll()).setMessage("获取角色列表成功");
    }
}