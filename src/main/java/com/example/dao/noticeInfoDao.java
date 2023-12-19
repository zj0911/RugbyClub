package com.example.dao;


import com.example.entity.noticeInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface noticeInfoDao extends Mapper<noticeInfo> {

    @Select("select * from notice where name like concat('%', #{name}, '%' )")
    List<noticeInfo> findPageName(String search);

    @Select("select * from notice order by date desc limit 3")
    List<noticeInfo> findTop();
}
