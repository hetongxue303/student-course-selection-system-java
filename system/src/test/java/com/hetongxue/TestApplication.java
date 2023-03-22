package com.hetongxue;

import com.hetongxue.system.repository.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 测试类
 *
 * @author 何同学
 */
@SpringBootTest(classes = Application.class)
public class TestApplication {

    @Resource
    private UserMapper UserMapper;


    @Test
    void test() {
        Integer ttt = UserMapper.ttt();
        System.out.println(ttt);
    }
}