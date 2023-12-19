package com.example.controller;

import com.example.common.Result;
import com.example.entity.trainingInfo;
import com.example.service.trainingInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/trainingInfo")
public class trainingInfoController {
    @Resource
    private trainingInfoService trainingService;

    @GetMapping //获取数据信息
    public Result findAll(){
        List<trainingInfo> infoList = trainingService.findAll();
        return Result.success(infoList);
    }
    @GetMapping("/page")//获取分页信息
    public Result findPage(@RequestParam Integer pageNum, Integer pageSize){
        PageInfo<trainingInfo> info = trainingService.findPage(pageNum, pageSize);
        return Result.success(info);
    }
    @GetMapping("/page/{search}")//分页信息中的模糊查询
    public Result findPageSearch(@RequestParam Integer pageNum, Integer pageSize, @PathVariable String search){
        PageInfo<trainingInfo> info = trainingService.findPageSearch(pageNum, pageSize, search);
        return Result.success(info);
    }

    @PostMapping //新增表单数据
    public Result submit(@RequestBody trainingInfo training){
        trainingService.submit(training);
        return  Result.success();
    }

    @DeleteMapping("/{id}")//删除表单信息
    public Result deleteId(@PathVariable Long id){
        trainingService.deleteId(id);
        return Result.success();
    }

    @PutMapping //编辑表单信息
    public Result edit(@RequestBody trainingInfo training){
        trainingService.edit(training);
        return Result.success();
    }

}
