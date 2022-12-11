package com.hetongxue.system.controller;

import com.hetongxue.base.response.Result;
import com.hetongxue.system.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 菜单模块
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/table/{parentId}")
    public Result getMenuToTable(@PathVariable("parentId") Long parentId) {
        return Result.Success(menuService.selectMenuListToTable(parentId)).setMessage("query list of menu success");
    }
}