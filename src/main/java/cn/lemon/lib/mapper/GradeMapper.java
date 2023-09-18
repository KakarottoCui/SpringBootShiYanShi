package cn.lemon.lib.mapper;

import cn.lemon.lib.entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeMapper {

    List<Grade> getCurrentGradeOfTeacher(
            @Param("teacherId") int teacherId,
            @Param("startWeek") int startWeek,
            @Param("day") int day,
            @Param("part") int part);

    long countOfMyGrade(@Param("teacherId")long teacher);

    List<Grade> getGradeListOfMine(@Param("teacherId")long teacher,@Param("page") int page, @Param("limit") int limit);
}
