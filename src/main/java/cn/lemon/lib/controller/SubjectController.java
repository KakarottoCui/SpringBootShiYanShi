package cn.lemon.lib.controller;

import cn.lemon.lib.entity.Grade;
import cn.lemon.lib.entity.Subject;
import cn.lemon.lib.entity.Teacher;
import cn.lemon.lib.service.GradeService;
import cn.lemon.lib.service.SubjectService;
import cn.lemon.lib.service.TeacherService;
import cn.lemon.lib.utils.ConstantUtils;
import cn.lemon.lib.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/education/subject")
@Slf4j
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    GradeService gradeService;

    @Autowired
    TeacherService teacherService;

    @GetMapping("/index.html")
    public String toSubjectPage() {
        return "/education/subject/index.html";
    }

    /**
     * 添加课程页面
     * */
    @GetMapping("/add.html")
    public String toSubjectAddPage(Model model, @RequestParam(value = "subjectId", defaultValue = "0") Integer subjectId) {

        Subject subject = new Subject();
        if (subjectId != 0) {
            subject = subjectService.getSubjectById(subjectId);
        }
        List<Grade> gradeList = getGradeList();
        List<Teacher> teacherList = getTeacherList();

        Map weeks = ConstantUtils.initWeeks();
        Map days = ConstantUtils.initDays();
        Map parts = ConstantUtils.initParts();

        model.addAttribute("gradeList",gradeList);
        model.addAttribute("teacherList",teacherList);

        model.addAttribute("subject",subject);
        model.addAttribute("weeks",weeks);
        model.addAttribute("days",days);
        model.addAttribute("parts",parts);

        return "/education/subject/add.html";
    }



    private List<Grade> getGradeList() {
        return gradeService.getGradeList();
    }

    private List<Teacher> getTeacherList() {
        return teacherService.getTeacherList();
    }
    /**
     * 添加课程
     * */
    @PostMapping
    @ResponseBody
    public ResultVO addSubject(Subject subject) {

        subjectService.save(subject);
        return ResultVO.SUCCESS();
    }

    /**
     * 获取所有班级
     * */
    @GetMapping
    @ResponseBody
    public ResultVO getSubjectList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        page -= 1;
        long total = subjectService.count();
        List<Subject> subjectList = subjectService.getGradeList(page,limit);
        log.info("total {},page {},limit {}",total,page,limit);
        HashMap<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("subjectList",subjectList);

        return ResultVO.SUCCESS(0,data);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultVO deleteSubject(Subject subject) {
        subjectService.delete(subject);
        return ResultVO.SUCCESS(200);
    }
}
