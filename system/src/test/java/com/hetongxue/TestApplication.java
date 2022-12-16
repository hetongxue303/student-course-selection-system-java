package com.hetongxue;

import com.hetongxue.system.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 测试类
 *
 * @author 何同学
 */
@SpringBootTest
public class TestApplication {

    @Resource
    private MenuService menuService;


    @Test
    void test() {

    }
}