package com.hetongxue;

import com.hetongxue.system.mapper.*;
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
    private AccountMapper accountMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private CollegeMapper collegeMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;

    @Test
    void test() {
        System.out.println(accountMapper.selectList(null));
        System.out.println(menuMapper.selectList(null));
        System.out.println(collegeMapper.selectList(null));
        System.out.println(majorMapper.selectList(null));
        System.out.println(roleMapper.selectList(null));
        System.out.println(userMapper.selectList(null));
    }

}