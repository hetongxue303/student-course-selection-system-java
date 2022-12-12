package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/tree")
    public Result getMenuToTree(Integer currentPage, Integer pageSize, Menu query) {
        return Result.Success(menuService.selectMenuTree(query)).setMessage("query list of menu success");
    }

    @PostMapping("/insert")
    @LogAnnotation(operate = "新增菜单")
    public Result addCollege(@RequestBody Menu Menu) {
        return menuService.addMenu(Menu) > 0 ? Result.Success().setMessage("insert success") : Result.Error().setMessage("insert fail");
    }

    @DeleteMapping("/delete/{id}")
    @LogAnnotation(operate = "删除菜单")
    public Result delCollege(@PathVariable("id") Long id) {
        return menuService.delMenu(id) > 0 ? Result.Success().setMessage("delete success") : Result.Error().setMessage("delete fail");
    }

    @DeleteMapping("/delete/batch")
    @LogAnnotation(operate = "批量删除菜单")
    public Result delBatchCollege(@RequestBody List<Long> ids) {
        return menuService.delBatchMenu(ids) > 0 ? Result.Success().setMessage("batch delete success") : Result.Error().setMessage("batch delete fail");

    }

    @PutMapping("/update")
    @LogAnnotation(operate = "更新菜单")
    public Result updateCollege(@RequestBody Menu Menu) {
        return menuService.updateMenu(Menu) > 0 ? Result.Success().setMessage("update success") : Result.Error().setMessage("update fail");
    }
}