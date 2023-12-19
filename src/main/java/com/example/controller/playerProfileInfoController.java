package com.example.controller;

import com.example.common.Result;
import com.example.entity.playerProfileInfo;
import com.example.service.playerProfileInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/playerProfileInfo")
public class playerProfileInfoController {
    @Resource
    private playerProfileInfoService playerProfileService;

    @GetMapping //获取数据信息
    public Result findAll(){
        List<playerProfileInfo> infoList = playerProfileService.findAll();
        return Result.success(infoList);
    }
    @GetMapping("/page")//获取分页信息
    public Result findPage(@RequestParam Integer pageNum, Integer pageSize){
        PageInfo<playerProfileInfo> info = playerProfileService.findPage(pageNum, pageSize);
        return Result.success(info);
    }
    @GetMapping("/page/{search}")//分页信息中的模糊查询
    public Result findPageSearch(@RequestParam Integer pageNum, Integer pageSize, @PathVariable String search){
        PageInfo<playerProfileInfo> info = playerProfileService.findPageSearch(pageNum, pageSize, search);
        return Result.success(info);
    }

    @PostMapping //新增表单数据
    public Result submit(@RequestBody playerProfileInfo playerProfile){
        playerProfileService.submit(playerProfile);
        return  Result.success();
    }

    @DeleteMapping("/{id}")//删除表单信息
    public Result deleteId(@PathVariable Long id){
        playerProfileService.deleteId(id);
        return Result.success();
    }

    @PutMapping //编辑表单信息
    public Result edit(@RequestBody playerProfileInfo playerProfile){
        playerProfileService.edit(playerProfile);
        return Result.success();
    }

}
