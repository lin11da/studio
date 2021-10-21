package com.example.controller.root.other;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.RootLogin;
import com.example.mapper.RootOperation;
import com.example.pojo.entity.StudioRoot;
import com.example.pojo.other.StudioRootOther;
import com.example.server.Other.RoletoList;
import com.example.utils.Userreturn;
import com.example.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/15 16:32
 */
@CrossOrigin
@RestController
public class Root {

    private final String errornoadmin = "账号或密码错误";
    private final String errorauthority = "权限不足";
    private final String erroruserexists = "该用户已存在";
    private final String unknown = "未知错误";
    private final String errorformat = "请输入正确电话号码";
    private final String errornullpwd = "密码不能为空";
    private final String errorpwdlong = "密码长度太短,请输入六位或6位以上密码";
    private final String Exception = "数据异常,请刷新";

    @Autowired
    RootOperation rootOperation;

    @Autowired
    RootLogin rootLogin;

    @Autowired
    RoletoList roletoList;

    /**
     * 查询全部管理员
     * @return
     */
    @PostMapping("/admin/allroot")
    public Userreturn allroot(){
        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        //查询全部管理员信息
        List<StudioRoot> allroot = rootOperation.allroot();

        List<Map> list = new LinkedList<>();

        for (StudioRoot studioRoot : allroot) {
            Map<String, Object> map =new HashMap<>();
            Map<String, String> stringMap = new HashMap<>();
            String password = studioRoot.getPassword();
            String usernumber = studioRoot.getUsernumber();
            String createTime = studioRoot.getCreateTime();
            String updateTime = studioRoot.getUpdateTime();
            String oneRoute = studioRoot.getOneRoute();
            String twoRoute = studioRoot.getTwoRoute();
            String username = studioRoot.getUsername();
            String userId = studioRoot.getUserId();
            Object rootlogin1 = roletoList.rootlogin(oneRoute,twoRoute);


            stringMap.put("id",userId);
            stringMap.put("name",username);
            stringMap.put("password",password);
            stringMap.put("number",usernumber);
            map.put("userinfo",stringMap);
            map.put("createTime",createTime);
            map.put("updatetime",updateTime);
            map.put("nav",rootlogin1);

            list.add(map);

        }
        return new Userreturn<>(list);
    }

    /**
     * 修改管理员信息
     * @param studioRoot
     * @return
     */
    @PostMapping("/admin/updateroot")
    public Userreturn updateroot(@RequestBody StudioRoot studioRoot){
        String password = studioRoot.getPassword();
        String usernumber = studioRoot.getUsernumber();
        String username = studioRoot.getUsername();
        String userId = studioRoot.getUserId();

        //time
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
        StudioRoot role = rootLogin.getrole(usernumber);
        if (role!=null){
            return new Userreturn<>(erroruserexists);
        }

        //登录帐户
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Object loginId = tokenInfo.loginId;
        String s = String.valueOf(loginId);

        StudioRoot getrole = rootLogin.getrole(s);
        String mysqluserId = getrole.getUserId();

        if (userId.equals(mysqluserId)){
            Integer updateroot = rootOperation.updateroot(usernumber, username, password, String.valueOf(timeMillis), userId);
            if (updateroot==1){
                return new Userreturn<>();
            }
        }else {
            //判断是否有角色  超级管理员
            boolean b = StpUtil.hasRole("admin:*");
            if (!b){
                return new Userreturn<>(401,errorauthority);
            }
            Integer updateroot = rootOperation.updateroot(usernumber, username, password, String.valueOf(timeMillis), userId);
            if (updateroot==1){
                return new Userreturn<>();
            }

        }

        return new Userreturn<>(unknown);
    }


    /**
     * 删除管理员
     * @param studioRootOther
     * @return
     */
    @PostMapping("/admin/delectroot")
    public Userreturn delectroot(@RequestBody StudioRootOther studioRootOther){
        String[] userId = studioRootOther.getUserId();
        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Object loginId = tokenInfo.loginId;
        String s2 = String.valueOf(loginId);

        StudioRoot getrole = rootLogin.getrole(s2);
        String mysqluserId = getrole.getUserId();



        int i = 0;
        for (String s : userId) {
            if ("7e5c6770-457b-40f7-b541-2ba579dfee5b".equals(s)){
                return new Userreturn<>("不可删除");
            }
            Integer delectroot = rootOperation.delectroot(s);
            if (delectroot==1){
                i++;
                if (userId.length==i){
                    return new Userreturn<>();
                }
            }

        }
        return new Userreturn<>(Exception);

    }

}
