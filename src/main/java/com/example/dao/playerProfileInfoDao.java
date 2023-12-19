package com.example.dao;

import com.example.entity.playerProfileInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface playerProfileInfoDao extends Mapper<playerProfileInfo> {

    @Select("select * from player_profile where name like concat('%', #{coach}, '%' )")
    List<playerProfileInfo> findPageName(String search);
}
