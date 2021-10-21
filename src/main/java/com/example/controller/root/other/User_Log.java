package com.example.controller.root.other;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.example.config.aop.OperLog;
import com.example.mapper.Log;
import com.example.mapper.RootLogin;
import com.example.mapper.UserLogin;
import com.example.pojo.entity.StudioRoot;
import com.example.pojo.entity.StudioUser;
import com.example.pojo.entity.StudioUserLoginlog;
import com.example.pojo.other.Operation_Log.Login_Log;
import com.example.server.LoginlogServer;
import com.example.utils.Userreturn;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/10/16 17:30
 */
@RestController
@CrossOrigin
public class User_Log {

    private final String errorusernoexists = "该用户不存在";
    private final String unknown = "未知错误";

    @Autowired
    Log log;

    @Autowired
    LoginlogServer loginlogServer;

    @Autowired
    RootLogin rootLogin;

    @Autowired
    UserLogin userLogin;



    /**
     * 查找管理员登录日志  超级管理员可操作
     * @param pageNumber
     * @return
     */
    @OperLog(operModul = "管理员",operDesc = "查找管理员登录日志 超级管理员可操作",operType = "查找")
    @SaCheckRole("admin:*")
    @GetMapping("/admin/adminloginlog")
    public Userreturn loginlog(@RequestParam(value = "pageNumber",required = false)Integer pageNumber,
                               @RequestParam(value = "number",required = false) String number){

        if (pageNumber==null||pageNumber==0){
            pageNumber=1;
        }

        int pageStart = 8*(pageNumber-1);
        int pageSize = 8;

        List<StudioUserLoginlog> selectadminloginlog = null;
        List<Map<String, String>> list = null;

        if (StringUtils.isEmpty(number)){
            //查询管理员登录日志
            selectadminloginlog = log.selectadminloginlog(pageStart, pageSize);
            list = loginlogServer.loginlog(selectadminloginlog);
        }else{
            StudioUser getrole = userLogin.getrole(number);
            //判断该用户是否存在
            if (getrole==null){
                return new Userreturn<>(errorusernoexists);
            }
            String userid = getrole.getUserId();
            List<StudioUserLoginlog> selectadminloginlogbyuserid = log.selectadminloginlogbyuserid(pageStart, pageSize, userid);
            list = loginlogServer.loginlog(selectadminloginlogbyuserid);
        }

        return new Userreturn<>(list);
    }

    /**
     * 删除登录记录  超级管理员可操作
     * @param login_log
     * @return
     */
    @OperLog(operModul = "管理员",operDesc = "删除登录记录  超级管理员可操作",operType = "删除")
    @SaCheckRole("admin:*")
    @PostMapping("/admin/delectloginlog")
    public Userreturn delectloginlog(@RequestBody Login_Log login_log){
        String[] logid = login_log.getLogid();
        int i = 0;
        for (String s : logid) {
            Integer deleteloginlog = log.deleteloginlog(s);
            if (deleteloginlog==1){
                i++;
                if (logid.length==i){
                    return new Userreturn<>();
                }
            }
        }
        return new Userreturn<>(unknown);
    }

}
