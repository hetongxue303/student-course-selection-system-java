package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.mapper.UserMapper;
import com.hetongxue.system.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class StudentServiceImpl extends ServiceImpl<UserMapper, User> implements StudentService {
}