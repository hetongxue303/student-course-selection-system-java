package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 课程模块
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @GetMapping("/get/all")
    @LogAnnotation(operate = "获取所有课程列表")
    public Result getCourseAll() {
        return Result.Success(courseService.getCourseAll()).setMessage("query all of list success");
    }

    @GetMapping("/get/page")
    @LogAnnotation(operate = "分页获取课程列表")
    public Result getCollegePage(Integer currentPage, Integer pageSize, String name) {
        return Result.Success(courseService.getCoursePage(currentPage, pageSize, name)).setMessage("query pagination list success");
    }

}