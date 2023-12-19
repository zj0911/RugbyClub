package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.seniorInfoDao;
import com.example.entity.Account;
import com.example.entity.seniorPlayerInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class seniorInfoService {
    @Resource
    private seniorInfoDao seniorDao;

    //账号登录
    public Account login(String sru, String password) {
        //查找是否已经存在账户
        seniorPlayerInfo seniorPlayer = seniorDao.findSruPassword(sru, password);
        if(ObjectUtil.isEmpty(seniorPlayer)){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return seniorPlayer;
    }

    //注册账户
    public void register(seniorPlayerInfo seniorPlayer) {
        //1. 检查输入用户是否有重名
        seniorPlayerInfo info = seniorDao.findName(seniorPlayer.getName());
        if(ObjectUtil.isNotEmpty(info)){
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        //2. 没有重名，在数据库中新增数据
        seniorDao.insertSelective(seniorPlayer);
    }

    //完善个人信息
    public void update(seniorPlayerInfo seniorPlayer) {
        seniorDao.updateByPrimaryKeySelective(seniorPlayer);
    }

    //通过id查找个人信息
    public seniorPlayerInfo findId(Long id) {
        return seniorDao.selectByPrimaryKey(id);
    }

    //在数据库中插入新增的数据
    public void submit(seniorPlayerInfo seniorPlayer) {
        //2. 用户重名
        //2.1. 查询数据库是否存在
        seniorPlayerInfo info = seniorDao.findName(seniorPlayer.getName());
        if(ObjectUtil.isNotEmpty(info)){ //用户存在
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if(ObjectUtil.isEmpty(info)){//1. 新增用户设置初始密码
            seniorPlayer.setPassword("123456");
            seniorPlayer.setType(4);
        }
        seniorDao.insertSelective(seniorPlayer);
    }

    public List<seniorPlayerInfo> findAll() {
        return seniorDao.selectAll();
    }

    public PageInfo<seniorPlayerInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<seniorPlayerInfo> info = seniorDao.selectAll();
        return PageInfo.of(info);
    }

    public PageInfo<seniorPlayerInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<seniorPlayerInfo> infoList = seniorDao.findPageSearch(search);
        return PageInfo.of(infoList);
    }

    public void deleteId(Long id) {
        seniorDao.deleteByPrimaryKey(id);
    }

    public int count() {
        return seniorDao.getCount();
    }
}
