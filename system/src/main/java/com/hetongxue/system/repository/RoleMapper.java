package com.hetongxue.system.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hetongxue.system.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 角色Mapper
 *
 * @author 何同学
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户ID查询角色信息
     *
     * @param UserId 用户ID
     * @return Long
     */
    @Select("select * from sys_user where role_id in (select role_id from sys_user_role where user_id = #{UserId})")
    Long selectRoleByUserId(@Param("UserId") Long UserId);

}