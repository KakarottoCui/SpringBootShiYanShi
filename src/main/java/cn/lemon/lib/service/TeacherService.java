package cn.lemon.lib.service;


import cn.lemon.lib.dao.TeacherDao;
import cn.lemon.lib.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherDao teacherDao;


    public void save(Teacher teacher) {
        teacherDao.save(teacher);
    }

    public long count() {
        return teacherDao.count();
    }

    public Teacher getTeacherById(long teacherId) {
        Optional<Teacher> optionalTeacher = teacherDao.findById(teacherId);
        Teacher teacher = optionalTeacher.get();
        return teacher;
    }

    public List<Teacher> getTeacherList() {
        return teacherDao.findAll();
    }

    public List<Teacher> getTeacherList(int page, int limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        Page<Teacher> teacherPage = teacherDao.findAll(pageable);
        List<Teacher> teacherList = teacherPage.getContent();
        return teacherList;
    }

    public void delete(Teacher teacher) {
        teacherDao.delete(teacher);
    }

    public Teacher check(String username,String password) {
        Teacher teacher = teacherDao.findByUsername(username);
        return teacher;
    }

}
