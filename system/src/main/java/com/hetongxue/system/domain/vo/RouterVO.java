package com.hetongxue.system.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 表现层对象：路由
 *
 * @author 何同学
 */
@Data
@Accessors(chain = true)
public class RouterVO implements Serializable {

    /**
     * 路由名称
     */
    private String name;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 路由组件
     */
    private String component;
    /**
     * 路由meta信息
     */
    private MetaVo meta;
    /**
     * 子路由
     */
    private List<RouterVO> children;

    @Data
    @Accessors(chain = true)
    public static class MetaVo implements Serializable {

        /**
         * 路由标题
         */
        private String title;
        /**
         * 路由图标
         */
        private String icon;
        /**
         * 是否显示
         */
        private Boolean show;
        /**
         * 是否缓存
         */
        private Boolean cache;
        /**
         * 权限信息
         */
        private String[] permission;

    }

}