package com.example.controller;

import com.example.common.Result;
import com.example.entity.doctorInfo;
import com.example.service.doctorInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/doctorInfo")
public class doctorInfoController {
    @Resource
    private doctorInfoService doctorService;

    @PostMapping//新增保存
    public Result submit(@RequestBody doctorInfo doctor){
        doctorService.submit(doctor);
        return Result.success();
    }

    @PutMapping //更新用户数据
    public Result update(@RequestBody doctorInfo doctor){
        doctorService.update(doctor);
        return Result.success();
    }


    @DeleteMapping("/{id}") //删除用户
    public Result deleteItem(@PathVariable Long id){
        doctorService.deleteId(id);
        return Result.success();
    }

    @GetMapping //获取表单数据
    public Result findAll(){
        List<doctorInfo> list = doctorService.findAll();
        return Result.success(list);
    }
    @GetMapping("/page") //获取表单分页
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<doctorInfo> info = doctorService.findPage(pageNum, pageSize);
        return Result.success(info);
    }

    @GetMapping("/page/{name}") //根据用户姓名搜索
    public Result findName(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @PathVariable String name){
        PageInfo<doctorInfo> info = doctorService.findName(pageNum, pageSize,name);
        return Result.success(info);
    }

}
