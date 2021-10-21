package com.example.server;

import com.example.mapper.RootLogin;

import com.example.mapper.UserLogin;
import com.example.pojo.entity.StudioRoot;
import com.example.pojo.entity.StudioUser;
import com.example.pojo.entity.StudioUserLoginlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/30 15:52
 */
@Service
public class LoginlogServer {
    @Autowired
    UserLogin userLogin;

    public List<Map<String, String>> loginlog(List<StudioUserLoginlog> selectadminloginlog){
        List<Map<String, String>> list = new LinkedList<>();

        for (StudioUserLoginlog rootLoginlog : selectadminloginlog) {
            Map<String, String> map = new HashMap<>();
            String loginip = rootLoginlog.getLoginip();
            String createtime = rootLoginlog.getCreatetime();
            String userid = rootLoginlog.getUserid();
            String logid = rootLoginlog.getLogid();
            //查询管理员用户名还有号码
            StudioUser studioUser = userLogin.getrole_by_userid(userid);

            String username = studioUser.getUsername();
            String usernumber = studioUser.getUsernumber();
            map.put("ip",loginip);
            map.put("createtime",createtime);
            map.put("id",userid);
            map.put("name",username);
            map.put("number",usernumber);
            map.put("logid",logid);

            list.add(map);
        }
        return list;
    }
}
