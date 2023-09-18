package cn.lemon.lib.service;

import cn.lemon.lib.dao.GradeDao;
import cn.lemon.lib.entity.Grade;
import cn.lemon.lib.mapper.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    GradeDao gradeDao;

    @Autowired
    GradeMapper gradeMapper;

    public List<Grade> getGradeList() {

        return gradeDao.findAll();
    }

    public void save(Grade grade) {
        gradeDao.save(grade);
    }

    public long count() {
        return gradeDao.count();
    }

    // 获取我的班级总数
    public long countOfMyGrade() {
        return gradeDao.count();
    }
    // 获取我的班级
    public List<Grade> getGradeListOfMine(long teacherId,int page, int limit) {
        return gradeMapper.getGradeListOfMine(teacherId,page,limit);
    }


    public Grade getGradeById(long gradeId) {
        Optional<Grade> optionalGrade = gradeDao.findById(gradeId);
        Grade grade = optionalGrade.get();
        return grade;
    }

    public List<Grade> getGradeList(int page, int limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        Page<Grade> gradePage = gradeDao.findAll(pageable);
        List<Grade> gradeList = gradePage.getContent();
        return gradeList;
    }

    public void delete(Grade grade) {
        gradeDao.delete(grade);
    }

    /**
     * 获取当前老师 该时间段没有课的班级
     * */
    public List<Grade> getCurrentGradeOfTeacher(int teacherId, int startWeek, int day, int part) {
        return gradeMapper.getCurrentGradeOfTeacher(teacherId,startWeek,day,part);
    }
}
