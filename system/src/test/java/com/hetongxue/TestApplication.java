package com.hetongxue;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.bo.MenuBO;
import com.hetongxue.system.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 测试类
 *
 * @author 何同学
 */
@SpringBootTest
public class TestApplication {

    @Resource
    private MenuMapper menuMapper;


    List<MenuBO> getList(List<Menu> menuList, Long pid) {
        List<MenuBO> menus = new ArrayList<>();
        // 判断是否为空
        Optional.ofNullable(menuList)
                // 不为空时创建新的数组
                .orElse(new ArrayList<>())
                // 转换流
                .stream()
                // 过滤:不为空 类型不是按钮 等于父ID
                .filter(item -> Objects.nonNull(item) && Objects.equals(item.getParentId(), pid))
                // 循环遍历
                .forEach(item -> {
                    // 如果你的菜单标题为空 则只需要拿它对应的子菜单
                    if (Objects.nonNull(item.getMenuTitle())) {
                        MenuBO bo = new MenuBO();
                        BeanUtils.copyProperties(item, bo);
                        menus.add(bo.setChildren(getList(menuList, item.getMenuId())));
                    } else {
                        getList(menuList, item.getMenuId()).stream().filter(Objects::nonNull).forEach(menus::add);
                    }
                });
        return menus;
    }

    @Test
    void test() {
        // 查询目录
        List<Menu> list = menuMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getIsDelete, false));
        getList(list, 0L).forEach(System.out::println);

    }
}