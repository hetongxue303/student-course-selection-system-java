package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.service.MajorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 专业模块
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/major")
public class MajorController {

    @Resource
    private MajorService majorService;

    @GetMapping("/getMajorAll")
    @LogAnnotation(operate = "获取专业列表All")
    public Result getMajorAll() {
        return Result.Success(majorService.getMajorAll()).setMessage("获取专业列表成功");
    }
}