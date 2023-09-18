package cn.lemon.lib.mapper;

import cn.lemon.lib.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservationMapper {

    List<Reservation> getReservationList(@Param("page") int page, @Param("limit") int limit);

    List<Reservation> getStudentReservationList(@Param("page") int page, @Param("limit") int limit,
                                                @Param("userId") long userId);

    List<Reservation> getTeacherReservationList(@Param("page") int page, @Param("limit") int limit,
                                                @Param("userId") long userId);

    List<Reservation> getNoneAuditReservationList(@Param("page") int page, @Param("limit") int limit);

    long noneAuditcount();

    long teacherCount(@Param("userId")long userId);

    long studentCount(@Param("userId")long userId);
}
