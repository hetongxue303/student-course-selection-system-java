package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Role;

import java.util.List;

/**
 * 角色业务
 *
 * @author 何同学
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据账户ID获取角色列表
     */
    List<Role> selectRoleByAccountId(Long accountID);

}