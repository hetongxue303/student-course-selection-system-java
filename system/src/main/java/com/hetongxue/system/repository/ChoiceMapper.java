package com.hetongxue.system.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hetongxue.system.domain.Choice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 选课Mapper
 *
 * @author 何同学
 */
public interface ChoiceMapper extends BaseMapper<Choice> {

    /**
     * 通过用户ID查询课程列表
     *
     * @param userId 用户ID
     * @return List
     */
    @Select("select course_id from sys_choice where user_id = #{userId}")
    List<Long> selectCourseIdList(@Param("userId") Long userId);

}