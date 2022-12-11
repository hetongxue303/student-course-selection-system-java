package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.bo.UserBO;
import com.hetongxue.system.domain.vo.QueryVO;
import com.hetongxue.system.mapper.UserMapper;
import com.hetongxue.system.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User selectOneByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserBO> getUserListAll(Integer type) {
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

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> getUserAll() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getIsDelete, false);
        wrapper.orderByAsc(User::getUserId);
        return userMapper.selectList(wrapper);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public QueryVO getUserPage(Integer currentPage, Integer pageSize, User query) {
        ArrayList<UserBO> userBO = new ArrayList<>();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(query.getUsername())) {
            wrapper.like(User::getUsername, query.getUsername());
        }
        if (Objects.nonNull(query.getIsEnable())) {
            wrapper.eq(User::getIsEnable, query.getIsEnable());
        }
        if (Objects.nonNull(query.getType())) {
            wrapper.eq(User::getType, query.getType());
        }
        wrapper.eq(User::getIsDelete, false);
        wrapper.orderByAsc(User::getUserId);
        Page<User> list = userMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);

        Optional.ofNullable(list.getRecords()).orElse(new ArrayList<>()).forEach(item -> {
            UserBO bo = new UserBO();
            BeanUtils.copyProperties(item, bo);
            userBO.add(bo);
        });

        return new QueryVO(list.getCurrent(), list.getSize(), list.getTotal(), list.getPages(), userBO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user) > 0 ? userMapper.insertUserRole(user.getUserId(), Long.valueOf(user.getType())) : 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delUser(Long id) {
        return userMapper.updateById(new User().setUserId(id).setIsDelete(true));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delBatchUser(List<Long> ids) {
        try {
            ids.forEach(id -> {
                userMapper.updateById(new User().setUserId(id).setIsDelete(true));
            });
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

}