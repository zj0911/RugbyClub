package com.example.controller;

import com.example.common.Result;
import com.example.entity.Count;
import com.example.service.coachInfoService;
import com.example.service.juniorInfoService;
import com.example.service.secretaryInfoService;
import com.example.service.seniorInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/count")
public class CountController {
    @Resource
    private secretaryInfoService secretaryService;
    private coachInfoService coachService;
    private juniorInfoService juniorService;
    private seniorInfoService seniorService;

    @GetMapping
    public Count getCount(){
        int count1 = secretaryService.count(); // 获取第一个表的数据数量
        int count2 = coachService.count(); // 获取第二个表的数据数量
        int count3 = juniorService.count(); // 获取第三个表的数据数量
        int count4 = seniorService.count(); // 获取第四个表的数据数量
        return new Count(count1, count2, count3, count4);
    }
}
