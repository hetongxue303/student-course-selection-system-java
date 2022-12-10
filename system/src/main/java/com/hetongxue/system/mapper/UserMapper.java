package com.hetongxue.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hetongxue.system.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 用户Mapper
 *
 * @author 何同学
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 新增用户角色
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return int
     */
    @Insert("insert into sys_user_role set user_id = #{userId},role_id = #{roleId}")
    int insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

}