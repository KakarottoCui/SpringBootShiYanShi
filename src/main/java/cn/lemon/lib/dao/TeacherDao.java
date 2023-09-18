package cn.lemon.lib.dao;

import cn.lemon.lib.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDao extends JpaRepository<Teacher, Long> {

    Page<Teacher> findAll(Pageable pageable);

    Teacher findByUsername(String username);
}
