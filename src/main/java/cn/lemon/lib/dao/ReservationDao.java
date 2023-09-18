package cn.lemon.lib.dao;

import cn.lemon.lib.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDao extends JpaRepository<Reservation, Long> {
}
