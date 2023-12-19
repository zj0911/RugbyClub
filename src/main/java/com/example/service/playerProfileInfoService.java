package com.example.service;

import com.example.dao.playerProfileInfoDao;
import com.example.entity.playerProfileInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class playerProfileInfoService {
    @Resource
    private playerProfileInfoDao playerProfileDao;

    //查询表单中所有数据
    public List<playerProfileInfo> findAll() {
        return playerProfileDao.selectAll();
    }

    //新增表单数据
    public void submit(playerProfileInfo playerProfile) {
        playerProfileDao.insertSelective(playerProfile);
    }

    //编辑表单信息
    public void edit(playerProfileInfo playerProfile) {
        playerProfileDao.updateByPrimaryKeySelective(playerProfile);
    }

    //删除表单信息
    public void deleteId(Long id) {
        playerProfileDao.deleteByPrimaryKey(id);
    }

    //获取分页信息
    public PageInfo<playerProfileInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<playerProfileInfo> info = playerProfileDao.selectAll();
        return PageInfo.of(info);
    }

    public PageInfo<playerProfileInfo> findPageSearch(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<playerProfileInfo> info = playerProfileDao.findPageName(search);
        return PageInfo.of(info);
    }
}
