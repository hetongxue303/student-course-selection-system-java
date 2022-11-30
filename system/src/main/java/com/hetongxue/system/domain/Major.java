package com.hetongxue.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 专业实体
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_major")
public class Major implements Serializable {

    /**
     * 专业ID
     */
    @TableId(type = IdType.AUTO)
    private Long majorId;
    /**
     * 专业名称
     */
    private String majorName;
    /**
     * 专业描述
     */
    private String remark;
    /**
     * 是否删除(1是 0否)
     */
    private boolean isDelete;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}