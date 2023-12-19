package com.example.dao;

import com.example.entity.nonPlayerInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface nonPlayerInfoDao extends Mapper<nonPlayerInfo> {
    @Select("select * from non_player where name like concat('%', #{name}, '%')")
    List<nonPlayerInfo> findPageName(String search);
}
