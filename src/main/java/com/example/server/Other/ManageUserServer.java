package com.example.server.Other;

import com.example.pojo.entity.StudioUser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/22 19:33
 */
public class ManageUserServer {

    public static List<Map> getuser(List<StudioUser> selectalluser){
        //List
        List<Map> list = new LinkedList<>();

        for (StudioUser studioUser : selectalluser) {
            Map<String, String> map = new HashMap<>();
            String userId = studioUser.getUserId();
            String createTime = studioUser.getCreateTime();
            String updateTime = studioUser.getUpdateTime();
            String about = studioUser.getAbout();
            String img = studioUser.getImg();
            String password = studioUser.getPassword();
            String username = studioUser.getUsername();
            String usernumber = studioUser.getUsernumber();
            String birthday = studioUser.getBirthday();

            map.put("id",userId);
            map.put("createtime",createTime);
            map.put("updatetime",updateTime);
            map.put("about",about);
            map.put("img",img);
            map.put("password",password);
            map.put("name",username);
            map.put("number",usernumber);
            map.put("birthday",birthday);

            list.add(map);
        }
        return list;
    }
}
