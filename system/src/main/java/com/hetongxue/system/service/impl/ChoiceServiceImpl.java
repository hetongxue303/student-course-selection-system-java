package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.handler.exception.DatabaseInsertException;
import com.hetongxue.system.domain.Choice;
import com.hetongxue.system.domain.Course;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.vo.QueryVO;
import com.hetongxue.system.repository.ChoiceMapper;
import com.hetongxue.system.repository.CourseMapper;
import com.hetongxue.system.repository.UserMapper;
import com.hetongxue.system.service.ChoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 选课业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class ChoiceServiceImpl extends ServiceImpl<ChoiceMapper, Choice> implements ChoiceService {

    @Resource
    private ChoiceMapper choiceMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Choice> getChoiceAll() {
        LambdaQueryWrapper<Choice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Choice::getIsDelete, false);
        wrapper.orderByAsc(Choice::getChoiceId);
        List<Choice> choices = new ArrayList<>();
        Optional.ofNullable(choiceMapper.selectList(wrapper))
                .orElse(new ArrayList<>())
                .forEach(item -> {
                    User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId, item.getUserId()));
                    Course course = courseMapper.selectOne(new LambdaQueryWrapper<Course>().eq(Course::getCourseId, item.getCourseId()));
                    choices.add(item.setRealName(user.getRealName())
                            .setCourseName(course.getCourseName()));
                });
        return choices;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public QueryVO getChoicePage(Integer currentPage, Integer pageSize, Choice query) {
        User user = SecurityUtils.getUser();
        LambdaQueryWrapper<Choice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Choice::getIsDelete, false);
        wrapper.orderByAsc(Choice::getChoiceId);

        // 不是管理员
        if (!user.getIsAdmin()) {
            if (user.getType() == 2) {
                List<Long> courseIds = courseMapper.selectList(new LambdaQueryWrapper<Course>().eq(Course::getUserId, user.getUserId()))
                        .stream()
                        .map(Course::getCourseId)
                        .collect(Collectors.toList());
                if (courseIds.size() > 0) {
                    wrapper.in(Choice::getCourseId, courseIds);
                }
            }
            if (user.getType() == 3) {
                wrapper.eq(Choice::getUserId, user.getUserId());
            }
        }

        // 查询相关
        if (Objects.nonNull(query.getRealName())) {
            List<Long> userIds = userMapper.selectList(new LambdaQueryWrapper<User>().like(User::getRealName, query.getRealName()))
                    .stream()
                    .map(User::getUserId)
                    .collect(Collectors.toList());
            wrapper.in(userIds.size() > 0, Choice::getUserId, userIds);
        }

        if (Objects.nonNull(query.getCourseName())) {
            List<Long> courseIds = courseMapper.selectList(new LambdaQueryWrapper<Course>().like(Course::getCourseName, query.getCourseName()))
                    .stream()
                    .map(Course::getCourseId)
                    .collect(Collectors.toList());
            wrapper.in(courseIds.size() > 0, Choice::getCourseId, courseIds);
        }

        if (Objects.nonNull(query.getStatus())) {
            if (query.getStatus() != -1) {
                wrapper.eq(Choice::getStatus, query.getStatus());
            }
        }
        Page<Choice> page = choiceMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);

        List<Choice> choices = new ArrayList<>();
        Optional.ofNullable(page.getRecords())
                .orElse(new ArrayList<>())
                .forEach(item -> {
                    Course course = courseMapper.selectOne(new LambdaQueryWrapper<Course>().eq(Course::getCourseId, item.getCourseId()));
                    String teacherName = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId, course.getUserId()))
                            .getRealName();
                    String studentName = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserId, item.getUserId()))
                            .getRealName();
                    String courseName = course.getCourseName();
                    choices.add(item.setTeacherName(teacherName)
                            .setRealName(studentName)
                            .setCourseName(courseName));
                });
        return new QueryVO(page.getCurrent(), page.getSize(), page.getTotal(), page.getPages(), choices);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addChoice(Choice choice) {
        return choiceMapper.insert(choice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delChoice(Long id) {
        return choiceMapper.updateById(new Choice().setChoiceId(id)
                .setIsDelete(true));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delBatchChoice(List<Long> ids) {
        AtomicInteger result = new AtomicInteger(1);
        ids.forEach(choiceId -> {
            if (choiceMapper.updateById(new Choice().setChoiceId(choiceId)
                    .setIsDelete(true)) == 0) result.set(0);
        });
        return result.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateChoice(Choice choice) {
        return choiceMapper.updateById(choice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int studentChoiceCourse(Integer type, Long courseId) {
        Course course = courseMapper.selectOne(new LambdaQueryWrapper<Course>().eq(Course::getCourseId, courseId));
        Long userId = SecurityUtils.getUser()
                .getUserId();
        switch (type) {
            case 1:
                Choice myChoice = choiceMapper.selectOne(new LambdaQueryWrapper<Choice>().eq(Choice::getCourseId, courseId)
                        .eq(Choice::getUserId, userId)
                        .eq(Choice::getIsQuit, false));
                if (Objects.nonNull(myChoice)) {
                    throw new DatabaseInsertException("你已选择该课程，不可重复选择！");
                }
                if (Objects.equals(course.getCount(), course.getChoice())) {
                    throw new DatabaseInsertException("课程已满");
                }

                courseMapper.updateById(new Course().setCourseId(courseId)
                        .setChoice(course.getChoice() + 1));
                choiceMapper.insert(new Choice().setUserId(userId)
                        .setCourseId(courseId));
                break;
            case 2:
                courseMapper.updateById(new Course().setCourseId(courseId)
                        .setChoice(course.getChoice() - 1));
                LambdaQueryWrapper<Choice> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Choice::getCourseId, courseId);
                wrapper.eq(Choice::getUserId, userId);
                wrapper.eq(Choice::getIsQuit, false);
                Choice choice = choiceMapper.selectOne(wrapper);
                choiceMapper.updateById(choice.setIsQuit(true));
                break;
        }
        return 1;
    }

}