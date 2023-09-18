package cn.lemon.lib.controller;

import cn.lemon.lib.entity.Menu;
import cn.lemon.lib.service.MenuService;
import cn.lemon.lib.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单
 * */
@Controller
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/index.html")
    public String toMenuPage() {
        return "/menu/index.html";
    }

    /**
     * 添加菜单页面
     * */
    @GetMapping("/add.html")
    public String toMenuAddPage(Model model, @RequestParam(value = "menuId", defaultValue = "0") Integer menuId) {
        // 初始化父菜单下拉框
        List<Menu> parentMenuList = menuService.getParentMenuList();
        model.addAttribute("parentMenuList",parentMenuList);
        Menu menu = new Menu();
        if (menuId != 0) {
            menu = menuService.getMenuById(menuId);
        }
        model.addAttribute("menu",menu);
        return "menu/add.html";
    }

    /**
     * 子菜单页面
     * */
    @GetMapping("/child.html")
    public String toMenuChildPage(int menuId, Model model) {

        return "menu/child.html";
    }

    /**
     * 添加菜单
     * */
    @PostMapping
    @ResponseBody
    public ResultVO addMenu(Menu menu) {
        log.info("menu {}",menu);
        menuService.save(menu);
        return ResultVO.SUCCESS();
    }

    /**
     * 获取所有菜单
     * */
    @GetMapping
    @ResponseBody
    public ResultVO getMenuList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        page -= 1;
        long total = menuService.count();
        List<Menu> menuList = menuService.getMenuList(page,limit);
        log.info("total {},page {},limit {}",total,page,limit);
        HashMap<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("menuList",menuList);

        return ResultVO.SUCCESS(0,data);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultVO deleteMenu(Menu menu) {
        menuService.delete(menu);
        return ResultVO.SUCCESS(200);
    }

}
