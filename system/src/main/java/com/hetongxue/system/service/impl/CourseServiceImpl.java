package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.system.domain.Course;
import com.hetongxue.system.domain.bo.CourseBO;
import com.hetongxue.system.domain.vo.QueryVO;
import com.hetongxue.system.mapper.CourseMapper;
import com.hetongxue.system.mapper.UserMapper;
import com.hetongxue.system.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> getCourseAll() {
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Course::getIsDelete, false);
        return courseMapper.selectList(wrapper);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public QueryVO getCoursePage(Integer currentPage, Integer pageSize, String name) {
        List<CourseBO> courseBO = new ArrayList<>();
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(name)) {
            wrapper.like(Course::getCourseName, name);
        }
        wrapper.eq(Course::getIsDelete, false);
        wrapper.orderByAsc(Course::getCourseId);
        Page<Course> list = courseMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);

        if (SecurityUtils.getUser().getIsAdmin()) {
            Optional.ofNullable(list.getRecords()).orElse(new ArrayList<>()).forEach(course -> {
                CourseBO bo = new CourseBO();
                BeanUtils.copyProperties(course, bo);
                courseBO.add(bo);
            });
            return new QueryVO(list.getCurrent(), list.getSize(), list.getTotal(), list.getPages(), courseBO);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCourse(Course course) {
        return courseMapper.insert(course);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delCourse(Long id) {
        return courseMapper.updateById(new Course().setCourseId(id).setIsDelete(true));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delBatchCourse(List<Long> ids) {
        try {
            ids.forEach(id -> {
                courseMapper.updateById(new Course().setCourseId(id).setIsDelete(true));
            });
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCourse(Course course) {
        return courseMapper.updateById(course);
    }

}