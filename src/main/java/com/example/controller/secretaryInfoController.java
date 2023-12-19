package com.example.controller;

import com.example.common.Result;
import com.example.entity.secretaryInfo;
import com.example.service.secretaryInfoService;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.runtime.PropertyMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/secretaryInfo")
public class secretaryInfoController {
    @Resource
    private secretaryInfoService secretaryService;

    @PostMapping//secretary新增保存
    public Result submit(@RequestBody secretaryInfo secretary){
        secretaryService.submit(secretary);
        return Result.success();
    }

    @PutMapping //更新用户数据
    public Result update(@RequestBody secretaryInfo secretary){
        secretaryService.update(secretary);
        return Result.success();
    }

    @GetMapping //获取表单数据
    public Result findAll(){
        List<secretaryInfo> list = secretaryService.findAll();
        return Result.success(list);
    }

    @DeleteMapping("/{id}") //删除用户
    public Result deleteItem(@PathVariable Long id){
        secretaryService.deleteId(id);
        return Result.success();
    }

    @GetMapping("/page") //获取表单分页
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<secretaryInfo> info = secretaryService.findPage(pageNum, pageSize);
        return Result.success(info);
    }

    @GetMapping("/page/{name}") //根据用户姓名搜索
    public Result findName(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @PathVariable String name){
        PageInfo<secretaryInfo> info = secretaryService.findName(pageNum, pageSize,name);
        return Result.success(info);
    }

}
