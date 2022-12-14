package com.hetongxue.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 表现层对象：菜单树
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MenuTreeVO implements Serializable {

    /**
     * 菜单ID
     */
    private Long id;
    /**
     * 节点标签名
     */
    private String label;
    /**
     * 没有子节点
     */
    private Boolean isLeaf;
    /**
     * 是否禁用
     */
    private Boolean disabled;
    /**
     * 指定子树为节点对象的某个属性值
     */
    private List<MenuTreeVO> children;

}