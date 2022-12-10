package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.bo.UserBO;
import com.hetongxue.system.mapper.UserMapper;
import com.hetongxue.system.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 用户业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User selectOneByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public List<UserBO> selectUserListAll(Integer type) {
        ArrayList<UserBO> userBO = new ArrayList<>();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getIsDelete, false);
        wrapper.eq(User::getType, type);
        wrapper.orderByAsc(User::getUserId);
        List<User> users = userMapper.selectList(wrapper);
        Optional.ofNullable(users).orElse(new ArrayList<>()).forEach(user -> {
            UserBO bo = new UserBO();
            BeanUtils.copyProperties(user, bo);
            userBO.add(bo);
        });
        return userBO;
    }

}