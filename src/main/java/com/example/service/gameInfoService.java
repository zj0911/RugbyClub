package com.example.service;

import com.example.dao.gameInfoDao;
import com.example.entity.gameInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class gameInfoService {
    @Resource
    private gameInfoDao gameDao;

    //查询表单中所有数据
    public List<gameInfo> findAll() {
        return gameDao.selectAll();
    }

    //新增表单数据
    public void submit(gameInfo game) {
        gameDao.insertSelective(game);
    }

    //编辑表单信息
    public void edit(gameInfo game) {
        gameDao.updateByPrimaryKeySelective(game);
    }

    //删除表单信息
    public void deleteId(Long id) {
        gameDao.deleteByPrimaryKey(id);
    }

    //获取分页信息
    public PageInfo<gameInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<gameInfo> info = gameDao.selectAll();
        return PageInfo.of(info);
    }

    public PageInfo<gameInfo> findPageSearch(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<gameInfo> info = gameDao.findPageName(search);
        return PageInfo.of(info);
    }
}
