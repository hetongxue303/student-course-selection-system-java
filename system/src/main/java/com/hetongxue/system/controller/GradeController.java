package com.hetongxue.system.controller;

import com.hetongxue.base.response.Result;
import com.hetongxue.system.domain.Grade;
import com.hetongxue.system.service.GradeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 班级控制器
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Resource
    private GradeService gradeService;

    @GetMapping("/get/all")
    public Result getGradeAll() {
        return Result.Success().setData(gradeService.getGradeAll());
    }

    @GetMapping("/get/page")
    public Result getGradePage(Integer currentPage, Integer pageSize, Grade query) {
        return Result.Success().setData(gradeService.getGradePage(currentPage, pageSize, query));
    }

    @PostMapping("/insert")
    public Result addGrade(@RequestBody Grade grade) {
        return gradeService.addGrade(grade) > 0 ? Result.Success().setMessage("insert success") : Result.Error().setMessage("insert fail");
    }

    @DeleteMapping("/delete/{id}")
    public Result delGrade(@PathVariable("id") Long id) {
        return gradeService.delGrade(id) > 0 ? Result.Success().setMessage("delete success") : Result.Error().setMessage("delete fail");
    }

    @DeleteMapping("/delete/batch")
    public Result delBatchGrade(@RequestBody List<Long> ids) {
        return gradeService.delBatchGrade(ids) > 0 ? Result.Success().setMessage("batch delete success") : Result.Error().setMessage("batch delete fail");

    }

    @PutMapping("/update")
    public Result updateGrade(@RequestBody Grade grade) {
        return gradeService.updateGrade(grade) > 0 ? Result.Success().setMessage("update success") : Result.Error().setMessage("update fail");
    }

}