package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.bo.UserBO;
import com.hetongxue.system.domain.vo.QueryVO;
import com.hetongxue.system.domain.vo.UserVO;

import java.util.List;

/**
 * 用户业务
 *
 * @author 何同学
 */
public interface UserService extends IService<User> {

    /**
     * 按用户名查询用户
     *
     * @param username 用户名
     * @return User
     */
    User selectOneByUsername(String username);

    /**
     * 按类型查询用户信息
     *
     * @param type 用户类型
     * @return List
     */
    List<UserBO> getUserListAll(Integer type);

    /**
     * 获取所有用户列表
     *
     * @return List<User>
     */
    List<User> getUserAll();

    /**
     * 分页获取用户列表
     *
     * @param currentPage 当前页
     * @param query       查询信息
     * @return QueryVo
     */
    QueryVO getUserPage(Integer currentPage, Integer pageSize, User query);

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return int
     */
    int addUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return int
     */
    int delUser(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 用户ID
     * @return int
     */
    int delBatchUser(List<Long> ids);

    /**
     * 更新用户
     *
     * @param user 用户信息
     * @return int
     */
    int updateUser(User user);

    /**
     * 获取用户信息
     *
     * @param user 当前用户
     */
    UserVO getUserInfo(User user);
}