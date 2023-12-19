package com.example.dao;

import com.example.entity.doctorInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface doctorInfoDao extends Mapper<doctorInfo> {

    @Select("select * from doctor where name like concat('%', #{name},'%')")
    List<doctorInfo> findPageName(String name);
}
