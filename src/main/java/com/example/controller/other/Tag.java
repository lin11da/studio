package com.example.controller.other;

import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.Other;
import com.example.pojo.entity.StudioModule;
import com.example.pojo.entity.StudioTag;
import com.example.pojo.other.StudioModuleOther;
import com.example.pojo.other.StudioTagOther;
import com.example.server.FileServer;
import com.example.utils.Userreturn;
import com.example.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/18 9:49
 */
@CrossOrigin
@RestController
public class Tag {

    private final String errorfilenotexist = "文件上传失败";
    private final String errorfileformat = "文件格式错误";
    private final String errornoadmin = "账号或密码错误";
    private final String errorauthority = "权限不足";
    private final String erroruserexists = "该用户已存在";
    private final String unknown = "未知错误";
    private final String errorformat = "请输入正确电话号码";
    private final String errornullpwd = "密码不能为空";
    private final String errorpwdlong = "密码长度太短,请输入六位或6位以上密码";
    private final String Exception = "数据异常,请刷新";


    @Autowired
    FileServer fileServer;

    @Autowired
    Other other;


    /**
     * 插入标签
     * @param studioTag
     * @return
     */
    @PostMapping("/admin/itag")
    public Userreturn inserttag(@RequestBody StudioTag studioTag){
        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("admin:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        String tagName = studioTag.getTagName();

        //time
        long timeMillis = System.currentTimeMillis();

        String s = Utils.RandomStr(16);

        Integer inserttag = other.inserttag(s, tagName, String.valueOf(timeMillis), String.valueOf(timeMillis));
        if (inserttag==1){
            return new Userreturn<>();
        }
        return new Userreturn<>(unknown);
    }


    /**
     * 查看标签
     * @return
     */
    @GetMapping("/tag")
    public Userreturn selecttag(){
        List<StudioTag> selecttag = other.selecttag();

        List<Map> list = new LinkedList<>();

        for (StudioTag studioTag : selecttag) {
            Map<String, String> map = new HashMap<>();
            String tagId = studioTag.getTagId();
            String tagName = studioTag.getTagName();

            try {
                //判断是否有角色 管理员
                boolean b = StpUtil.hasRole("admin:common");
                if (b){
                    map.put("id",tagId);
                }
            } catch (java.lang.Exception e) {
            }

            map.put("tagname",tagName);

            list.add(map);
        }
        return new Userreturn<>(list);
    }


    /**
     * 删除标签
     * @param studioTagOther
     * @return
     */
    @PostMapping("/admin/dtag")
    public Userreturn deletetag(@RequestBody StudioTagOther studioTagOther){
        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        int i = 0;
        String[] tagId = studioTagOther.getTagId();

        for (String s : tagId) {
            Integer deletetag = other.deletetag(s);
            if (deletetag==1){
                i++;
                if (tagId.length==i){
                    return new Userreturn<>();
                }
            }
        }
        return new Userreturn<>(Exception);
    }

}
