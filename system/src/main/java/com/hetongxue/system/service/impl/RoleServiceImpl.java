package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.handler.exception.DatabaseUpdateException;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.domain.RoleMenu;
import com.hetongxue.system.domain.UserRole;
import com.hetongxue.system.domain.vo.QueryVO;
import com.hetongxue.system.mapper.MenuMapper;
import com.hetongxue.system.mapper.RoleMapper;
import com.hetongxue.system.mapper.RoleMenuMapper;
import com.hetongxue.system.mapper.UserRoleMapper;
import com.hetongxue.system.service.RoleService;
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
 * 角色业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> selectRoleListByUserId(Long userId) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Role::getRoleId, selectRoleIdListByUserId(userId));
        return roleMapper.selectList(wrapper);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> getRoleAll() {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getIsDelete, false);
        wrapper.orderByAsc(Role::getLevel);
        return roleMapper.selectList(wrapper);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public QueryVO getRolePage(Integer currentPage, Integer pageSize, Role query) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(query.getRoleName())) {
            wrapper.like(Role::getRoleName, query.getRoleName());
        }
        wrapper.eq(Role::getIsDelete, false);
        wrapper.orderByAsc(Role::getRoleId);
        Page<Role> page = roleMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);

        ArrayList<Role> roles = new ArrayList<>();
        Optional.ofNullable(page.getRecords()).orElse(new ArrayList<>()).forEach(item -> {
            List<RoleMenu> menus = roleMenuMapper.selectList(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, item.getRoleId()));
            List<Long> menuIds = Optional.ofNullable(menus).orElse(new ArrayList<>()).stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
            if (menuIds.size() > 0) {
                LambdaQueryWrapper<Menu> menuWrapper = new LambdaQueryWrapper<>();
                menuWrapper.in(Menu::getMenuId, menuIds);
                menuWrapper.orderByAsc(Menu::getSort);
                item.setMenus(menuMapper.selectList(menuWrapper));
            }
            roles.add(item);
        });

        return new QueryVO(page.getCurrent(), page.getSize(), page.getTotal(), page.getPages(), roles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delRole(Long id) {
        return roleMapper.updateById(new Role().setRoleId(id).setIsDelete(true));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delBatchRole(List<Long> ids) {
        try {
            ids.forEach(id -> {
                roleMapper.updateById(new Role().setRoleId(id).setIsDelete(true));
            });
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(Role role) {
        if (roleMapper.selectList(new LambdaQueryWrapper<Role>().eq(Role::getLevel, role.getLevel())).size() > 0) {
            throw new DatabaseUpdateException("级别重复");
        }
        return roleMapper.updateById(role);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Long> selectRoleIdListByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        return userRoleMapper.selectList(wrapper).stream().map(UserRole::getRoleId).collect(Collectors.toList());
    }

}