package utils;

import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.bo.MenuBO;
import com.hetongxue.system.domain.vo.MenuTreeVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 菜单过滤工具类
 *
 * @author 何同学
 */
public class MenuFilterUtils {

    /**
     * 过滤菜单表格数据
     *
     * @param menus    菜单列表
     * @param parentId 父ID
     * @return List
     */
    public static List<MenuBO> filterMenuToMenuBO(List<Menu> menus, Long parentId) {
        List<MenuBO> result = new ArrayList<>();
        Optional.ofNullable(menus).orElse(new ArrayList<>()).stream().filter(item -> Objects.nonNull(item) && Objects.equals(item.getParentId(), parentId)).forEach(item -> {
            MenuBO bo = new MenuBO();
            BeanUtils.copyProperties(item, bo);
            result.add(bo.setHasChildren(filterMenuToMenuBO(menus, item.getMenuId()).size() > 0));
        });
        return result;
    }

    /**
     * 过滤菜单树形数据
     *
     * @param menus    菜单列表
     * @param parentId 父ID
     * @return List
     */
    public static List<MenuTreeVO> filterMenuToMenuTree(List<Menu> menus, Long parentId) {
        List<MenuTreeVO> result = new ArrayList<>();
        Optional.ofNullable(menus).orElse(new ArrayList<>()).stream().filter(item -> Objects.nonNull(item) && Objects.nonNull(item.getMenuTitle()) && Objects.equals(item.getParentId(), parentId)).forEach(item -> {
            result.add(new MenuTreeVO(item.getMenuId(), item.getMenuTitle(), !(Objects.requireNonNull(filterMenuToMenuTree(menus, item.getMenuId())).size() > 0), false, null));
        });
        return result;
    }

}