package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.bo.MenuBO;
import com.hetongxue.system.mapper.MenuMapper;
import com.hetongxue.system.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 菜单业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Menu> selectMenuListByUserId(Long userId) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.inSql(Menu::getMenuId, "select menu_id from sys_role_menu where role_id in (select role_id from sys_user_role where user_id = " + userId + ")");
        wrapper.orderByAsc(Menu::getSort);
        return menuMapper.selectList(wrapper);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<MenuBO> selectMenuListToTable(Long parentId) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getIsDelete, false);
        return getMenuListToTable(menuMapper.selectList(wrapper), parentId);
    }

    List<MenuBO> getMenuListToTable(List<Menu> menuList, Long parentId) {
        List<MenuBO> menus = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                //
                .stream()
                //
                .filter(item -> Objects.nonNull(item) && Objects.equals(item.getParentId(), parentId))
                //
                .forEach(item -> {
                    if (Objects.nonNull(item.getMenuTitle())) {
                        MenuBO bo = new MenuBO();
                        BeanUtils.copyProperties(item, bo);
                        List<Menu> collect = menuList.stream().filter(sub -> Objects.equals(sub.getParentId(), parentId) && Objects.nonNull(sub.getComponent())).collect(Collectors.toList());
                        menus.add(bo.setHasChildren(collect.size() > 0));
                    } else {
                        getMenuListToTable(menuList, item.getMenuId()).stream().filter(Objects::nonNull).forEach(menus::add);
                    }
                });
        return menus;
    }

}