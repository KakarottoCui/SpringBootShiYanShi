package cn.lemon.lib.dao;

import cn.lemon.lib.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeDao extends JpaRepository<Grade, Long> {
}
