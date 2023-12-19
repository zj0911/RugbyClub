package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.juniorInfoDao;
import com.example.entity.Account;
import com.example.entity.coachInfo;
import com.example.entity.juniorPlayerInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class juniorInfoService {
    @Resource
    private juniorInfoDao juniorDao;

    //账号登录
    public Account login(String sru, String password) {
        //查找是否已经存在账户
        juniorPlayerInfo juniorPlayer = juniorDao.findSruPassword(sru, password);
        if(ObjectUtil.isEmpty(juniorPlayer)){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return juniorPlayer;
    }

    //注册账户
    public void register(juniorPlayerInfo juniorPlayer) {
        //1. 检查输入用户是否有重名
        juniorPlayerInfo info = juniorDao.findName(juniorPlayer.getName());
        if(ObjectUtil.isNotEmpty(info)){
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        //2. 没有重名，在数据库中新增数据
        juniorDao.insertSelective(juniorPlayer);
    }

    //完善个人信息
    public void update(juniorPlayerInfo juniorPlayer) {
        juniorDao.updateByPrimaryKeySelective(juniorPlayer);
    }

    //通过id查找个人信息
    public juniorPlayerInfo findId(Long id) {
        return juniorDao.selectByPrimaryKey(id);
    }

    //在数据库中插入新增的数据
    public void submit(juniorPlayerInfo juniorPlayer) {
        //2. 用户重名
        //2.1. 查询数据库是否存在
        juniorPlayerInfo info = juniorDao.findName(juniorPlayer.getName());
        if(ObjectUtil.isNotEmpty(info)){ //用户存在
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if(ObjectUtil.isEmpty(info)){//1. 新增用户设置初始密码
            juniorPlayer.setPassword("123456");
            juniorPlayer.setType(3);
        }
        juniorDao.insertSelective(juniorPlayer);
    }

    public List<juniorPlayerInfo> findAll() {
        return juniorDao.selectAll();
    }

    public PageInfo<juniorPlayerInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<juniorPlayerInfo> info = juniorDao.selectAll();
        return PageInfo.of(info);
    }

    public PageInfo<juniorPlayerInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<juniorPlayerInfo> infoList = juniorDao.findPageSearch(search);
        return PageInfo.of(infoList);
    }

    public void deleteId(Long id) {
        juniorDao.deleteByPrimaryKey(id);
    }

    public int count() {
        return juniorDao.getCount();
    }
}
