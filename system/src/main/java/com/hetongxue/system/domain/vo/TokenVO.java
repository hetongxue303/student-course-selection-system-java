package com.hetongxue.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 表现层对象：token
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TokenVO implements Serializable {

    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    private long expireTime;

}