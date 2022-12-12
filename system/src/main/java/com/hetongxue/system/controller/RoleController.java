package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/get/all")
    @LogAnnotation(operate = "获取所有角色列表")
    public Result getRoleAll() {
        return Result.Success(roleService.getRoleAll()).setMessage("query all of list success");
    }

    @GetMapping("/get/page")
    @LogAnnotation(operate = "分页获取角色列表")
    public Result getRolePage(Integer currentPage, Integer pageSize, Role query) {
        return Result.Success(roleService.getRolePage(currentPage, pageSize, query)).setMessage("query pagination " + "list" + " " + "success");

    }

    @PostMapping("/insert")
    @LogAnnotation(operate = "新增角色")
    public Result addRole(@RequestBody Role role) {
        return roleService.addRole(role) > 0 ? Result.Success().setMessage("insert success") : Result.Error().setMessage("insert fail");
    }

    @DeleteMapping("/delete/{id}")
    @LogAnnotation(operate = "删除角色")
    public Result delRole(@PathVariable("id") Long id) {
        return roleService.delRole(id) > 0 ? Result.Success().setMessage("delete success") : Result.Error().setMessage("delete fail");
    }

    @DeleteMapping("/delete/batch")
    @LogAnnotation(operate = "批量删除角色")
    public Result delBatchRole(@RequestBody List<Long> ids) {
        return roleService.delBatchRole(ids) > 0 ? Result.Success().setMessage("batch delete success") : Result.Error().setMessage("batch delete fail");

    }

    @PutMapping("/update")
    @LogAnnotation(operate = "更新角色")
    public Result updateRole(@RequestBody Role role) {
        return roleService.updateRole(role) > 0 ? Result.Success().setMessage("update success") : Result.Error().setMessage("update fail");
    }

}