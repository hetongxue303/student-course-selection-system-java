package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.domain.College;
import com.hetongxue.system.service.CollegeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 学院模块
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/college")
public class CollegeController {

    @Resource
    private CollegeService collegeService;

    @GetMapping("/getCollegeAll")
    @LogAnnotation(operate = "获取学院列表All")
    public Result getCollegeAll() {
        return Result.Success(collegeService.getCollegeAll()).setMessage("获取所有学院列表成功");
    }

    @GetMapping("/getCollegePage")
    @LogAnnotation(operate = "获取学院列表Page")
    public Result getCollegePage(Integer page, Integer size, String name) {
        return Result.Success(collegeService.getCollegePage(page, size, name)).setMessage("获取学院列表成功");

    }

    @PostMapping("/add")
    @LogAnnotation(operate = "新增学院")
    public Result addCollege(@RequestBody College college) {
        return collegeService.addCollege(college) ? Result.Success().setMessage("添加成功") : Result.Error().setMessage("添加失败");
    }

    @DeleteMapping("/del/{id}")
    public Result delCollege(@PathVariable("id") Long id) {
        return collegeService.delCollege(id) ? Result.Success().setMessage("删除成功") : Result.Error().setMessage("删除失败");
    }
}
