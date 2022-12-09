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
     * 按用户ID查询菜单列表
     *
     * @param userId 用户ID
     * @return List<Menu>
     */
    List<Menu> selectMenuListByUserId(Long userId);

}