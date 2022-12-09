package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.mapper.MenuMapper;
import com.hetongxue.system.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

}