package com.example.service;

import com.example.dao.trainingInfoDao;
import com.example.entity.trainingInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class trainingInfoService {
    @Resource
    private trainingInfoDao trainingDao;

    //查询表单中所有数据
    public List<trainingInfo> findAll() {
        return trainingDao.selectAll();
    }

    //新增表单数据
    public void submit(trainingInfo training) {
        trainingDao.insertSelective(training);
    }

    //编辑表单信息
    public void edit(trainingInfo training) {
        trainingDao.updateByPrimaryKeySelective(training);
    }

    //删除表单信息
    public void deleteId(Long id) {
        trainingDao.deleteByPrimaryKey(id);
    }

    //获取分页信息
    public PageInfo<trainingInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<trainingInfo> info = trainingDao.selectAll();
        return PageInfo.of(info);
    }

    public PageInfo<trainingInfo> findPageSearch(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<trainingInfo> info = trainingDao.findPageName(search);
        return PageInfo.of(info);
    }
}
