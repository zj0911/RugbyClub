package com.example.controller;

import com.example.common.Result;
import com.example.entity.coachInfo;
import com.example.entity.noticeInfo;
import com.example.entity.trainingInfo;
import com.example.service.noticeInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/noticeInfo")
public class noticeInfoController {
    @Resource
    private noticeInfoService noticeService;

    @GetMapping //查询所有信息
    public Result findTop(){
        List<noticeInfo> list = noticeService.findTop();
        return Result.success(list);
    }
    @GetMapping("/page")//获取分页信息
    public Result findPage(@RequestParam Integer pageNum, Integer pageSize){
        PageInfo<noticeInfo> info = noticeService.findPage(pageNum, pageSize);
        return Result.success(info);
    }
    @GetMapping("/page/{search}")//分页信息中的模糊查询
    public Result findPageSearch(@RequestParam Integer pageNum, Integer pageSize, @PathVariable String search){
        PageInfo<noticeInfo> info = noticeService.findPageSearch(pageNum, pageSize, search);
        return Result.success(info);
    }

    @PostMapping //新增信息
    public Result submit(@RequestBody noticeInfo notice){
        noticeService.submit(notice);
        return Result.success();
    }

    @DeleteMapping("/{id}") //删除信息
    public Result deleteId(@PathVariable Long id){
        noticeService.deleteId(id);
        return  Result.success();
    }

    @PutMapping //编辑信息
    public Result edit(@RequestBody noticeInfo notice){
        noticeService.edit(notice);
        return Result.success();
    }
}
