package cn.lemon.lib.controller;

import cn.lemon.lib.entity.Teacher;
import cn.lemon.lib.service.TeacherService;
import cn.lemon.lib.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/index.html")
    public String toTeacherPage() {

        return "/user/teacher/index.html";
    }


    /**
     * 添加教师页面
     * */
    @GetMapping("/add.html")
    public String toTeacherAddPage(Model model, @RequestParam(value = "teacherId", defaultValue = "0") Integer teacherId) {
        // 初始化班级下拉框
        Teacher teacher = new Teacher();
        if (teacherId != 0) {
            teacher = teacherService.getTeacherById(teacherId);
        }
        model.addAttribute("teacher",teacher);
        return "/user/teacher/add.html";
    }

    /**
     * 添加教师
     * */
    @PostMapping
    @ResponseBody
    public ResultVO addTeacher(Teacher teacher) {

        teacherService.save(teacher);
        return ResultVO.SUCCESS();
    }

    /**
     * 获取所有老师
     * */
    @GetMapping
    @ResponseBody
    public ResultVO getTeacherList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        page -= 1;
        long total = teacherService.count();
        List<Teacher> teacherList = teacherService.getTeacherList(page,limit);

        HashMap<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("teacherList",teacherList);

        return ResultVO.SUCCESS(0,data);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultVO deleteTeacher(Teacher teacher) {
        teacherService.delete(teacher);
        return ResultVO.SUCCESS(200);
    }

}
