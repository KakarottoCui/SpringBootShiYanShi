package cn.lemon.lib.controller;

import cn.lemon.lib.entity.Grade;
import cn.lemon.lib.entity.Student;
import cn.lemon.lib.service.GradeService;
import cn.lemon.lib.service.StudentService;
import cn.lemon.lib.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user/student")
@Slf4j
public class StudentController {

    @Autowired
    GradeService gradeService;

    @Autowired
    StudentService studentService;

    @GetMapping("/index.html")
    public String toStudentPage(Model model) {

        List<Grade> gradeList = gradeService.getGradeList();
        model.addAttribute("gradeList",gradeList);
        return "/user/student/index.html";
    }

    /**
     * 添加学生页面
     * */
    @GetMapping("/add.html")
    public String toMenuAddPage(Model model, @RequestParam(value = "studentId", defaultValue = "0") Integer studentId) {
        // 初始化班级下拉框
        List<Grade> gradeList = gradeService.getGradeList();
        model.addAttribute("gradeList",gradeList);
        Student student = new Student();
        if (studentId != 0) {
            student = studentService.getStudentById(studentId);
        }
        model.addAttribute("student",student);
        return "/user/student/add.html";
    }

    /**
     * 获取所有学生
     * */
    @GetMapping
    @ResponseBody
    public ResultVO getStudentList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                   @RequestParam(value = "gradeId", defaultValue = "0") Integer gradeId) {
        page -= 1;

        long total = 0;
        List<Student> studentList = new ArrayList<>();
        if (gradeId != 0) {
            log.info("gradeId {}",gradeId);
            total = studentService.countOfGrade(gradeId);
            studentList = studentService.getStudentListOfGrade(page,limit,gradeId);
        } else {
            total = studentService.count();
            studentList = studentService.getStudentList(page,limit);
        }

        HashMap<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("studentList",studentList);
        System.out.println(studentList);
        return ResultVO.SUCCESS(0,data);
    }


    /**
     * 添加学生
     * */
    @PostMapping
    @ResponseBody
    public ResultVO addMenu(Student student) {
        studentService.save(student);
        return ResultVO.SUCCESS();
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultVO deleteStudent(Student student) {
        studentService.delete(student);
        return ResultVO.SUCCESS(200);
    }
}
