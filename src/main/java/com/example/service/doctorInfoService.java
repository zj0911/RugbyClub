package com.example.service;

import com.example.dao.doctorInfoDao;
import com.example.entity.doctorInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class doctorInfoService {
    @Resource
    private doctorInfoDao doctorDao;

    //更新数据
    public void update(doctorInfo doctor) {
        doctorDao.updateByPrimaryKeySelective(doctor);
    }

    public void submit(doctorInfo doctor) {//新增数据
        doctorDao.insertSelective(doctor);
    }

    //查询所有信息
    public List<doctorInfo> findAll() {
        return doctorDao.selectAll();
    }

    //删除用户信息
    public void deleteId(Long id) {
        doctorDao.deleteByPrimaryKey(id);
    }

    //加载分页信息
    public PageInfo<doctorInfo> findPage(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //查询根据num和size进行对应展示
        List<doctorInfo> infoList = doctorDao.selectAll();
        return PageInfo.of(infoList);
    }

    //模糊搜索
    public PageInfo<doctorInfo> findName(Integer pageNum, Integer pageSize, String name) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //查询根据num和size进行对应展示
        List<doctorInfo> infoList = doctorDao.findPageName(name);
        return PageInfo.of(infoList);
    }

}
