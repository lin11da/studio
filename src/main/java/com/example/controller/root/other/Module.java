package com.example.controller.root.other;

import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.Other;
import com.example.pojo.entity.StudioModule;
import com.example.pojo.other.StudioModuleOther;
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
 * @date 2021/9/18 9:22
 */
@CrossOrigin
@RestController
public class Module {
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
     * 添加模块
     * @param studioModule
     * @return
     */
    @PostMapping("/admin/insertmodule")
    public Userreturn imodule(@RequestBody StudioModule studioModule){
        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        String s = Utils.RandomStr(16);

        //time
        long timeMillis = System.currentTimeMillis();

        String moduleName = studioModule.getModuleName();

        Integer insertmodule = other.insertmodule(s, moduleName, String.valueOf(timeMillis), String.valueOf(timeMillis));
        if (insertmodule==1){
            return new Userreturn<>();
        }

        return new Userreturn<>(unknown);
    }

    /**
     * 查看模块
     * @return
     */
    @GetMapping("/module")
    public Userreturn module(){
        List<StudioModule> selectmodule = other.selectmodule();

        List<Map> list = new LinkedList<>();

        for (StudioModule studioModule : selectmodule) {
            Map<String, String> map = new HashMap<>();
            String moduleId = studioModule.getModuleId();
            String moduleName = studioModule.getModuleName();

            try {
                //判断是否有角色 管理员
                boolean b = StpUtil.hasRole("admin:common");
                if (b){
                    map.put("id",moduleId);
                }
            } catch (java.lang.Exception e) {
            }

            map.put("modulename",moduleName);

            list.add(map);
        }
        return new Userreturn<>(list);
    }


    /**
     * 删除模块
     * @param studioModuleOther
     * @return
     */
    @PostMapping("/admin/deletemodule")
    public Userreturn dmodule(@RequestBody StudioModuleOther studioModuleOther){
        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        int i = 0;
        String[] moduleId = studioModuleOther.getModuleId();

        for (String s : moduleId) {
            Integer deletemodule = other.deletemodule(s);
            if (deletemodule==1){
                i++;
                if (moduleId.length==i){
                    return new Userreturn<>();
                }
            }
        }
        return new Userreturn<>(Exception);
    }

}
