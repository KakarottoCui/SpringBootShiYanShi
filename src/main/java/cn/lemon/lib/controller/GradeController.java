package cn.lemon.lib.controller;

import cn.lemon.lib.entity.Grade;
import cn.lemon.lib.service.GradeService;
import cn.lemon.lib.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import jav
    public String getMyStudentListHtml(long gradeId,Model model) {
        model.addAttribute("gradeId",gradeId);
        log.info("gradeId {}",gradeId);
        return "/education/grade/myStudent.html";
    }

    /**
     * 获取所有班级
     * */
    @GetMapping
    @ResponseBody
    public ResultVO getGradeList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        page -= 1;
        long total = gradeService.count();
        List<Grade> gradeList = gradeService.getGradeList(page,limit);
        log.info("total {},page {},limit {}",total,page,limit);
        HashMap<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("menuList",gradeList);

        return ResultVO.SUCCESS(0,data);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultVO deleteGrade(Grade grade) {
        gradeService.delete(grade);
        return ResultVO.SUCCESS(200);
    }
}
