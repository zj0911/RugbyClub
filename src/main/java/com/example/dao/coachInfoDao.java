package com.example.dao;


import com.example.entity.coachInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface coachInfoDao extends Mapper<coachInfo> {

    @Select("select * from coach where name = #{name}")
    coachInfo findByName(String name);

    @Select("select * from coach where sru = #{sru} and password = #{password}")
    coachInfo findSruPassword(@Param("sru") String sru, @Param("password") String password);

    @Select("select * from coach where name like concat('%', #{name},'%')")
    List<coachInfo> findPageSearch(String search);

    @Select("select count(*) from coach")
    int getCount();
}
