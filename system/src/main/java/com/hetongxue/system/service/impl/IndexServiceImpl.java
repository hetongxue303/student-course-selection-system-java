package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hetongxue.system.domain.Course;
import com.hetongxue.system.domain.Major;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.vo.IndexVo;
import com.hetongxue.system.mapper.CourseMapper;
import com.hetongxue.system.mapper.MajorMapper;
import com.hetongxue.system.mapper.UserMapper;
import com.hetongxue.system.service.IndexService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 何同学
 */
@Transactional
@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private MajorMapper majorMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public IndexVo getIndexInfo() {
        int teacherCount = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getIsDelete, false).eq(User::getType, 1)).size();
        int studentCount = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getIsDelete, false).eq(User::getType, 2)).size();
        int courseCount = courseMapper.selectList(new LambdaQueryWrapper<Course>().eq(Course::getIsDelete, false)).size();
        int majorCount = majorMapper.selectList(new LambdaQueryWrapper<Major>().eq(Major::getIsDelete, false)).size();
        return new IndexVo().setTeacherCount(teacherCount).setStudentCount(studentCount).setCourseCount(courseCount).setMajorCount(majorCount);
    }
}