package cn.lemon.lib.controller;

import cn.lemon.lib.entity.Menu;
import cn.lemon.lib.service.MenuService;

import cn.lemon.lib.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    MenuService menuService;

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        String userInfo = (String) session.getAttribute("userInfo");
        log.info("userInfo {}", userInfo);
        // 根据用户类型查找相应的菜单

        //添加菜单权限
        List<Menu> menuList = menuService.getMenuList();
        List<Menu> menuTree = TreeUtils.list2tree(menuList);
        model.addAttribute("menuTree",menuTree);
        return "index.html";
    }
}
