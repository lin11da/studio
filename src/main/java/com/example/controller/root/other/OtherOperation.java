package com.example.controller.root.other;

import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.OperationLog;
import com.example.mapper.Other;
import com.example.mapper.RootLogin;
import com.example.pojo.entity.StudioBulletinBoard;
import com.example.pojo.entity.StudioRoot;
import com.example.pojo.other.Operation_Log.Operation_Log_Up;
import com.example.pojo.other.Operation_Log.RootOperLogAspect;
import com.example.utils.Userreturn;
import com.example.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *  其他操作
 * @author chen
 * @version 1.0
 * @date 2021/9/16 11:08
 */
@CrossOrigin
@RestController
public class OtherOperation {

    private final String errornoadmin = "账号或密码错误";
    private final String errorauthority = "权限不足";
    private final String erroruserexists = "该用户已存在";
    private final String unknown = "未知错误";
    private final String errorformat = "请输入正确电话号码";
    private final String errornullpwd = "密码不能为空";
    private final String errorpwdlong = "密码长度太短,请输入六位或6位以上密码";
    private final String Exception = "数据异常,请刷新";
    private final String errorrefused = "拒绝添加，请删除历史内容";

    @Autowired
    Other other;

    @Autowired
    OperationLog operationLog;

    @Autowired
    RootLogin rootLogin;

    /**
     * 查看公告
     * @return
     */
    @GetMapping("/select/announcement")
    public Userreturn selectannouncement(){
        Map<String, String> map = new HashMap<>();
        StudioBulletinBoard selectannouncement = other.selectannouncement();
        if (selectannouncement==null){
            map.put("announcement",null);
            map.put("updateTime",null);
            map.put("title",null);
            map.put("id",null);
            return new Userreturn<>(map);
        }

        String announcement = selectannouncement.getAnnouncement();
        String announcementId = selectannouncement.getAnnouncementId();
        String updateTime = selectannouncement.getUpdateTime();
        String title = selectannouncement.getTitle();

        try {
            //判断是否有角色 管理员
            boolean b = StpUtil.hasRole("admin:common");
            if (b){
                map.put("id",announcementId);
            }
        } finally {
            map.put("announcement",announcement);
            map.put("updateTime",updateTime);
            map.put("title",title);
            return new Userreturn<>(map);
        }


    }

    /**
     * 添加公告
     * @param studioBulletinBoard
     * @return
     */
    @PostMapping("/admin/insertannouncement")
    public Userreturn insertannouncement(@RequestBody StudioBulletinBoard studioBulletinBoard){
        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }
        StudioBulletinBoard selectannouncement = other.selectannouncement();
        if (selectannouncement!=null){
            return new Userreturn<>(errorrefused);
        }

        String announcement = studioBulletinBoard.getAnnouncement();
        String announcementId = Utils.RandomStr(12);
        String title = studioBulletinBoard.getTitle();

        //time
        long timeMillis = System.currentTimeMillis();

        Integer insertannouncement = other.insertannouncement(announcementId, announcement, title, String.valueOf(timeMillis), String.valueOf(timeMillis));
        if (insertannouncement==1){
            return new Userreturn<>();
        }

        return new Userreturn<>(Exception);
    }

    /**
     * 更新公告
     * @param studioBulletinBoard
     * @return
     */
    @PostMapping("/admin/updateannouncement")
    public Userreturn updateannouncement(@RequestBody StudioBulletinBoard studioBulletinBoard){
        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        String announcement = studioBulletinBoard.getAnnouncement();
        String announcementId = Utils.RandomStr(12);
        String title = studioBulletinBoard.getTitle();

        //time
        long timeMillis = System.currentTimeMillis();

        Integer updateannouncement = other.updateannouncement(announcementId,announcement,title,String.valueOf(timeMillis));
        if (updateannouncement==1){
            return new Userreturn<>();
        }

        return new Userreturn<>(Exception);
    }

    /**
     * 删除公告
     * @param studioBulletinBoard
     * @return
     */
    @PostMapping("/admin/deleteannouncement")
    public Userreturn deleteannouncement(@RequestBody StudioBulletinBoard studioBulletinBoard){
        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        String announcementId = studioBulletinBoard.getAnnouncementId();

        Integer deleteannouncement = other.deleteannouncement(announcementId);
        if (deleteannouncement==1){
            return new Userreturn<>();
        }

        return new Userreturn<>(Exception);
    }

    /**
     * 查看操作日志
     * @param pageNump
     * @return
     */
    @GetMapping("/admin/operation_log")
    public Userreturn select_operation_log(@RequestParam(value = "pageNump",required = false)Integer pageNump){

        if (pageNump==null){
            pageNump=1;
        }

        Integer startSize = (pageNump-1)*8;
        Integer pageSize = 8;

        Map<String, Integer> map =new HashMap<>();
        map.put("startSize",startSize);
        map.put("pageSize",pageSize);
        List<Operation_Log_Up> rootOperLogAspects = operationLog.select_Operation_Log(map);

        for (Operation_Log_Up rootOperLogAspect : rootOperLogAspects) {
            String userid = rootOperLogAspect.getUserid();
            String username = null;
            try {
                StudioRoot getrolebyuserid = rootLogin.getrolebyuserid(userid);
                username = getrolebyuserid.getUsername();
            } catch (java.lang.Exception e) {
                //do nothing
            }
            rootOperLogAspect.setName(username);
        }
        return new Userreturn<>(rootOperLogAspects);

    }

}
