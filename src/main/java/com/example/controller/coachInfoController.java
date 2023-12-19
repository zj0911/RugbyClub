package com.example.controller;

import com.example.common.Result;
import com.example.entity.coachInfo;
import com.example.entity.secretaryInfo;
import com.example.service.coachInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/coachInfo")
public class coachInfoController {
    @Resource
    private coachInfoService coachService;

    @PutMapping //完善coachInfo的个人信息
    public Result update(@RequestBody coachInfo coach){
        coachService.update(coach);
        return Result.success();
    }

    @PostMapping //新增数据提交信息
    public Result submit(@RequestBody coachInfo coach){
        coachService.submit(coach);
        return Result.success();
    }

    @GetMapping //查询所有coach信息
    public Result findAll(){
        List<coachInfo> list = coachService.findAll();
        return Result.success(list);
    }

    @GetMapping("/page") //分页查询信息
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<coachInfo> pageInfo = coachService.findPage(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @GetMapping("/{search}") //分页模糊查询用户信息
    public Result findPageSearch(@PathVariable String search, @RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<coachInfo> pageInfo  = coachService.findPageSearch(search, pageNum, pageSize);
        return Result.success(pageInfo);
    }


    @DeleteMapping("/{id}") //删除用户信息接口
    public Result deleteId(@PathVariable Long id){
        coachService.deleteId(id);
        return Result.success();
    }

}
