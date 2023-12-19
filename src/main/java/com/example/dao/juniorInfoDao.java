package com.example.dao;

import com.example.entity.juniorPlayerInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface juniorInfoDao extends Mapper<juniorPlayerInfo> {

    @Select("select * from junior_player where sru = #{sru} and password = #{password}")
    juniorPlayerInfo findSruPassword(@Param("sru") String sru, @Param("password") String password);

    @Select("select * from junior_player where name = #{name}")
    juniorPlayerInfo findName(String name);

    @Select("select * from junior_player where name like concat('%', #{name},'%')")
    List<juniorPlayerInfo> findPageSearch(String search);

    @Select("select count(*) from junior_player")
    int getCount();
}
