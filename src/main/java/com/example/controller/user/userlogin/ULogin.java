package com.example.controller.user.userlogin;

import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.Log;
import com.example.mapper.UserLogin;
import com.example.pojo.entity.StudioRoot;
import com.example.pojo.entity.StudioUser;
import com.example.server.FileServerSmall;
import com.example.utils.IpUntil;
import com.example.utils.Userreturn;
import com.example.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/17 18:42
 */
@CrossOrigin
@RestController
public class ULogin {
    private final String errornoadmin = "账号或密码错误";
    private final String errorauthority = "权限不足";
    private final String erroruserexists = "该用户已存在";
    private final String unknown = "未知错误";
    private final String errorformat = "请输入正确电话号码";
    private final String errornullpwd = "密码不能为空";
    private final String errorpwdlong = "密码长度太短,请输入六位或6位以上密码";
    private final String Exception = "数据异常,请刷新";
    private final String errorrefused = "拒绝添加，请删除历史内容";
    private final String error_invalid_format = "invalid_format:文件格式错误";
    private final String error = "数据处理失败";


    @Autowired
    UserLogin userLogin;

    @Autowired
    Log log;



    /**
     * 用户登录
     * @param studioUser
     * @return
     */
    @PostMapping("/user/login")
    public Userreturn login(@RequestBody StudioUser studioUser, HttpServletRequest request){
        String usernumber = studioUser.getUsernumber();
        String password = studioUser.getPassword();
        Map<String, Object> map = new HashMap<>();
        Map<String, String> two_map = new HashMap<>();

        StudioUser userlogin = userLogin.userlogin(usernumber, password);

        if (userlogin==null){
            return new Userreturn<>(errornoadmin);
        }

        //time
        long timeMillis = System.currentTimeMillis();
        String userId = userlogin.getUserId();
        String img = userlogin.getImg();
        String username = userlogin.getUsername();
        two_map.put("img",img);
        two_map.put("name",username);

        String s = Utils.RandomStr(16);

        //ip地址
        String ipAddr = IpUntil.getIpAddr(request);
        String ipaddressto = null;

        //id地址转换归属地
        ipaddressto = IpUntil.ipaddressto(ipAddr);

        //插入用户登陆日志
        Integer addloginlog = log.addloginlog(s,userId, ipAddr, ipaddressto, String.valueOf(timeMillis), String.valueOf(timeMillis), String.valueOf(timeMillis));
        if (addloginlog==1){
            //后面转成log
            System.out.println(usernumber+"  日志记录成功");
        }

        StpUtil.login(usernumber);
        //拿到token
        String tokenValue = StpUtil.getTokenValue();
        map.put("token",tokenValue);
        map.put("msg",two_map);

        return new Userreturn<>(200,map);
    }



    /**
     * 用户注册
     * @param studioRoot
     * @return
     */
    @PostMapping("/user/registered")
    public Userreturn registered(@RequestBody StudioRoot studioRoot){
        String usernumber = studioRoot.getUsernumber();
        //密码
        String password = studioRoot.getPassword();

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
        StudioUser getrole = userLogin.getrole(usernumber);
        if (getrole!=null){
            return new Userreturn<>(erroruserexists);
        }

        Integer registered = userLogin.registereduser(uuid, s, usernumber, password, String.valueOf(timeMillis), String.valueOf(timeMillis));
        if (registered==1){
            return new Userreturn<>();
        }

        return new Userreturn<>(unknown);

    }


    /**
     * 修改用户信息
     * @param img
     * @param username
     * @param about
     * @param birthday
     * @param gender
     * @return
     * @throws IOException
     */
    @PostMapping("/user/update_user_profile")
    public Userreturn user_profile(@RequestParam("img") MultipartFile img,@RequestParam("username")String username,
                                   @RequestParam("about")String about,@RequestParam("birthday")String birthday,
                                   @RequestParam("gender")String gender) throws IOException {

        //图片url
        String imgfileseversmall = FileServerSmall.fileseversmall(img);

        String number = StpUtil.getLoginId().toString();

        //time
        long timeMillis = System.currentTimeMillis();

        if ("error,invalid_format".equals(imgfileseversmall)){
            return new Userreturn<>(error_invalid_format);
        }else if (StringUtils.isEmpty(imgfileseversmall)){
            return new Userreturn<>(error);
        }

        Integer integer = userLogin.modify_user_information(username, number, gender, about, birthday, imgfileseversmall, String.valueOf(timeMillis));

        if (integer ==1){
            return new Userreturn<>();
        }

        return new Userreturn<>(unknown);
    }

    /**
     * 查看用户信息
     * @return
     */
    @GetMapping("/user/user_information")
    public Userreturn view_user_information(){
        Map<String, String> map = new HashMap<>();
        String s = StpUtil.getLoginId().toString();
        StudioUser getrole = userLogin.getrole(s);
        String about = getrole.getAbout();
        String birthday = getrole.getBirthday();
        String gender = getrole.getGender();
        String img = getrole.getImg();
        String username = getrole.getUsername();
        String usernumber = getrole.getUsernumber();

        map.put("about",about);
        map.put("birthday",birthday);
        map.put("gender",gender);
        map.put("img",img);
        map.put("name",username);
        map.put("number",usernumber);

        return new Userreturn<>(map);
    }
}
