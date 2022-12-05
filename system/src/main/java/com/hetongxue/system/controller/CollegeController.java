package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.service.CollegeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return Result.Success(collegeService.getCollegeAll()).setMessage("获取学院列表成功");
    }


}