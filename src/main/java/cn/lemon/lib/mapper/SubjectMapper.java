package cn.lemon.lib.mapper;

import cn.lemon.lib.entity.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMapper {

    //List<Student> getStudentListByGradeId(long gradeId);

    List<Subject> getSubjectListPage(@Param("page") int page, @Param("limit") int limit);

}
