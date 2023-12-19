package com.example.controller;

import com.example.common.Result;
import com.example.entity.coachInfo;
import com.example.entity.juniorPlayerInfo;
import com.example.service.juniorInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/juniorPlayerInfo")
public class juniorInfoController {
    @Resource
    private juniorInfoService juniorService;

    @PutMapping //完善junior player个人信息
    public Result update(@RequestBody juniorPlayerInfo juniorPlayer){
        juniorService.update(juniorPlayer);
        return Result.success();
    }

    @PostMapping //新增数据提交信息
    public Result submit(@RequestBody juniorPlayerInfo juniorPlayer){
        juniorService.submit(juniorPlayer);
        return Result.success();
    }

    @GetMapping //查询所有junior player信息
    public Result findAll(){
        List<juniorPlayerInfo> list = juniorService.findAll();
        return Result.success(list);
    }

    @GetMapping("/page") //分页查询信息
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<juniorPlayerInfo> pageInfo = juniorService.findPage(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @GetMapping("/{search}") //分页模糊查询用户信息
    public Result findPageSearch(@PathVariable String search, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<juniorPlayerInfo> pageInfo  = juniorService.findPageSearch(search, pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @DeleteMapping("/{id}") //删除用户信息接口
    public Result deleteId(@PathVariable Long id){
        juniorService.deleteId(id);
        return Result.success();
    }
}
