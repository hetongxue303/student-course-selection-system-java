package com.hetongxue.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 查询VO
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class QueryVo implements Serializable {

    /**
     * 当前页
     */
    long page;

    /**
     * 页面大小
     */
    long size;

    /**
     * 总条数
     */
    long total;

    /**
     * 总共页数
     */
    long pages;

    /**
     * 数据记录
     */
    Object records;

}