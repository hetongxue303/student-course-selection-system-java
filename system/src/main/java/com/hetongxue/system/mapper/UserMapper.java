package com.hetongxue.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hetongxue.system.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户Mapper
 *
 * @author 何同学
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过账户ID获取用户信息
     *
     * @param accountID 账户ID
     * @return com.hetongxue.system.domain.User
     */
    @Select("select * from sys_user where user_id in (select user_id from sys_account_user where account_id = #{accountID})")
    User getUserByAccountID(@Param("accountID") Long accountID);

}