package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.gameInfo;
import com.example.service.gameInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gameInfo")
public class gameInfoController {
    @Resource
    private gameInfoService gameService;

    @GetMapping("/page")//获取分页信息
    public Result findPage(@RequestParam Integer pageNum, Integer pageSize){
        PageInfo<gameInfo> info = gameService.findPage(pageNum, pageSize);
        return Result.success(info);
    }
    @GetMapping("/page/{search}")//分页信息中的模糊查询
    public Result findPageSearch(@RequestParam Integer pageNum, Integer pageSize, @PathVariable String search){
        PageInfo<gameInfo> info = gameService.findPageSearch(pageNum, pageSize, search);
        return Result.success(info);
    }
    @GetMapping("/echarts/pie")
    public Result findChart(){
        //获取所有数据
        List<gameInfo> infoList = gameService.findAll();
        Map<String, Long> collect = infoList.stream()
                .filter(x-> ObjectUtil.isNotEmpty(x.getResult()))
                .collect(Collectors.groupingBy(gameInfo::getResult, Collectors.counting()));
        //返回前端
        List<Map<String, Object>> mapList = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(collect)){
            for(String key: collect.keySet()){
                Map<String, Object> map = new HashMap<>();
                map.put("name", key);
                map.put("value", collect.get(key));
                mapList.add(map);
            }
        }
        return Result.success(mapList);
    }

    @PostMapping //新增表单数据
    public Result submit(@RequestBody gameInfo game){
        gameService.submit(game);
        return  Result.success();
    }

    @DeleteMapping("/{id}")//删除表单信息
    public Result deleteId(@PathVariable Long id){
        gameService.deleteId(id);
        return Result.success();
    }

    @PutMapping //编辑表单信息
    public Result edit(@RequestBody gameInfo game){
        gameService.edit(game);
        return Result.success();
    }
}
