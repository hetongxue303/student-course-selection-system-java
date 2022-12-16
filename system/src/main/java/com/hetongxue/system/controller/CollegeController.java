package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.domain.College;
import com.hetongxue.system.service.CollegeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/get/all")
    @LogAnnotation(operate = "获取所有学院列表")
    public Result getCollegeAll() {
        return Result.Success(collegeService.getCollegeAll()).setMessage("query all of list success");
    }

    @GetMapping("/get/page")
    @LogAnnotation(operate = "分页获取学院列表")
    public Result getCollegePage(Integer currentPage, Integer pageSize, College query) {
        return Result.Success(collegeService.getCollegePage(currentPage, pageSize, query)).setMessage("query pagination list success");

    }

    @PostMapping("/insert")
    @LogAnnotation(operate = "新增学院")
    public Result addCollege(@RequestBody College college) {
        System.out.println("college = " + college);
        return collegeService.addCollege(college) > 0 ? Result.Success().setMessage("insert success") : Result.Error().setMessage("insert fail");
    }

    @DeleteMapping("/delete/{id}")
    @LogAnnotation(operate = "删除学院")
    public Result delCollege(@PathVariable("id") Long id) {
        return collegeService.delCollege(id) > 0 ? Result.Success().setMessage("delete success") : Result.Error().setMessage("delete fail");
    }

    @DeleteMapping("/delete/batch")
    @LogAnnotation(operate = "批量删除学院")
    public Result delBatchCollege(@RequestBody List<Long> ids) {
        return collegeService.delBatchCollege(ids) > 0 ? Result.Success().setMessage("batch delete success") : Result.Error().setMessage("batch delete fail");

    }

    @PutMapping("/update")
    @LogAnnotation(operate = "更新学院")
    public Result updateCollege(@RequestBody College college) {
        return collegeService.updateCollege(college) > 0 ? Result.Success().setMessage("update success") : Result.Error().setMessage("update fail");
    }

}
