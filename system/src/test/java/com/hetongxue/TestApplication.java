package com.hetongxue;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hetongxue.system.domain.Course;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.mapper.CourseMapper;
import com.hetongxue.system.mapper.SelectionMapper;
import com.hetongxue.system.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试类
 *
 * @author 何同学
 */
@SpringBootTest
public class TestApplication {

    @Resource
    private CourseMapper courseMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private SelectionMapper selectionMapper;


    @Test
    void test() {
        User student = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, "2021230522"));
        User teacher = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, "100522"));
        List<Course> courses = courseMapper.selectList(new LambdaQueryWrapper<Course>().eq(Course::getUserId, teacher.getUserId()));
//        List<Selection> selections = selectionMapper.selectList(new LambdaQueryWrapper<Selection>().eq(Selection::getUserId, student.getUserId()));
        List<Course> courseList = courseMapper.selectList(new LambdaQueryWrapper<Course>().in(Course::getCourseId, selectionMapper.selectCourseIdList(student.getUserId())));
        System.out.println("\n\n\n==============分界线===================");
        System.out.println("学生：" + student.getNickName());
        System.out.println("课程：");
        courseList.forEach(item -> System.out.println(item.getCourseName()));
        System.out.println("=========================================");
        System.out.println("教师：" + teacher.getNickName());
        System.out.println("课程：");
        courses.forEach(item -> System.out.println(item.getCourseName()));

    }

}