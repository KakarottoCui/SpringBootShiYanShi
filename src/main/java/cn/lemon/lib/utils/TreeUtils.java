package cn.lemon.lib.utils;

import cn.lemon.lib.entity.Menu;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    public static List<Menu> list2tree(List<Menu> list) {
        List<Menu> tree = new ArrayList<>();
        for (Menu node : list) {
            if (node.getParentId() == 0) {
                tree.add(findChild(node,list));
            }
        }
        return tree;
    }

    private static Menu findChild(Menu node, List<Menu> list) {
        for (Menu n : list) {
            if (n.getParentId() == node.getId()) {
                if (node.getChild() == null) {
                    node.setChild(new ArrayList<Menu>());
                }
                node.getChild().add(findChild(n,list));
            }
        }
        return node;
    }
}
