package cn.lemon.lib.utils;

import java.util.HashMap;
import java.util.Map;

public class ConstantUtils {

    public static Map initDays() {
        HashMap<Integer, String> days = new HashMap<>();

        days.put(1, "周一");
        days.put(2, "周二");
        days.put(3, "周三");
        days.put(4, "周四");
        days.put(5, "周五");
        days.put(6, "周六");
        days.put(7, "周日");
        return days;
    }

    public static Map initParts() {
        HashMap<Integer, String> parts = new HashMap<>();

        parts.put(1, "第一大节");
        parts.put(2, "第二大节");
        parts.put(3, "第三大节");
        parts.put(4, "第四大节");

        return parts;
    }

    public static Map initWeeks() {
        HashMap<Integer, String> weeks = new HashMap<>();
        for (int i = 1; i <= 20; i++) {
            String value = "第" + i + "周";
            weeks.put(i, value);
        }
        return weeks;
    }
}
