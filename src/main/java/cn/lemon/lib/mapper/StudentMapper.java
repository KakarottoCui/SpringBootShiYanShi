package cn.lemon.lib.mapper;

import cn.lemon.lib.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    //List<Student> getStudentListByGradeId(long gradeId);

    List<Student> getStudentListPage(@Param("page")int page, @Param("limit")int limit);

    long countOfGrade(@Param("gradeId")int gradeId);

    List<Student> getStudentListOfGrade(@Param("page")int page, @Param("limit")int limit,@Param("gradeId")int gradeId);

    Student findByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
}
