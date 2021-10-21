package com.example.controller.root.rootlogin;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.stp.StpUtil;


import com.example.config.aop.OperLog;
import com.example.mapper.RootLogin;
import com.example.pojo.entity.StudioRoot;
import com.example.server.Other.RoletoList;
import com.example.utils.Userreturn;
import com.example.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * TODO
 *
 * @author dada
 * @version 1.0
 * @date 2021/9/14 21:23
 */
@CrossOrigin
@RestController
public class Login {


    private final String errornoadmin = "账号或密码错误";
    private final String errorauthority = "权限不足";
    private final String erroruserexists = "该用户已存在";
    private final String unknown = "未知错误";
    private final String errorformat = "请输入正确电话号码";
    private final String errornullpwd = "密码不能为空";
    private final String errorpwdlong = "密码长度太短,请输入六位或6位以上密码";

    @Autowired
    RootLogin rootLogin;

    @Autowired
    RoletoList roletoList;



    /**
     * 管理员登录
     * @param studioRoot
     * @return
     */
    @OperLog(operModul = "管理员",operDesc = "管理员登录",operType = "登录")
    @PostMapping("/admin/login")
    public Userreturn login(@RequestBody StudioRoot studioRoot){
        String usernumber = studioRoot.getUsernumber();
        String password = studioRoot.getPassword();
        Map<String, Object> map = new HashMap<>();
        Map<String, String> stringMap = new HashMap<>();


        usernumber = usernumber +"type";
        //time  13位时间戳
        StudioRoot rootlogin = rootLogin.rootlogin(usernumber, password);

        if (rootlogin==null){
            return new Userreturn<>(errornoadmin);
        }

        String oneRoute = rootlogin.getOneRoute();
        String twoRoute = rootlogin.getTwoRoute();
        String mysql_usernumber = rootlogin.getUsernumber();
        String username = rootlogin.getUsername();

        String userId = rootlogin.getUserId();

        Object rootlogin1 = roletoList.rootlogin(oneRoute,twoRoute);



        stringMap.put("number",mysql_usernumber);
        stringMap.put("id",userId);
        stringMap.put("name",username);
        map.put("userinfo",stringMap);

        map.put("nav",rootlogin1);
        StpUtil.login(usernumber);
        //拿到token
        String tokenValue = StpUtil.getTokenValue();
        map.put("token",tokenValue);


        return new Userreturn<>(200,map);
    }



    /**
     * 管理员注册
     * @param studioRoot
     * @return
     */
    @PostMapping("/admin/registered")
    public Userreturn registered(@RequestBody StudioRoot studioRoot){
        String usernumber = studioRoot.getUsernumber();
        //密码
        String password = studioRoot.getPassword();

        //角色
        String role = studioRoot.getRole();

        //userid
        String uuid = UUID.randomUUID().toString();

        //随机用户名
        String s = Utils.RandomStr(6);

        //time  13位时间戳
        long timeMillis = System.currentTimeMillis();

        //判断电话号码是否是数字
        Boolean pdsz = Utils.pdsz(usernumber);
        if (!pdsz){
            return new Userreturn<>(errorformat);
        }

        //检查是否是11位电话号码
        boolean datalong = Utils.datalong(usernumber, 11);
        if (!datalong){
            return new Userreturn<>(errorformat);
        }
//        判断密码是否为空
        if ("".equals(password)||password==null){
            return new Userreturn<>(errornullpwd);
        }
        //判断密码是否是6位或6位以上
        if (password.length()<6){
            return new Userreturn<>(errorpwdlong);
        }

        //查看要注册的号码是否存在
        StudioRoot getrole = rootLogin.getrole(usernumber+"type");
        if (getrole!=null){
            return new Userreturn<>(erroruserexists);
        }
        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        usernumber = usernumber +"type";

        Integer registered = rootLogin.registered(uuid, s, usernumber, password, role, String.valueOf(timeMillis), String.valueOf(timeMillis));
        if (registered==1){
            return new Userreturn<>();
        }

        return new Userreturn<>(unknown);

    }


}
