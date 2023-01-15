package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.system.domain.Choice;
import com.hetongxue.system.domain.Course;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.vo.QueryVO;
import com.hetongxue.system.mapper.ChoiceMapper;
import com.hetongxue.system.mapper.CourseMapper;
import com.hetongxue.system.mapper.UserMapper;
import com.hetongxue.system.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private ChoiceMapper choiceMapper;
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
    public QueryVO getCoursePage(Integer currentPage, Integer pageSize, Course query) {
        User user = SecurityUtils.getUser();
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Course::getIsDelete, false);
        wrapper.orderByDesc(Course::getCreateTime);
        wrapper.like(Objects.nonNull(query.getCourseName()), Course::getCourseName, query.getCourseName());
        Page<Course> list = courseMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);
        ArrayList<Course> courses = new ArrayList<>();
        Optional.ofNullable(list.getRecords()).orElse(new ArrayList<>()).forEach(item -> {
            item.setUser(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId, item.getUserId())));
            item.setIsChoice(Objects.nonNull(choiceMapper.selectOne(new LambdaQueryWrapper<Choice>().eq(Choice::getUserId, user.getUserId()).eq(Choice::getCourseId, item.getCourseId()).ne(Choice::getIsQuit, true))));
            courses.add(item);
        });
        return new QueryVO(list.getCurrent(), list.getSize(), list.getTotal(), list.getPages(), courses);
    }

    @Override
    public QueryVO getMyCoursePage(Integer currentPage, Integer pageSize, Course query) {
        User user = SecurityUtils.getUser();
        List<Choice> choices = choiceMapper.selectList(new LambdaQueryWrapper<Choice>().eq(Choice::getUserId, user.getUserId()).eq(Choice::getStatus, 1));
        List<Long> courseIds = Optional.ofNullable(choices).orElse(new ArrayList<>()).stream().map(Choice::getCourseId).collect(Collectors.toList());
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        if (courseIds.size() > 0) {
            wrapper.eq(Course::getIsDelete, false);
            wrapper.in(Course::getCourseId, courseIds);
            wrapper.orderByAsc(Course::getCourseId);
            wrapper.like(Objects.nonNull(query.getCourseName()), Course::getCourseName, query.getCourseName());
        } else {
            return new QueryVO(currentPage.longValue(), pageSize.longValue(), 0L, 0L, null);
        }
        Page<Course> list = courseMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);
        ArrayList<Course> courses = new ArrayList<>();
        Optional.ofNullable(list.getRecords()).orElse(new ArrayList<>()).forEach(item -> courses.add(item.setUser(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId, item.getUserId())))));
        return new QueryVO(list.getCurrent(), list.getSize(), list.getTotal(), list.getPages(), courses);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCourse(Course course) {
        User user = SecurityUtils.getUser();
        if (user.getIsAdmin()) {
            return courseMapper.insert(course);
        }
        return courseMapper.insert(course.setUserId(user.getUserId()));
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