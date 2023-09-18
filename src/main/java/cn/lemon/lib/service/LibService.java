package cn.lemon.lib.service;

import cn.lemon.lib.dao.LibDao;
import cn.lemon.lib.entity.Lib;
import cn.lemon.lib.mapper.LibMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibService {

    @Autowired
    LibDao libDao;

    @Autowired
    LibMapper libMapper;

    public List<Lib> getLibList() {

        return libDao.findAll();
    }

    public void save(Lib lib) {
        libDao.save(lib);
    }

    public long count() {
        return libDao.count();
    }

    public Lib getLibById(long libId) {
        Optional<Lib> optionalGrade = libDao.findById(libId);
        Lib lib = optionalGrade.get();
        return lib;
    }

    public List<Lib> getLibList(int page, int limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        Page<Lib> libPage = libDao.findAll(pageable);
        List<Lib> libList = libPage.getContent();
        return libList;
    }

    public void delete(Lib lib) {
        libDao.delete(lib);
    }

    public List<Lib> getCurrentlyUnusedLabList(int startWeek, int day, int part) {
        return libMapper.getCurrentlyUnusedLabList(startWeek, day, part);
    }
}
