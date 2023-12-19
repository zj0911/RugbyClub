package com.example.dao;


import com.example.entity.gameInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface gameInfoDao extends Mapper<gameInfo> {

    @Select("select * from game where opposition like concat('%', #{opposition}, '%' )")
    List<gameInfo> findPageName(String search);
}
