package com.hetongxue;

import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.vo.UserVO;
import com.hetongxue.system.service.MenuService;
import com.hetongxue.system.service.RoleService;
import com.hetongxue.system.service.UserService;
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
    @Resource
    private RoleService roleService;
    @Resource
    private UserService userService;

    @Test
    void test() {
        User user = userService.selectOneByUsername("admin");
        UserVO userInfo = userService.getUserInfo(user);
        System.out.println("userInfo = " + userInfo);
        userInfo.getMenus().forEach(System.out::println);
        userInfo.getRouters().forEach(System.out::println);
    }
}