package com.example.server;

import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.Paper;
import com.example.mapper.UserLogin;
import com.example.pojo.entity.StudioUser;
import com.example.pojo.other.TsudioPaperOther;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/20 18:13
 */
@Service
public class PaperServer {

    @Autowired
    private Paper ppaper;

    @Autowired
    UserLogin userLogin;

    public List<Map> selectpaper(List<TsudioPaperOther> selectpaper ){
        List<Map> list = new LinkedList<>();
        for (TsudioPaperOther tsudioPaperOther : selectpaper) {
            Map<String, Object> map = new HashMap<>();
            String paperId = tsudioPaperOther.getPaperId();
            String paperuserId = tsudioPaperOther.getUserId();
            String paperImg = tsudioPaperOther.getPaperImg();
            String paperAbout = tsudioPaperOther.getPaperAbout();
            String paperTag = tsudioPaperOther.getPaperTag();
            String paperModule = tsudioPaperOther.getPaperModule();
            String paperurl = tsudioPaperOther.getPaperurl();
            String createTime = tsudioPaperOther.getCreateTime();
            String updateTime = tsudioPaperOther.getUpdateTime();
            String paperTitle = tsudioPaperOther.getPaperTitle();

            //把标签数组化
            List<String> tagList = Arrays.asList(paperTag.split(","));
            String userId = null;

            try {
                //获取userid
                String loginId = StpUtil.getLoginId().toString();
                StudioUser getrole = userLogin.getrole(loginId);
                userId = getrole.getUserId();
            } catch (Exception e) {

            }

            //文章的userid和登录用户的userid进行比较，比较成功则是显示该文章的paperid
            if (paperuserId.equals(userId)){
                map.put("paperid",paperId);
            }else {
                map.put("paperid",null);
            }
            try {
                //判断是否有角色 管理员
                boolean c = StpUtil.hasRole("admin:common");
                if (c){
                    map.put("paperid",paperId);
                }
            } catch (java.lang.Exception e) {
            }

            map.put("about",paperAbout);
            map.put("img",paperImg);
            map.put("tag",tagList);
            map.put("module",paperModule);
            map.put("paperurl",paperurl);
            map.put("createtime",createTime);
            map.put("updatetime",updateTime);
            map.put("title",paperTitle);
            list.add(map);
        }
        return list;
    }

}
