package cn.lemon.lib.dao;

import cn.lemon.lib.entity.Lib;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibDao extends JpaRepository<Lib, Long> {
}
