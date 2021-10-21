package com.example.controller.root.other;

import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.Other;
import com.example.pojo.entity.StudioFooter;
import com.example.utils.Userreturn;
import com.example.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *  页脚管理
 * @author chen
 * @version 1.0
 * @date 2021/9/16 9:46
 */
@CrossOrigin
@RestController
public class Footer {

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

    /**
     * 添加页脚
     * @param studioFooter
     * @return
     */
    @PostMapping("/admin/footer")
    public Userreturn insertfooter(@RequestBody StudioFooter studioFooter){
        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        StudioFooter selectfooter = other.selectfooter();
        if (selectfooter!=null){
            return new Userreturn<>(errorrefused);
        }

        //负责人
        String head = studioFooter.getHead();
        String email = studioFooter.getEmail();
        String address = studioFooter.getAddress();
        //版权
        String copyright = studioFooter.getCopyright();
        String number = studioFooter.getNumber();

        String s = Utils.RandomStr(12);

        //time
        long timeMillis = System.currentTimeMillis();

        Integer foothermanage = other.foothermanage(s,head, number, email, address, copyright, String.valueOf(timeMillis), String.valueOf(timeMillis));
        if (foothermanage==1){
            return new Userreturn<>();
        }
        return new Userreturn(Exception);
    }

    /**
     * 查看页脚
     * @return
     */
    @GetMapping("/footer")
    public Userreturn selectfooter(){
        StudioFooter selectfooter = other.selectfooter();
        Map<String, String> map = new HashMap<>();
        if (selectfooter==null){
            map.put("id",null);
            map.put("head",null);
            map.put("email",null);
            map.put("address",null);
            map.put("copyright",null);
            map.put("number",null);
            return new Userreturn<>(map);
        }

        //负责人
        String head = selectfooter.getHead();
        String email = selectfooter.getEmail();
        String address = selectfooter.getAddress();
        //版权
        String copyright = selectfooter.getCopyright();
        String number = selectfooter.getNumber();
        String footerid = selectfooter.getFooterid();

        try {
            //判断是否有角色 管理员
            boolean b = StpUtil.hasRole("admin:common");
            if (b){
                map.put("id",footerid);
            }
        } finally {
            map.put("head",head);
            map.put("email",email);
            map.put("address",address);
            map.put("copyright",copyright);
            map.put("number",number);
            return new Userreturn<>(map);
        }

    }

    /**
     * 更新页脚
     * @param studioFooter
     * @return
     */
    @PostMapping("/admin/updatefooter")
    public Userreturn updattefooter(@RequestBody StudioFooter studioFooter){
        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }
        //负责人
        String head = studioFooter.getHead();
        String email = studioFooter.getEmail();
        String address = studioFooter.getAddress();
        //版权
        String copyright = studioFooter.getCopyright();
        String number = studioFooter.getNumber();
        String footerid = studioFooter.getFooterid();

        //time
        long timeMillis = System.currentTimeMillis();

        Integer updatefooter = other.updatefooter(footerid, head, number, email, address, copyright, String.valueOf(timeMillis));
        if (updatefooter==1){
            return new Userreturn<>();
        }
        return new Userreturn<>(Exception);
    }

    /**
     * 删除页脚
     * @param studioFooter
     * @return
     */
    @PostMapping("/admin/delectfooter")
    public Userreturn deletefooter(@RequestBody StudioFooter studioFooter){
        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }
        String footerid = studioFooter.getFooterid();

        Integer deletefooter = other.deletefooter(footerid);
        if (deletefooter==1){
            return new Userreturn<>();
        }
        return new Userreturn<>(Exception);
    }




}
