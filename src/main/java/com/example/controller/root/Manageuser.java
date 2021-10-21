package com.example.controller.root;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.example.mapper.RootManageUser;
import com.example.mapper.UserLogin;
import com.example.pojo.entity.StudioUser;
import com.example.pojo.other.StudioUserOther;
import com.example.server.Other.ManageUserServer;
import com.example.utils.Userreturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/21 20:05
 */
@CrossOrigin
@RestController
public class Manageuser {


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
    private RootManageUser rootManageUser;

    @Autowired
    private UserLogin userLogin;

    /**
     * 管理员查看用户信息
     *  当管理员没有输入number时，就是全局搜索用户信息，
     *  当管理员输入number后，就查找该number的信息
     * @param number
     * @return
     */
    @SaCheckRole("admin:common")
    @GetMapping("/admin/getuser")
    public Userreturn admingetuser(@RequestParam(value ="number", required = false)String number){

        List<Map> getuser=null;
        if (number==null||"".equals(number)){
            List<StudioUser> selectalluser = rootManageUser.selectalluser();
            getuser = ManageUserServer.getuser(selectalluser);
        }
        //如果number不为空   则根据number查询
        if (number!=null){
            List<StudioUser> selectuserbynumber = rootManageUser.selectuserbynumber(number);
            getuser = ManageUserServer.getuser(selectuserbynumber);
        }
        return new Userreturn<>(getuser);
    }


    /**
     * 更改用户信息
     * @param studioUser
     * @return
     */
    @SaCheckRole("admin:common")
    @PostMapping("/admin/upuserdate")
    public Userreturn adminupdateuserdata(@RequestBody StudioUser studioUser){
        String userId = studioUser.getUserId();
        String password = studioUser.getPassword();
        String username = studioUser.getUsername();
        String usernumber = studioUser.getUsernumber();

        StudioUser getrole = userLogin.getrole(usernumber);
        if (getrole!=null){
            return new Userreturn(erroruserexists);
        }

        //time
        long timeMillis = System.currentTimeMillis();

        Integer updateuserdate = rootManageUser.updateuserdate(password, usernumber, username, String.valueOf(timeMillis), userId);
        if (updateuserdate==1){
            return new Userreturn<>();
        }



        return new Userreturn<>(unknown);
    }




    /**
     * 修改用户权限
     * @param studioUserOther
     * @return
     */
    @SaCheckRole("admin:common")
    @PostMapping("/admin/upusermanage")
    public Userreturn updateusermanage(@RequestBody StudioUserOther studioUserOther){
        String[] userId = studioUserOther.getUserId();
        String role = studioUserOther.getRole();

        int i =0;
        for (String s : userId) {
            Integer updateusermanage = rootManageUser.updateusermanage(s, role);
            if (updateusermanage==1){
                i++;
                if (userId.length==i){
                    return new Userreturn<>();
                }
            }
        }
        return new Userreturn<>(Exception);
    }

}
