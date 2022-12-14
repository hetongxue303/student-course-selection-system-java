package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.RoleMenu;
import com.hetongxue.system.domain.bo.MenuBO;
import com.hetongxue.system.domain.vo.MenuTreeVO;
import com.hetongxue.system.domain.vo.QueryVO;
import com.hetongxue.system.mapper.MenuMapper;
import com.hetongxue.system.mapper.RoleMenuMapper;
import com.hetongxue.system.service.MenuService;
import com.hetongxue.utils.MenuFilterUtils;
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
    @Resource
    private RoleMenuMapper roleMenuMapper;

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
    public List<MenuTreeVO> selectMenuToTree(Long parentId) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getIsDelete, false);
        wrapper.orderByAsc(Menu::getSort);

        return MenuFilterUtils.filterMenuToMenuTree(menuMapper.selectList(wrapper), parentId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<MenuBO> selectMenuTableToTree(Menu query) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(query.getMenuTitle())) {
            wrapper.like(Menu::getMenuTitle, query.getMenuTitle());
        }
        wrapper.eq(Menu::getIsDelete, false);
        wrapper.orderByAsc(Menu::getSort);

        return MenuFilterUtils.filterMenuToMenuBO(menuMapper.selectList(wrapper), query.getParentId());
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public QueryVO selectMenuTreePage(Integer currentPage, Integer pageSize, Menu query) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(query.getMenuTitle())) {
            wrapper.like(Menu::getMenuTitle, query.getMenuTitle());
        }
        wrapper.eq(Menu::getIsDelete, false);
        Page<Menu> page = menuMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);

        return new QueryVO(page.getCurrent(), page.getSize(), page.getTotal(), page.getPages(), MenuFilterUtils.filterMenuToMenuBO(page.getRecords(), query.getParentId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addMenu(Menu menu) {
        return menuMapper.insert(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delMenu(Long id) {
        return menuMapper.updateById(new Menu().setMenuId(id).setIsDelete(true));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delBatchMenu(List<Long> ids) {
        try {
            ids.forEach(id -> {
                menuMapper.updateById(new Menu().setMenuId(id).setIsDelete(true));
            });
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMenu(Menu menu) {
        return menuMapper.updateById(menu);
    }

    @Override
    public List<MenuTreeVO> getMenuListByRoleId(Long roleId) {
        List<RoleMenu> list = roleMenuMapper.selectList(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
        List<Long> menuIds = Optional.ofNullable(list).orElse(new ArrayList<>()).stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<MenuTreeVO> result = new ArrayList<>();

        if (menuIds.size() > 0) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuId, menuIds);
            wrapper.orderByAsc(Menu::getSort);
            List<Menu> menuList = menuMapper.selectList(wrapper);
            Optional.ofNullable(menuList).orElse(new ArrayList<>()).stream().filter(Objects::nonNull).forEach(item -> {
                result.add(new MenuTreeVO(item.getMenuId(), item.getMenuTitle(), false, false, null));
            });
        }

        return result;
    }

}