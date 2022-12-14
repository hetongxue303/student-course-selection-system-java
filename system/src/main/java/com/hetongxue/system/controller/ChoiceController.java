package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.domain.Choice;
import com.hetongxue.system.service.ChoiceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 选课模块
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/choice")
public class ChoiceController {

    @Resource
    private ChoiceService choiceService;


    @GetMapping("/get/all")
    @LogAnnotation(operate = "获取所有选课记录列表")
    public Result getChoiceAll() {
        return Result.Success(choiceService.getChoiceAll()).setMessage("query all of list success");
    }

    @GetMapping("/get/page")
    @LogAnnotation(operate = "分页获取选课记录列表")
    public Result getChoicePage(Integer currentPage, Integer pageSize, Choice query) {
        return Result.Success(choiceService.getChoicePage(currentPage, pageSize, query)).setMessage("query pagination" + " " + "list" + " success");

    }

    @PostMapping("/insert")
    @LogAnnotation(operate = "新增选课记录")
    public Result addChoice(@RequestBody Choice choice) {
        return choiceService.addChoice(choice) > 0 ? Result.Success().setMessage("insert success") : Result.Error().setMessage("insert fail");
    }

    @DeleteMapping("/delete/{id}")
    @LogAnnotation(operate = "删除选课记录")
    public Result delChoice(@PathVariable("id") Long id) {
        return choiceService.delChoice(id) > 0 ? Result.Success().setMessage("delete success") : Result.Error().setMessage("delete fail");
    }

    @DeleteMapping("/delete/batch")
    @LogAnnotation(operate = "批量删除选课记录")
    public Result delBatchChoice(@RequestBody List<Long> ids) {
        return choiceService.delBatchChoice(ids) > 0 ? Result.Success().setMessage("batch delete success") : Result.Error().setMessage("batch delete fail");

    }

    @PutMapping("/update")
    @LogAnnotation(operate = "更新选课记录")
    public Result updateChoice(@RequestBody Choice choice) {
        return choiceService.updateChoice(choice) > 0 ? Result.Success().setMessage("update success") : Result.Error().setMessage("update fail");
    }

    @PutMapping("/course")
    @LogAnnotation(operate = "学生选课/退课")
    public Result studentChoiceCourse(Integer type, Long courseId) {
        return choiceService.studentChoiceCourse(type, courseId) > 0 ? Result.Success() : Result.Error();
    }

}