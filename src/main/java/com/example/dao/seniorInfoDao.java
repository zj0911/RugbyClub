package com.example.dao;

import com.example.entity.seniorPlayerInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface seniorInfoDao extends Mapper<seniorPlayerInfo> {

    @Select("select * from senior_player where sru = #{sru} and password = #{password}")
    seniorPlayerInfo findSruPassword(@Param("sru") String sru, @Param("password") String password);

    @Select("select * from senior_player where name = #{name}")
    seniorPlayerInfo findName(String name);

    @Select("select * from senior_player where name like concat('%', #{name},'%')")
    List<seniorPlayerInfo> findPageSearch(String search);

    @Select("select count(*) from senior_palyer")
    int getCount();
}
