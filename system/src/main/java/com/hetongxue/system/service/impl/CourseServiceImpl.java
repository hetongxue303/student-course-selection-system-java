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
        wrapper.orderByAsc(Course::getCourseId);
        if (Objects.nonNull(query.getCourseName())) {
            wrapper.like(Course::getCourseName, query.getCourseName());
        }

        if (!user.getIsAdmin()) {
            if (user.getType() == 2) {
                wrapper.eq(Course::getUserId, user.getUserId());
            }
        }

        Page<Course> list = courseMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);

        List<Course> courses = new ArrayList<>();
        List<Long> courseIds = new ArrayList<>();
        List<Long> confirmCourseIds = new ArrayList<>();
        // 查出该学生的所有选课记录
        if (user.getType() == 3) {
            List<Choice> choices = choiceMapper.selectList(new LambdaQueryWrapper<Choice>().eq(Choice::getUserId, user.getUserId()));
            courseIds = Optional.of(choices).orElse(new ArrayList<>()).stream().filter(item -> Objects.nonNull(item) && Objects.equals(item.getIsDelete(), false)).map(Choice::getCourseId).collect(Collectors.toList());
            if (courseIds.size() > 0) {
                List<Course> courseList = courseMapper.selectList(new LambdaQueryWrapper<Course>().in(Course::getCourseId, courseIds));
                if (courseList.size() > 0) {
                    confirmCourseIds = courseList.stream().filter(item -> Objects.nonNull(item) && Objects.equals(item.getIsConfirm(), true)).map(Course::getCourseId).collect(Collectors.toList());
                }
            }
        }
        List<Long> finalCourseIds = courseIds;
        List<Long> finalConfirmCourseIds = confirmCourseIds;
        Optional.ofNullable(list.getRecords()).orElse(new ArrayList<>()).forEach(course -> {
            courses.add(course.setTeacherName(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId, course.getUserId())).getRealName()).setIsChoice(finalCourseIds.contains(course.getCourseId())).setIsConfirm(finalConfirmCourseIds.contains(course.getCourseId())));
        });

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