package com.hetongxue.system.domain.bo;

import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 业务对象：角色菜单信息
 *
 * @author 何同学
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RoleMenuBO extends Role implements Serializable {

    /**
     * 角色菜单列表
     */
    private List<Menu> menus;

}