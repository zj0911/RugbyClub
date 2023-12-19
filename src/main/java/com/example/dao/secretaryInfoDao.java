package com.example.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.example.entity.secretaryInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface secretaryInfoDao extends Mapper<secretaryInfo> {
    @Select("select * from secretary where name = #{name}")
    secretaryInfo findName(String name);

    @Select("select * from secretary where sru = #{sru} and password = #{password}")
    secretaryInfo findSRUPassword(@Param("sru") String sru, @Param("password") String password);

    @Select("select * from secretary where name like concat('%', #{name},'%')")
    List<secretaryInfo> findPageName(String search);

    @Select("select count(*) from secretary")
    int getCount();
}
