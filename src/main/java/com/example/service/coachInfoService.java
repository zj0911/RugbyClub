package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.coachInfoDao;
import com.example.entity.Account;
import com.example.entity.coachInfo;
import com.example.entity.secretaryInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class coachInfoService {
    @Resource
    private coachInfoDao coachDao;

    //注册账户
    public void register(coachInfo coach) {
        //1. 检查输入用户是否有重名
        coachInfo info = coachDao.findByName(coach.getName());
        if(ObjectUtil.isNotEmpty(info)){
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        //2. 没有重名，在数据库中新增数据
        coachDao.insertSelective(coach);
    }

    //登录账户
    public Account login(String sru, String password) {
        coachInfo coach = coachDao.findSruPassword(sru, password);
        if(ObjectUtil.isEmpty(coach)){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return coach;
    }

    //通过id查询用户信息
    public coachInfo findId(Long id) {
        return coachDao.selectByPrimaryKey(id);
    }

    //完善coachInfo的个人信息
    public void update(coachInfo coach) {
        coachDao.updateByPrimaryKeySelective(coach);
    }

    //查询所有coach信息
    public List<coachInfo> findAll() {
        return coachDao.selectAll();
    }

    //在数据库中插入新增的数据
    public void submit(coachInfo coach) {//新增数据
        //2. 用户重名
        //2.1. 查询数据库是否存在
        coachInfo info = coachDao.findByName(coach.getName());
        if(ObjectUtil.isNotEmpty(info)){ //用户存在
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if(ObjectUtil.isEmpty(info)){//1. 新增用户设置初始密码
            coach.setPassword("123456");
            coach.setType(2);
        }
        coachDao.insertSelective(coach);
    }

    //根据主键删除用户信息
    public void deleteId(Long id) {
        coachDao.deleteByPrimaryKey(id);
    }

    //查询分页信息
    public PageInfo<coachInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<coachInfo> info = coachDao.selectAll();
        return PageInfo.of(info);
    }

    //分页信息中的模糊查询
    public PageInfo<coachInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<coachInfo> infoList = coachDao.findPageSearch(search);
        return PageInfo.of(infoList);
    }

    public int count() {
        return coachDao.getCount();
    }
}
