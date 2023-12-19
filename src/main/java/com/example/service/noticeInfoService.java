package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.dao.noticeInfoDao;
import com.example.entity.noticeInfo;
import com.example.entity.trainingInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class noticeInfoService {
    @Resource
    private noticeInfoDao noticeDao;

    //返回所有数据
    public List<noticeInfo> findTop() {
        return noticeDao.findTop();
    }

    //新增数据
    public void submit(noticeInfo notice) {
        notice.setDate(DateUtil.now());
        noticeDao.insertSelective(notice);
    }

    //删除数据
    public void deleteId(Long id) {
        noticeDao.deleteByPrimaryKey(id);
    }

    //编辑信息
    public void edit(noticeInfo notice) {
        notice.setDate(DateUtil.now());
        noticeDao.updateByPrimaryKeySelective(notice);
    }

    //获取分页信息
    public PageInfo<noticeInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<noticeInfo> info = noticeDao.selectAll();
        return PageInfo.of(info);
    }

    public PageInfo<noticeInfo> findPageSearch(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<noticeInfo> info = noticeDao.findPageName(search);
        return PageInfo.of(info);
    }

}
