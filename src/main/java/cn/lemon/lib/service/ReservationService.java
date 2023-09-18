package cn.lemon.lib.service;

import cn.lemon.lib.dao.ReservationDao;
import cn.lemon.lib.entity.Reservation;
import cn.lemon.lib.entity.Student;
import cn.lemon.lib.entity.Teacher;
import cn.lemon.lib.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationDao reservationDao;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    ReservationMapper reservationMapper;

    public void save(Reservation  reservation) {
        reservationDao.save(reservation);
    }

    public long count() {
        return reservationDao.count();
    }

    public long studentCount(long userId) {
        return reservationMapper.studentCount(userId);
    }

    public long teacherCount(long userId) {
        return reservationMapper.teacherCount(userId);
    }



    public Reservation getReservationId(long reservationId) {
        Optional<Reservation> optionalReservation = reservationDao.findById(reservationId);
        Reservation reservation = optionalReservation.get();
        return reservation;
    }

    public List<Reservation> getReservationList(int page, int limit) {
        // 获取预约人姓名
        List<Reservation> reservationList = reservationMapper.getReservationList(page, limit);
        for (Reservation reservation : reservationList) {
            if (reservation.getType() == 0) {
                Student student = studentService.getStudentById(reservation.getUserId());
                reservation.setReservationName(student.getUsername());
            } else {
                Teacher teacher = teacherService.getTeacherById(reservation.getUserId());
                reservation.setReservationName(teacher.getUsername());
            }
        }
        // 获取预约类型
        for (Reservation reservation : reservationList) {
            if (reservation.getType() == 0) {
                reservation.setTypeName("个人预约");
            } else {
                reservation.setTypeName("教师预约");
            }
        }

        // 是否审核
        for (Reservation reservation : reservationList) {
            if (reservation.getStatus() == 0) {
                reservation.setIsAudit("未审核");
            } else {
                reservation.setIsAudit("已审核");
            }
        }
        return reservationList;
    }


    public List<Reservation> getStudentReservationList(int page, int limit, long userId) {
        // 获取预约人姓名
        List<Reservation> reservationList = reservationMapper.getStudentReservationList(page, limit,userId);
        for (Reservation reservation : reservationList) {
            Student student = studentService.getStudentById(reservation.getUserId());
            reservation.setReservationName(student.getUsername());
            reservation.setTypeName("个人预约");
        }
        // 是否审核
        for (Reservation reservation : reservationList) {
            if (reservation.getStatus() == 0) {
                reservation.setIsAudit("未审核");
            } else {
                reservation.setIsAudit("已审核");
            }
        }
        return reservationList;
    }

    public List<Reservation> getTeacherReservationList(int page, int limit, long userId) {
        // 获取预约人姓名
        List<Reservation> reservationList = reservationMapper.getTeacherReservationList(page, limit,userId);
        for (Reservation reservation : reservationList) {
            Teacher teacher = teacherService.getTeacherById(reservation.getUserId());
            reservation.setReservationName(teacher.getUsername());
            reservation.setTypeName("教师预约");
        }
        // 是否审核
        for (Reservation reservation : reservationList) {
            if (reservation.getStatus() == 0) {
                reservation.setIsAudit("未审核");
            } else {
                reservation.setIsAudit("已审核");
            }
        }
        return reservationList;
    }

    public long noneAuditCount() {
        return reservationMapper.noneAuditcount();
    }

    public List<Reservation> getNoneAuditReservationList(int page, int limit) {
        // 获取预约人姓名
        List<Reservation> reservationList = reservationMapper.getNoneAuditReservationList(page, limit);
        for (Reservation reservation : reservationList) {
            if (reservation.getType() == 0) {
                Student student = studentService.getStudentById(reservation.getUserId());
                reservation.setReservationName(student.getUsername());
            } else {
                Teacher teacher = teacherService.getTeacherById(reservation.getUserId());
                reservation.setReservationName(teacher.getUsername());
            }
        }
        // 获取预约类型
        for (Reservation reservation : reservationList) {
            if (reservation.getType() == 0) {
                reservation.setTypeName("个人预约");
            } else {
                reservation.setTypeName("教师预约");
            }
        }

        return reservationList;
    }


    public void delete(Reservation reservation) {
        reservationDao.delete(reservation);
    }
}
