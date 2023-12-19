package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.nonPlayerInfoDao;
import com.example.entity.nonPlayerInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class nonPlayerInfoService {
    @Resource
    private nonPlayerInfoDao nonPlayerDao;

    //查询表单中所有数据
    public List<nonPlayerInfo> findAll() {
        return nonPlayerDao.selectAll();
    }

    //新增表单数据
    public void submit(nonPlayerInfo nonPlayer) {
        nonPlayerDao.insertSelective(nonPlayer);
    }

    //编辑表单信息
    public void edit(nonPlayerInfo nonPlayer) {
        nonPlayerDao.updateByPrimaryKeySelective(nonPlayer);
    }

    //删除表单信息
    public void deleteId(Long id) {
        nonPlayerDao.deleteByPrimaryKey(id);
    }

    //获取分页信息
    public PageInfo<nonPlayerInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<nonPlayerInfo> info = nonPlayerDao.selectAll();
        return PageInfo.of(info);
    }

    public PageInfo<nonPlayerInfo> findPageSearch(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<nonPlayerInfo> info = nonPlayerDao.findPageName(search);
        return PageInfo.of(info);
    }
}
