package com.example.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.example.mapper.RootLogin;
import com.example.mapper.UserLogin;
import com.example.pojo.entity.StudioRoot;
import com.example.pojo.entity.StudioUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private RootLogin rootLogin;

    @Autowired
    private UserLogin userLogin;


    /**
     * 返回一个账号所拥有的一级路由权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String s1 = String.valueOf(loginId);
        // 遍历路由权限
        List<String> list = new ArrayList<>();

        StudioRoot getrole = rootLogin.getrole(s1);
        //如果到用户这边，没有任何权限则返回null
        if (getrole==null){
            return null;
        }
        String oneRoute = getrole.getOneRoute();
        String twoRoute = getrole.getTwoRoute();
        List<String> onewList = Arrays.asList(oneRoute.split(","));

        List<String> twoList = Arrays.asList(twoRoute.split(","));

        for (String s : onewList) {
            list.add(s);
        }

        for (String s : twoList) {
            list.add(s);
        }
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        String s1 = String.valueOf(loginId);
        // 遍历角色
        List<String> list = new ArrayList<>();

        StudioRoot getrole = rootLogin.getrole(s1);
        if (getrole==null){
            StudioUser user = userLogin.getrole(s1);
            String role = user.getRole();
            List<String> strList = Arrays.asList(role.split(","));
            for (String s : strList) {
                list.add(s);
            }
            return list;
        }
        String role = getrole.getRole();
        List<String> strList = Arrays.asList(role.split(","));
        for (String s : strList) {
            list.add(s);
        }
        return list;
    }
}
