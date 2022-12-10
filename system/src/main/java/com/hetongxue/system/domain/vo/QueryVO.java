package com.hetongxue.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 表现层对象：通用分页查询结果
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class QueryVO implements Serializable {

    /**
     * 当前页
     */
    Long page;

    /**
     * 页面大小
     */
    Long size;

    /**
     * 总条数
     */
    Long total;

    /**
     * 总共页数
     */
    Long pages;

    /**
     * 数据记录
     */
    Object records;

}