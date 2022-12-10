package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.domain.Course;
import com.hetongxue.system.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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


    @PostMapping("/insert")
    @LogAnnotation(operate = "新增课程")
    public Result addCollege(@RequestBody Course course) {
        return courseService.addCourse(course) > 0 ? Result.Success().setMessage("insert success") : Result.Error().setMessage("insert fail");
    }

    @DeleteMapping("/delete/{id}")
    @LogAnnotation(operate = "删除课程")
    public Result delCollege(@PathVariable("id") Long id) {
        return courseService.delCourse(id) > 0 ? Result.Success().setMessage("delete success") : Result.Error().setMessage("delete fail");
    }

    @DeleteMapping("/delete/batch")
    @LogAnnotation(operate = "批量删除课程")
    public Result delBatchCollege(@RequestBody List<Long> ids) {
        return courseService.delBatchCourse(ids) > 0 ? Result.Success().setMessage("batch delete success") : Result.Error().setMessage("batch delete fail");

    }

    @PutMapping("/update")
    @LogAnnotation(operate = "更新课程")
    public Result updateCollege(@RequestBody Course course) {
        return courseService.updateCourse(course) > 0 ? Result.Success().setMessage("update success") : Result.Error().setMessage("update fail");
    }

}