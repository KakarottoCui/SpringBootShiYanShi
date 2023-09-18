package cn.lemon.lib.dao;

import cn.lemon.lib.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin, Long> {

    Admin findByName(String username);
}
