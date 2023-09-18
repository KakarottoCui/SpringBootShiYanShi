package cn.lemon.lib.controller;

import cn.lemon.lib.entity.Grade;
import cn.lemon.lib.entity.Lib;
import cn.lemon.lib.entity.Reservation;
import cn.lemon.lib.service.GradeService;
import cn.lemon.lib.service.LibService;
import cn.lemon.lib.service.ReservationService;
import cn.lemon.lib.utils.ConstantUtils;
import cn.lemon.lib.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    LibService libService;

    @Autowired
    GradeService gradeService;

    @Autowired
    ReservationService reservationService;

    @GetMapping("/teacher/index.html")
    public String toTeacherIndexHtml(Model model) {

        Map weeks = ConstantUtils.initWeeks();
        Map days = ConstantUtils.initDays();
        Map parts = ConstantUtils.initParts();

        model.addAttribute("weeks", weeks);
        model.addAttribute("days", days);
        model.addAttribute("parts", parts);


        return "/reservation/teacher/index.html";
    }

    @GetMapping("/individual/index.html")
    public String toIndividualIndexHtml(Model model) {
        Map weeks = ConstantUtils.initWeeks();
        Map days = ConstantUtils.initDays();
        Map parts = ConstantUtils.initParts();

        model.addAttribute("weeks", weeks);
        model.addAttribute("days", days);
        model.addAttribute("parts", parts);
        return "/reservation/individual/index.html";
    }

    @GetMapping("/index.html")
    public String toReservationIndexHtml() {

        return "/reservation/index.html";
    }

    @GetMapping("/lib")
    @ResponseBody
    public ResultVO getLibList(int startWeek, int day, int part) {
        List<Lib> labList = libService.getCurrentlyUnusedLabList(startWeek, day, part);
        return ResultVO.SUCCESS(labList);
    }

    @GetMapping("/grade")
    @ResponseBody
    public ResultVO getGradeList(int teacherId,int startWeek, int day, int part) {
        List<Grade> gradeList = gradeService.getCurrentGradeOfTeacher(teacherId, startWeek, day, part);
        return ResultVO.SUCCESS(gradeList);
    }


    /**
     * 添加预约
     * */
    @PostMapping("/teacher")
    @ResponseBody
    public ResultVO addReservation(Reservation reservation, HttpSession session) {
        long userId = (long)session.getAttribute("userInfo");
        reservation.setUserId(userId);
        reservationService.save(reservation);
        return ResultVO.SUCCESS();
    }

    /**
     * 获取所有预约单
     * */
    @GetMapping
    @ResponseBody
    public ResultVO getReservationList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        page -= 1;
        long total = reservationService.count();
        List<Reservation> reservationList = reservationService.getReservationList(page,limit);
        HashMap<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("reservationList",reservationList);

        return ResultVO.SUCCESS(0,data);
    }

    /**
     * 获取个人预约单
     * */
    @GetMapping("/individual/mine")
    @ResponseBody
    public ResultVO getStudentReservationList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                              HttpSession session) {
        page -= 1;
        long userId = (long)session.getAttribute("userInfo");
        long total = reservationService.studentCount(userId);

        List<Reservation> reservationList = reservationService.getStudentReservationList(page,limit,userId);
        HashMap<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("reservationList",reservationList);

        return ResultVO.SUCCESS(0,data);
    }

    /**
     * 获取教师预约单
     * */
    @GetMapping("/teacher/mine")
    @ResponseBody
    public ResultVO getTeacherReservationList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                              @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                              HttpSession session) {
        page -= 1;
        long userId = (long)session.getAttribute("userInfo");
        long total = reservationService.teacherCount(userId);

        List<Reservation> reservationList = reservationService.getTeacherReservationList(page,limit,userId);
        HashMap<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("reservationList",reservationList);

        return ResultVO.SUCCESS(0,data);
    }

    /**
     * 学生预约列表
     * individual/mine.html
     * */
    @GetMapping("/individual/mine.html")
    public String toIndividualMineHtml() {

        return "/reservation/individual/mine.html";
    }

    /**
     * 教师预约列表
     * teacher/mine.html
     * */
    @GetMapping("/teacher/mine.html")
    public String toTeacherMineHtml() {

        return "/reservation/teacher/mine.html";
    }

    /**
     * 获取未深刻预约单
     * */
    @GetMapping("/audit")
    @ResponseBody
    public ResultVO getNoneAuditReservationList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        page -= 1;
        long total = reservationService.noneAuditCount();
        List<Reservation> reservationList = reservationService.getNoneAuditReservationList(page,limit);
        HashMap<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("reservationList",reservationList);

        return ResultVO.SUCCESS(0,data);
    }

    @GetMapping("/Audit.html")
    public String toAuditReservationIndexHtml() {

        return "/reservation/Audit.html";
    }

    @GetMapping("update/audit")
    @ResponseBody
    public ResultVO UpdateReservation(long reservationId) {
        Reservation reservation = reservationService.getReservationId(reservationId);
        reservation.setStatus(1);
        reservationService.save(reservation);
        System.out.println(reservationId);
        return ResultVO.SUCCESS(200);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultVO deleteReservation(Reservation reservation) {
        reservationService.delete(reservation);
        return ResultVO.SUCCESS(200);
    }

//    /**
//     * 获取学生预约列表
//     * */
//    @GetMapping("/individual/mine")
//    @ResponseBody
//    public ResultVO getReservationListOfMine(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                                                @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
//
//        return ResultVO.SUCCESS();
//    }
}
