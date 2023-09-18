package cn.lemon.lib.mapper;

import cn.lemon.lib.entity.Lib;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibMapper {

    List<Lib> getCurrentlyUnusedLabList(
            @Param("startWeek") int startWeek,
            @Param("day") int day,
            @Param("part") int part);
}
