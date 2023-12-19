package com.example.controller;

import com.example.common.Result;
import com.example.entity.seniorPlayerInfo;
import com.example.service.seniorInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/seniorPlayerInfo")
public class seniorInfoController {
    @Resource
    private seniorInfoService seniorService;

    @PutMapping //完善senior player个人信息
    public Result update(@RequestBody seniorPlayerInfo seniorPlayer){
        seniorService.update(seniorPlayer);
        return Result.success();
    }

    @PostMapping //新增数据提交信息
    public Result submit(@RequestBody seniorPlayerInfo seniorPlayer){
        seniorService.submit(seniorPlayer);
        return Result.success();
    }

    @GetMapping //查询所有senior player信息
    public Result findAll(){
        List<seniorPlayerInfo> list = seniorService.findAll();
        return Result.success(list);
    }

    @GetMapping("/page") //分页查询信息
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<seniorPlayerInfo> pageInfo = seniorService.findPage(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @GetMapping("/{search}") //分页模糊查询用户信息
    public Result findPageSearch(@PathVariable String search, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<seniorPlayerInfo> pageInfo  = seniorService.findPageSearch(search, pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @DeleteMapping("/{id}") //删除用户信息接口
    public Result deleteId(@PathVariable Long id){
        seniorService.deleteId(id);
        return Result.success();
    }
}
