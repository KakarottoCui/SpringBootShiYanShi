package cn.lemon.lib.vo;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String name;

    private String url;

    private List<Menu> child;

    public Menu() {
    }

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getChild() {
        return child;
    }

    public void setChild(List<Menu> child) {
        this.child = child;
    }

    public static List<Menu> getStudentMenuTree() {

        Menu father01 = new Menu("用户管理","");
        List<Menu> childList01 = new ArrayList<>();

        Menu child0101 = new Menu("个人信息","javascript:;");
        childList01.add(child0101);
        father01.setChild(childList01);

        Menu father02 = new Menu("实验室管理","");
        List<Menu> childList02 = new ArrayList<>();

        Menu child0201 = new Menu("添加实验室","javascript:;");
        childList02.add(child0201);
        father01.setChild(childList02);

        List<Menu> menuList = new ArrayList<>();
        menuList.add(father01);
        menuList.add(father02);
        return menuList;
    }
}
