package cn.lemon.lib.dao;

import cn.lemon.lib.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectDao extends JpaRepository<Subject, Long> {

    Page<Subject> findAll(Pageable pageable);
}
