package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.service.ChoiceService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @PutMapping("/course")
    @LogAnnotation(operate = "学生选课/退课")
    public Result studentChoiceCourse(Integer type, Long courseId) {
        return choiceService.studentChoiceCourse(type, courseId) > 0 ? Result.Success() : Result.Error();
    }

}