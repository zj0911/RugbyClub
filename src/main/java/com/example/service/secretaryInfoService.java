package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.secretaryInfoDao;
import com.example.entity.Account;
import com.example.entity.secretaryInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class secretaryInfoService {
    @Resource
    private secretaryInfoDao secretaryDao;

    //登录账号
    public Account login(String sru, String password) {
        secretaryInfo secretary = secretaryDao.findSRUPassword(sru, password);
        if(ObjectUtil.isEmpty(secretary)){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return secretary;
    }

    public secretaryInfo findId(Long id) {
        return secretaryDao.selectByPrimaryKey(id);
    }

    //更新数据
    public void update(secretaryInfo secretary) {
        secretaryDao.updateByPrimaryKeySelective(secretary);
    }

    public void submit(secretaryInfo secretary) {//新增数据
        //2. 用户重名
        //2.1. 查询数据库是否存在
        secretaryInfo info = secretaryDao.findName(secretary.getName());
        if(ObjectUtil.isNotEmpty(info)){ //用户存在
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if(ObjectUtil.isEmpty(info)){//1. 新增用户设置初始密码
            secretary.setPassword("123456");
            secretary.setType(1);
        }
        secretaryDao.insertSelective(secretary);
    }

    public List<secretaryInfo> findAll() {
        return secretaryDao.selectAll();
    }

    public void deleteId(Long id) {
        secretaryDao.deleteByPrimaryKey(id);
    }

    public PageInfo<secretaryInfo> findPage(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //查询根据num和size进行对应展示
        List<secretaryInfo> infoList = secretaryDao.selectAll();
        return PageInfo.of(infoList);
    }

    public PageInfo<secretaryInfo> findName(Integer pageNum, Integer pageSize, String name) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //查询根据num和size进行对应展示
        List<secretaryInfo> infoList = secretaryDao.findPageName(name);
        return PageInfo.of(infoList);
    }

    public int count() {
        return secretaryDao.getCount();
    }
}
