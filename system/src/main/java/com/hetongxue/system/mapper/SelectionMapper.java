package com.hetongxue.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hetongxue.system.domain.Selection;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 选课Mapper
 *
 * @author 何同学
 */
public interface SelectionMapper extends BaseMapper<Selection> {

    /**
     * 通过用户ID查询课程列表
     *
     * @param userId 用户ID
     * @return List
     */
    @Select("select course_id from sys_selection where user_id = #{userId}")
    List<Long> selectCourseIdList(@Param("userId") Long userId);

}