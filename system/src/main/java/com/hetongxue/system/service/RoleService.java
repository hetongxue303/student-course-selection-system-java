package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.domain.vo.QueryVO;

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

    /**
     * 获取所有角色列表
     *
     * @return List<Role>
     */
    List<Role> getRoleAll();

    /**
     * 分页获取角色列表
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param query       查询信息
     * @return QueryVo
     */
    QueryVO getRolePage(Integer currentPage, Integer pageSize, Role query);

    /**
     * 添加角色
     *
     * @param role 角色信息
     * @return int
     */
    int addRole(Role role);

    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return int
     */
    int delRole(Long id);

    /**
     * 批量删除角色
     *
     * @param ids 角色ID
     * @return int
     */
    int delBatchRole(List<Long> ids);

    /**
     * 更新角色
     *
     * @param role 角色信息
     * @return int
     */
    int updateRole(Role role);

    List<Long> selectRoleIdsByUserId(Long userId);

}