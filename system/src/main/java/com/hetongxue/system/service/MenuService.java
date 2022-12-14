package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.bo.MenuBO;
import com.hetongxue.system.domain.vo.MenuTreeVO;
import com.hetongxue.system.domain.vo.QueryVO;

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

    /**
     * 按树格式查询菜单列表
     *
     * @param parentId 父ID
     * @return List
     */
    List<MenuTreeVO> selectMenuToTree(Long parentId);

    /**
     * 按树格式查询菜单列表
     *
     * @param menu 查询信息
     * @return List
     */
    List<MenuBO> selectMenuTableToTree(Menu menu);


    /**
     * 按树格式分页查询菜单列表
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param query       查询信息
     * @return List
     */
    QueryVO selectMenuTreePage(Integer currentPage, Integer pageSize, Menu query);

    /**
     * 添加菜单
     *
     * @param menu 菜单信息
     * @return int
     */
    int addMenu(Menu menu);

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return int
     */
    int delMenu(Long id);

    /**
     * 批量删除菜单
     *
     * @param ids 菜单ID
     * @return int
     */
    int delBatchMenu(List<Long> ids);

    /**
     * 更新菜单
     *
     * @param menu 菜单信息
     * @return int
     */
    int updateMenu(Menu menu);

    /**
     * 获取对应角色的角色菜单列表
     *
     * @param roleId 角色ID
     */
    List<MenuTreeVO> getMenuListByRoleId(Long roleId);

}