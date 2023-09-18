package cn.lemon.lib.controller;

import cn.lemon.lib.entity.Admin;
import cn.lemon.lib.entity.Student;
import cn.lemon.lib.entity.Teacher;
import cn.lemon.lib.service.AdminService;
import cn.lemon.lib.service.MenuService;
import cn.lemon.lib.service.StudentService;
import cn.lemon.lib.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录
 * */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    AdminService adminService;

    @Autowired
    MenuService menuService;
    /**
     * 登录页面
     * */
    @GetMapping("/login")
    public String loginIndex() {
        return "/login/index.html";
    }

    /**
     * 登录验证
     * */
    @PostMapping("/login")
    public String checkLogin(String username, String password, int type, HttpServletRequest request, HttpServletResponse response, Model model) {

        log.info("username {}, password {}, type {}", username, password, type);

        HttpSession session = request.getSession();
        Cookie cookie = new Cookie("cookie_username", "");
        model.addAttribute("error",null);
        int current = 2;
        if (type == 0) {
            Student check = studentService.check(username, password);
            if (check == null) {
                model.addAttribute("error","账号密码错误");
                return "/login/index.html";
            }
            cookie = new Cookie("cookie_username", String.valueOf(check.getId()));
            session.setAttribute("userInfo",check.getId());
            model.addAttribute("name",check.getUsername());
            current = 0;
        } else if (type == 1){
            Teacher check = teacherService.check(username, password);
            if (check == null) {
                model.addAttribute("error","账号密码错误");
                return "/login/index.html";
            }
            cookie = new Cookie("cookie_username", String.valueOf(check.getId()));
            session.setAttribute("userInfo",check.getId());
            model.addAttribute("name",check.getUsername());
            current = 1;
        } else if (type == 2){
            Admin check = adminService.check(username, password);
            if (check == null) {
                model.addAttribute("error","账号密码错误");
                return "/login/index.html";
            }
            cookie = new Cookie("cookie_username", String.valueOf(check.getId()));
            session.setAttribute("userInfo",check.getId());
            model.addAttribute("name",check.getName());
            current = 2;
        }
        // 保存cookie，实现自动登录
        // 设置cookie的持久化时间，30天
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setMaxAge(60 * 60);
        // 设置为当前项目下都携带这个cookie
        cookie.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(cookie);
        switch (current) {
            case 0:return "studentIndex.html";
            case 1:return "teacherIndex.html";
        }
        return "index.html";
    }

    /**
     * 退出登录
     * */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // 删除session里面的用户信息
        session.removeAttribute("userInfo");
        // 保存cookie，实现自动登录
        Cookie cookie_username = new Cookie("cookie_username", "");
        // 设置cookie的持久化时间，0
        cookie_username.setMaxAge(0);
        // 设置为当前项目下都携带这个cookie
        cookie_username.setPath(request.getContextPath());
        // 向客户端发送cookie
        response.addCookie(cookie_username);
        return "/login/index";
    }
}
