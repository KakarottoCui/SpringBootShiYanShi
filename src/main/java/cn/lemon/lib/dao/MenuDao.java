package cn.lemon.lib.dao;

import cn.lemon.lib.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface MenuDao extends JpaRepository<Menu, Integer> {

    Page<Menu> findAll(Pageable pageable);
}
