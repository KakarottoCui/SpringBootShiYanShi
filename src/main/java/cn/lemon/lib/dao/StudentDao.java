package cn.lemon.lib.dao;

import cn.lemon.lib.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Long> {
}
