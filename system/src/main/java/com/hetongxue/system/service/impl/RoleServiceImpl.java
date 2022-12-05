package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.mapper.RoleMapper;
import com.hetongxue.system.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public List<Role> selectRoleByAccountId(Long accountID) {
        return roleMapper.selectList(new QueryWrapper<Role>().inSql("role_id", "select distinct role_id from sys_account_role where account_id = " + accountID));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> getRoleAll() {
        return roleMapper.selectList(new LambdaQueryWrapper<Role>().orderByAsc(Role::getRoleId));
    }
    
}