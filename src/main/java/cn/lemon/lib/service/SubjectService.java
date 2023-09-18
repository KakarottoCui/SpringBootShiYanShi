package cn.lemon.lib.service;

import cn.lemon.lib.dao.SubjectDao;
import cn.lemon.lib.entity.Subject;
import cn.lemon.lib.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    SubjectMapper subjectMapper;

    public List<Subject> getSubjectList() {

        return subjectDao.findAll();
    }

    public void save(Subject subject) {
        subjectDao.save(subject);
    }

    public long count() {
        return subjectDao.count();
    }

    public Subject getSubjectById(long subjectId) {
        Optional<Subject> optionalSubject = subjectDao.findById(subjectId);
        Subject subject = optionalSubject.get();
        return subject;
    }

    public List<Subject> getGradeList(int page, int limit) {
        List<Subject> subjectList = subjectMapper.getSubjectListPage(page, limit);
        System.out.println(subjectList);
        return subjectList;
    }

    public void delete(Subject subject) {
        subjectDao.delete(subject);
    }
}
