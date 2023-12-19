package com.example.controller;

import com.example.common.Result;
import com.example.entity.nonPlayerInfo;
import com.example.service.nonPlayerInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/nonPlayerInfo")
public class nonPlayerInfoController {
    @Resource
    private nonPlayerInfoService nonPlayerService;

    @GetMapping //获取数据信息
    public Result findAll(){
        List<nonPlayerInfo> infoList = nonPlayerService.findAll();
        return Result.success(infoList);
    }
    @GetMapping("/page")//获取分页信息
    public Result findPage(@RequestParam Integer pageNum, Integer pageSize){
        PageInfo<nonPlayerInfo> info = nonPlayerService.findPage(pageNum, pageSize);
        return Result.success(info);
    }
    @GetMapping("/page/{search}")//分页信息中的模糊查询
    public Result findPageSearch(@RequestParam Integer pageNum, Integer pageSize, @PathVariable String search){
        PageInfo<nonPlayerInfo> info = nonPlayerService.findPageSearch(pageNum, pageSize, search);
        return Result.success(info);
    }

    @PostMapping //新增表单数据
    public Result submit(@RequestBody nonPlayerInfo nonPlayer){
        nonPlayerService.submit(nonPlayer);
        return  Result.success();
    }

    @DeleteMapping("/{id}")//删除表单信息
    public Result deleteId(@PathVariable Long id){
        nonPlayerService.deleteId(id);
        return Result.success();
    }

    @PutMapping //编辑表单信息
    public Result edit(@RequestBody nonPlayerInfo nonPlayer){
        nonPlayerService.edit(nonPlayer);
        return Result.success();
    }

}
