package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.*;
import com.example.service.coachInfoService;
import com.example.service.juniorInfoService;
import com.example.service.secretaryInfoService;
import com.example.service.seniorInfoService;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跟账号相关接口
 */
@RequestMapping
@RestController
public class AccountController {

    @Resource
    private secretaryInfoService secretaryService;
    @Resource
    private coachInfoService coachService;
    @Resource
    private juniorInfoService juniorService;
    @Resource
    private seniorInfoService seniorService;

    //图形验证功能
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //png类型
        SpecCaptcha captcha = new SpecCaptcha(135,33,4);
        captcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        CaptchaUtil.out(captcha,request,response);
    }

    /**
     * 描述：登录接口
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account user, HttpServletRequest request){
        //图形验证码
        if(!com.wf.captcha.utils.CaptchaUtil.ver(user.getVerCode(),request)){
            //清除session中验证码
            CaptchaUtil.clear(request);
            return Result.error("1001","Error Captcha!");
        }
        //校验信息是否填写
        if(ObjectUtil.isEmpty(user.getSRU()) || ObjectUtil.isEmpty(user.getPassword()) ||ObjectUtil.isEmpty(user.getType())){
            return Result.error("-1","Please complete information!");
        }
        //获取用户类型
        int type = user.getType();
        //登录用户
        Account loginUser = new Account();
        if(1 == type){
            //Secretary
            loginUser = secretaryService.login(user.getSRU(), user.getPassword());
        }
        if(2 == type){
            //Coach
            loginUser = coachService.login(user.getSRU(),user.getPassword());
        }
        if(3 == type){
            // Junior Player
            loginUser = juniorService.login(user.getSRU(),user.getPassword());
        }
        if(4 == type){
            //Senior Player
            loginUser = seniorService.login(user.getSRU(),user.getPassword());
        }
        request.getSession().setAttribute("user",loginUser); //session备份用户信息
        return Result.success(loginUser);
    }

    /**
     * 注册信息
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account user, HttpServletRequest request){
        //校验信息是否填写
        if(ObjectUtil.isEmpty(user.getName()) || ObjectUtil.isEmpty(user.getPassword()) ||ObjectUtil.isEmpty(user.getType())
                ||ObjectUtil.isEmpty(user.getEmail()) ||ObjectUtil.isEmpty(user.getTel())){
            return Result.error("-1","Please complete information!");
        }
        //获取用户类型
        int type = user.getType();
        if(2 == type){
            //Coach
            coachInfo coach = new coachInfo(); //将user的属性复制到coach
            BeanUtils.copyProperties(user,coach);
            coachService.register(coach);
        }
        if(3 == type){
            //Junior Player
            juniorPlayerInfo juniorPlayer = new juniorPlayerInfo();
            //将user的属性复制到coach
            BeanUtils.copyProperties(user,juniorPlayer);
            juniorService.register(juniorPlayer);
        }
        if(4 == type){
            //Senior Player
            seniorPlayerInfo seniorPlayer = new seniorPlayerInfo();
            BeanUtils.copyProperties(user,seniorPlayer);
            seniorService.register(seniorPlayer);
        }
        return Result.success();
    }

//    获取数据接口
    @GetMapping("/getUser")
    public Result getUser(HttpServletRequest request){
        //从session中获取用户信息
        Account user = (Account) request.getSession().getAttribute("user");
        //判断用户角色
        int type = user.getType();
        //登录用户
        if(1 == type){
            //Secretary
            secretaryInfo secretary = secretaryService.findId(user.getId());
            return Result.success(secretary);
        }
        if(2 == type){
            //Coach
            coachInfo coach = coachService.findId(user.getId());
            return Result.success(coach);
        }
        if(3 == type){
            //Junior Player
            juniorPlayerInfo juniorPlayer = juniorService.findId(user.getId());
            return Result.success(juniorPlayer);
        }
        if(4 == type){
            //Senior Player
            seniorPlayerInfo seniorPlayer = seniorService.findId(user.getId());
            return Result.success(seniorPlayer);
        }
        return Result.success(new Account());
    }

    //修改密码
    @PostMapping("/updatePassword")
    public  Result updatePassword(@RequestBody Account account, HttpServletRequest request){
        //1.获取当前用户的type
        Account user = (Account) request.getSession().getAttribute("user");
        Integer type = user.getType();
        //2.根据type去数据库修改密码
        String oldPassword = account.getPassword();
        //判断原密码是否正确
        if(!user.getPassword().equals(oldPassword)){
            return Result.error("-1", "Password Error!");
        }
        String newPassword = account.getNewPassword();
        if(1 == type){ //管理员
            secretaryInfo secretary = new secretaryInfo();
            BeanUtils.copyProperties(user, secretary);
            secretary.setPassword(newPassword); //修改密码
            secretaryService.update(secretary);
        }
        if(2 == type){ //教练
            coachInfo coach = new coachInfo();
            BeanUtils.copyProperties(user, coach);
            coach.setPassword(newPassword); //修改密码
            coachService.update(coach);
        }
        if(3 == type){ //初级会员
            juniorPlayerInfo junior = new juniorPlayerInfo();
            BeanUtils.copyProperties(user, junior);
            junior.setPassword(newPassword); //修改密码
            juniorService.update(junior);
        }
        if(4 == type){ //高级会员
            seniorPlayerInfo senior = new seniorPlayerInfo();
            BeanUtils.copyProperties(user, senior);
            senior.setPassword(newPassword); //修改密码
            seniorService.update(senior);
        }
        //3.在session清除用户登录信息
        request.getSession().setAttribute("user", null);
        return Result.success();
    }

    //退出登录
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        //获取session中user信息,清除user数据
        request.getSession().setAttribute("user", null);
        return Result.success();
    }
}
