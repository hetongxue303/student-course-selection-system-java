package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.system.domain.Major;
import com.hetongxue.system.service.MajorService;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/getAll")
    @LogAnnotation(operate = "获取所有专业列表")
    public Result getMajorAll() {
        return Result.Success(majorService.getMajorAll()).setMessage("query all of list success");
    }

    @GetMapping("/getPage")
    @LogAnnotation(operate = "分页获取专业列表")
    public Result getMajorPage(Integer page, Integer size, String name) {
        return Result.Success(majorService.getMajorPage(page, size, name)).setMessage("query pagination list success");

    }

    @PostMapping("/add")
    @LogAnnotation(operate = "新增专业")
    public Result addMajor(@RequestBody Major major) {
        return majorService.addMajor(major) > 0 ? Result.Success().setMessage("insert success") : Result.Error().setMessage("insert fail");
    }

    @DeleteMapping("/del/{id}")
    @LogAnnotation(operate = "删除专业")
    public Result delMajor(@PathVariable("id") Long id) {
        return majorService.delMajor(id) > 0 ? Result.Success().setMessage("delete success") : Result.Error().setMessage("delele fail");
    }

    @DeleteMapping("/update")
    @LogAnnotation(operate = "更新专业")
    public Result updateMajor(@RequestBody Major major) {
        return majorService.updateMajor(major) > 0 ? Result.Success().setMessage("update success") : Result.Error().setMessage("update fail");
    }
}