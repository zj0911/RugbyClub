package com.example.dao;

import com.example.entity.trainingInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface trainingInfoDao extends Mapper<trainingInfo> {

    @Select("select * from training where coach like concat('%', #{coach}, '%' )")
    List<trainingInfo> findPageName(String search);
}
