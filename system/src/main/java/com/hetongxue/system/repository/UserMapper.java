package com.hetongxue.system.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hetongxue.system.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 用户Mapper
 *
 * @author 何同学
 */
@Repository
//@Mapper
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

    @Select("select max(user_id) from sys_user")
    Integer ttt();

}