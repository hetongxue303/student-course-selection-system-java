package com.hetongxue;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hetongxue.system.domain.College;
import com.hetongxue.system.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

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
        List<College> list = collegeMapper.selectList(new LambdaQueryWrapper<College>().eq(College::getIsDelete, true));
        list.forEach(item -> {
            System.out.println(item.getIsDelete());
        });

    }

}