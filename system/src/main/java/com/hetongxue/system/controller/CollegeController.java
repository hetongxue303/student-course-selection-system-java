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

    @GetMapping("/getAll")
    @LogAnnotation(operate = "获取所有学院列表")
    public Result getCollegeAll() {
        return Result.Success(collegeService.getCollegeAll()).setMessage("query all of list success");
    }

    @GetMapping("/getPage")
    @LogAnnotation(operate = "分页获取学院列表")
    public Result getCollegePage(Integer page, Integer size, String name) {
        return Result.Success(collegeService.getCollegePage(page, size, name)).setMessage("query pagination list success");

    }

    @PostMapping("/add")
    @LogAnnotation(operate = "新增学院")
    public Result addCollege(@RequestBody College college) {
        return collegeService.addCollege(college) > 0 ? Result.Success().setMessage("insert success") : Result.Error().setMessage("insert fail");
    }

    @DeleteMapping("/del/{id}")
    @LogAnnotation(operate = "删除学院")
    public Result delCollege(@PathVariable("id") Long id) {
        return collegeService.delCollege(id) > 0 ? Result.Success().setMessage("delete success") : Result.Error().setMessage("delele fail");
    }

    @DeleteMapping("/update")
    @LogAnnotation(operate = "更新学院")
    public Result updateCollege(@RequestBody College college) {
        return collegeService.updateCollege(college) > 0 ? Result.Success().setMessage("update success") : Result.Error().setMessage("update fail");
    }
}
