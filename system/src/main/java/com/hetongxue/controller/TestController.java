package com.hetongxue.controller;

import com.hetongxue.base.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试模块
 *
 * @author 何同学
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public Result test() {
        return Result.Success().setMessage("hello  word!");
    }

}