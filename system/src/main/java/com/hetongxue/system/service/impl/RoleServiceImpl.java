package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.domain.vo.QueryVO;
import com.hetongxue.system.mapper.RoleMapper;
import com.hetongxue.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> selectRoleListByUserId(Long userId) {
        return roleMapper.selectList(new LambdaQueryWrapper<Role>().inSql(Role::getRoleId, "select role_id from sys_user_role where user_id = " + userId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> getRoleAll() {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getIsDelete, false);
        wrapper.orderByAsc(Role::getRoleId);
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
        Page<Role> list = roleMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);

        List<Role> colleges = Optional.ofNullable(list.getRecords()).orElse(new ArrayList<>());

        return new QueryVO(list.getCurrent(), list.getSize(), list.getTotal(), list.getPages(), colleges);
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
        return roleMapper.updateById(role);
    }

}