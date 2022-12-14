package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Choice;

/**
 * 选课业务
 *
 * @author 何同学
 */
public interface ChoiceService extends IService<Choice> {

    /**
     * 学生选课处理
     *
     * @param type     类型(1选课 2退选)
     * @param courseId 课程ID
     */
    int studentChoiceCourse(Integer type, Long courseId);

}