package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Course;
import com.hetongxue.system.domain.vo.QueryVo;
import com.hetongxue.system.mapper.CourseMapper;
import com.hetongxue.system.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 课程业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> getCourseAll() {
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Course::getIsDelete, false);
        return courseMapper.selectList(wrapper);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public QueryVo getCoursePage(Integer currentPage, Integer pageSize, String name) {
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(name)) {
            wrapper.like(Course::getCourseName, name);
        }
        wrapper.eq(Course::getIsDelete, false);
        wrapper.orderByAsc(Course::getCourseId);
        Page<Course> list = courseMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);
        return new QueryVo(list.getCurrent(), list.getSize(), list.getTotal(), list.getPages(), list.getRecords());
    }

}