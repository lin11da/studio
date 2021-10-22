package com.example.controller.root;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.NavigationOperation;
import com.example.mapper.RootLogin;
import com.example.mapper.SuperManage;
import com.example.pojo.entity.StudioOneroute;
import com.example.pojo.entity.StudioRoot;
import com.example.pojo.other.nav.NavO_Root;
import com.example.pojo.other.StudioRole;
import com.example.pojo.other.StudioinsertTwoRole;
import com.example.pojo.other.nav.NavO_two;
import com.example.server.Other.RoletoList;
import com.example.utils.Userreturn;
import com.example.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/22 21:03
 */
@CrossOrigin
@RestController
public class SupperManage {

    private final String errorfilenotexist = "文件上传失败";
    private final String errorfileformat = "文件格式错误";
    private final String errornoadmin = "账号或密码错误";
    private final String errorauthority = "权限不足";
    private final String erroruserexists = "该用户已存在";
    private final String errornoonerole = "没有该一级权限";
    private final String unknown = "未知错误";
    private final String errorformat = "请输入正确电话号码";
    private final String errornullpwd = "密码不能为空";
    private final String errorpwdlong = "密码长度太短,请输入六位或6位以上密码";
    private final String Exception = "数据异常,请刷新";
    private final String errornoupdate = "不可修改";

    @Autowired
    SuperManage superManage;

    @Autowired
    RootLogin rootLogin;

    @Autowired
    RoletoList roletoList;

    @Autowired
    NavigationOperation navigationOperation;

    /**
     * 超级管理员插入一级权限
     * @param studioOneroute
     * @return
     */
    @SaCheckRole("admin:*")
    @PostMapping("/superadmin/insertonerole")
    public Userreturn insertrole(@RequestBody StudioOneroute studioOneroute){
        String routeName = studioOneroute.getRouteName();
        String routeUrl = studioOneroute.getRouteUrl();
        String s = Utils.RandomStr(14);

        //time
        long timeMillis = System.currentTimeMillis();

        Integer insertrole = superManage.insertrole(s, routeName, routeUrl, String.valueOf(timeMillis), String.valueOf(timeMillis));
        if (insertrole==1){
            return new Userreturn<>();
        }
        return new Userreturn<>(unknown);
    }

    /**
     * 超级管理员插入二级权限
     * @param studioinsertTwoRole
     * @return
     */
    @SaCheckRole("admin:*")
    @PostMapping("/superadmin/inserttworole")
    public Userreturn insertrole(@RequestBody StudioinsertTwoRole studioinsertTwoRole){
        String oneroute = studioinsertTwoRole.getOneroute();
        String routeName = studioinsertTwoRole.getRouteName();
        String routeUrl = studioinsertTwoRole.getRouteUrl();
        String s = Utils.RandomStr(14);

        StudioOneroute selectonerole = superManage.selectonerole(oneroute);
        if (selectonerole==null){
            return new Userreturn<>(errornoonerole);
        }

        //time
        long timeMillis = System.currentTimeMillis();

        Integer inserttworole = superManage.inserttworole(oneroute, s, routeName, routeUrl, String.valueOf(timeMillis), String.valueOf(timeMillis));
        if (inserttworole==1){
            return new Userreturn<>();
        }
        return new Userreturn<>(unknown);
    }

    /**
     * 超级管理员给普通管理员赋予权限  更新权限
     * @param studioRole
     * @return
     */
    @SaCheckRole("admin:*")
    @PostMapping("/superadmin/setpermission")
    public Userreturn setpermission(@RequestBody StudioRole studioRole){
        String[] onerouteId = studioRole.getOnerouteId();
        String[] tworouteId = studioRole.getTworouteId();
        String userId = studioRole.getUserId();

        //获取当前登录的用户号码
        String s = StpUtil.getLoginId().toString();

        //获取  登录用户的userId
        StudioRoot getrole = rootLogin.getrole(s);
        String mysqluserId = getrole.getUserId();

        //获取  登录用户的role
        StudioRoot getrolebyuserid = rootLogin.getrolebyuserid(userId);
        String role = getrolebyuserid.getRole();

        //如果userid是自己的，或者其他人的权限为 * 则不能修改
        if (userId.equals(mysqluserId)||"*".equals(role)){
            return new Userreturn<>(errornoupdate);
        }

        //time
        long timeMillis = System.currentTimeMillis();

        //String.join(",",onerouteId)数组转字符串
        Integer setpermission = superManage.setpermission(String.join(",",onerouteId), String.join(",",tworouteId), String.valueOf(timeMillis), userId);

        if (setpermission==1){
            return new Userreturn<>();
        }
        return new Userreturn<>(unknown);
    }


    /**
     * 查看所有导航
     * @return
     */
    @SaCheckRole("admin:*")
    @GetMapping("/admin/nav")
    public Userreturn slect_nav(){
        List<Object> objectList = new ArrayList<>();

        //遍历出所有一级导航
        List<NavO_Root> navO_roots = navigationOperation.select_nav_one();
        for (NavO_Root navO_root : navO_roots) {

            Map<String,  Object> listmap = new HashMap<>();

            String oneid = navO_root.getRouteId();
            String label = navO_root.getRouteName();
            String url = navO_root.getRouteUrl();


            //根据 oneid 进行查询 二级导航 数据
            List<NavO_two> navO_twos = navigationOperation.select_nav_two(oneid);

            //把navOS_two放入list
            List<NavO_Root> list = new ArrayList(navO_twos);

            //分类
            listmap.put("id",oneid);
            listmap.put("label",label);

            listmap.put("url",url);
            listmap.put("children",list);


            objectList.add(listmap);

        }
        return new Userreturn<>(objectList);
    }


    /**
     * 查看某个账号的导航
     * @param number
     * @return
     */
    @SaCheckRole("admin:*")
    @GetMapping("/admin/select_nav")
    public Userreturn select_nav(@RequestParam("number")String number){
        StudioRoot getrole = rootLogin.getrole(number+"type");
        String oneRoute = getrole.getOneRoute();
        String twoRoute = getrole.getTwoRoute();

        Map<String, Object> map = new HashMap<>();
        Object rootlogin1 = roletoList.rootlogin(oneRoute,twoRoute);
        map.put("nav",rootlogin1);
        return new Userreturn<>(map);
    }





}
