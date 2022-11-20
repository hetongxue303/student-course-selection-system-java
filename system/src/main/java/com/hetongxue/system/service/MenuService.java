package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Menu;

import java.util.List;

/**
 * 菜单业务
 *
 * @author 何同学
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据用户ID获取权限列表
     */
    List<Menu> selectMenuListByAccountID(Long accountID);

}