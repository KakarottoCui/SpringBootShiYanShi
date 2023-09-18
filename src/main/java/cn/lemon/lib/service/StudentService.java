package cn.lemon.lib.service;

import cn.lemon.lib.dao.StudentDao;
import cn.lemon.lib.entity.Student;
import cn.lemon.lib.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    StudentMapper studentMapper;


    public void save(Student student) {
        studentDao.save(student);
    }

    public long count() {
        return studentDao.count();
    }

    public Student getStudentById(long studentId) {
        Optional<Student> optionalGrade = studentDao.findById(studentId);
        Student student = optionalGrade.get();
        return student;
    }


    public void delete(Student student) {
        studentDao.delete(student);
    }

    public List<Student> getStudentList(int page, int limit) {
        List<Student> studentList = studentMapper.getStudentListPage(page, limit);
        return studentList;
    }

    //班级学生人数
    public long countOfGrade(int gradeId) {

        return studentMapper.countOfGrade(gradeId);
    }

    // 获取当前班级的学生
    public List<Student> getStudentListOfGrade(int page, int limit, int gradeId) {

        return studentMapper.getStudentListOfGrade(page,limit,gradeId);
    }

    public Student check(String username,String password) {
        Student student = studentMapper.findByUsernameAndPassword(username, password);
        return student;
    }

}
