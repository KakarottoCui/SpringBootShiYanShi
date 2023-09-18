package cn.lemon.lib.service;

import cn.lemon.lib.dao.MenuDao;
import cn.lemon.lib.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    MenuDao menuDao;

    public List<Menu> getMenuList() {

        return menuDao.findAll();
    }

    public List<Menu> getParentMenuList() {
        List<Menu> parentMenuList = menuDao.findAll().stream().filter(menu -> menu.getParentId() == 0).collect(Collectors.toList());
        return parentMenuList;
    }

    public void save(Menu menu) {
        menuDao.save(menu);
    }

    public long count() {
        return menuDao.count();
    }

    public Menu getMenuById(int menuId) {
        Optional<Menu> optionalMenu = menuDao.findById(menuId);
        Menu menu = optionalMenu.get();
        return menu;
    }

    public List<Menu> getMenuList(int page, int limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        Page<Menu> menuPage = menuDao.findAll(pageable);
        List<Menu> menuList = menuPage.getContent();
        return menuList;
    }

    public void delete(Menu menu) {
        menuDao.delete(menu);
    }
}
