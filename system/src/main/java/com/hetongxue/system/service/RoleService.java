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
     * 按用户ID查询角色列表
     *
     * @param userId 用户ID
     * @return List<Role>
     */
    List<Role> selectRoleListByUserId(Long userId);

}