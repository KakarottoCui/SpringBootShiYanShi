package cn.lemon.lib.controller;

import cn.lemon.lib.entity.Lib;
import cn.lemon.lib.service.LibService;
import cn.lemon.lib.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/lib")
public class LibController {


    @Autowired
    LibService libService;

    @GetMapping("/index.html")
    public String toLibIndexPage() {
        return "/lib/index.html";
    }

    /**
     * 添加实验室页面
     */
    @GetMapping("/add.html")
    public String toLibAddPage(Model model, @RequestParam(value = "libId", defaultValue = "0") Integer libId) {

        Lib lib = new Lib();
        if (libId != 0) {
            lib = libService.getLibById(libId);
        }
        model.addAttribute("lib", lib);
        return "/lib/add.html";
    }

    /**
     * 添加实验室
     */
    @PostMapping
    @ResponseBody
    public ResultVO addLib(Lib lib) {
        libService.save(lib);
        return ResultVO.SUCCESS();
    }

    /**
     * 获取所有实验室
     */
    @GetMapping
    @ResponseBody
    public ResultVO getLibList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        System.out.println("limit"+limit);

        page -= 1;
        long total = libService.count();
        List<Lib> libList = libService.getLibList(page, limit);
        HashMap<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("libList", libList);

        return ResultVO.SUCCESS(0, data);
    }

    /**
     * 删除实验室
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultVO deleteGrade(Lib lib) {
        libService.delete(lib);
        return ResultVO.SUCCESS(200);
    }

    /**
     * 学生看到的实验室页面
     */
    @GetMapping("/studentIndex.html")
    public String toLibStudentIndexPage() {
        return "/lib/studentIndex.html";
    }

    /**
     * 实验室课程列表
     */
    @GetMapping("/libSubject.html")
    public String subject(long libId, Model model) {
        model.addAttribute("libId", libId);
        return "/lib/libSubject.html";
    }

    /**
     * 实验室课程列表data
     */
    public ResultVO getCurrentSubjectListOfLab(
            long libId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        page -= 1;
        long total = libService.count();
        List<Lib> subjectListOFLib = libService.getLibList(page, limit);
        HashMap<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("subjectListOFLib", subjectListOFLib);

        return ResultVO.SUCCESS(0, data);
    }
}
