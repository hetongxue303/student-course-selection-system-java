package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.handler.exception.DatabaseInsertException;
import com.hetongxue.handler.exception.DatabaseUpdateException;
import com.hetongxue.system.domain.Choice;
import com.hetongxue.system.domain.Course;
import com.hetongxue.system.mapper.ChoiceMapper;
import com.hetongxue.system.mapper.CourseMapper;
import com.hetongxue.system.service.ChoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int studentChoiceCourse(Integer type, Long courseId) {
        try {
            Course course = courseMapper.selectOne(new LambdaQueryWrapper<Course>().eq(Course::getCourseId, courseId));
            Long userId = SecurityUtils.getUser().getUserId();
            switch (type) {
                case 1:
                    if (Objects.equals(course.getCount(), course.getChoice())) {
                        throw new DatabaseInsertException("课程已满");
                    }
                    courseMapper.updateById(new Course().setCourseId(courseId).setChoice(course.getChoice() + 1));
                    choiceMapper.insert(new Choice().setUserId(userId).setCourseId(courseId));
                    break;
                case 2:
                    courseMapper.updateById(new Course().setCourseId(courseId).setChoice(course.getChoice() - 1));
                    LambdaQueryWrapper<Choice> wrapper = new LambdaQueryWrapper<>();
                    wrapper.eq(Choice::getCourseId, courseId);
                    wrapper.eq(Choice::getUserId, userId);
                    Choice choice = choiceMapper.selectOne(wrapper);
                    System.out.println("choice = " + choice);
                    choiceMapper.updateById(choice.setIsQuit(true));
                    break;
            }
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new DatabaseUpdateException("更新失败");
        }
    }

}