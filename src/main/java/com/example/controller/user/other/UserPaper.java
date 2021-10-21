package com.example.controller.user.other;

import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.Paper;
import com.example.mapper.UserLogin;
import com.example.pojo.entity.StudioPaper;
import com.example.pojo.entity.StudioUser;
import com.example.pojo.other.StudioPaperOther2;
import com.example.pojo.other.TsudioPaperOther;
import com.example.server.FileServerSmall;
import com.example.server.PaperServer;
import com.example.utils.Userreturn;
import com.example.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/18 15:17
 */
@CrossOrigin
@RestController
public class UserPaper {

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
    private final String errorpaperupload = "文章上传失败";
    private final String errorpapernotpartofyou = "有部分篇文章不属于你，数据异常，删除失败，请刷新数据";
    private final String errorpapernull = "该文章已被删除，或没有该文章";
    private final String errorpapertitlenull = "搜索框数据格式输入错误";

    @Autowired
    private Paper ppaper;

    @Autowired
    UserLogin userLogin;

    @Autowired
    PaperServer paperServer;

    /**
     * 用户添加文章
     * @param img
     * @param paper
     * @param title
     * @param paperabout
     * @param tag
     * @param module
     * @return
     * @throws IOException
     */
    @PostMapping("/user/additionpaper")
    public Userreturn userinsertpaper(@RequestParam("img") MultipartFile img,@RequestParam("paper") MultipartFile paper,
                                      @RequestParam("title") String title,
                                      @RequestParam("paperabout") String paperabout,
                                      @RequestParam("tag") String tag,
                                      @RequestParam("module")String module
                                                                                ) throws IOException {

        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("user:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        //time
        long timeMillis = System.currentTimeMillis();

        //获取userid
        String loginId = StpUtil.getLoginId().toString();
        StudioUser getrole = userLogin.getrole(loginId);
        String userId = getrole.getUserId();

        String s = Utils.RandomStr(16);

        //图片url
        String imgfileseversmall = FileServerSmall.fileseversmall(img);

        //文章url
        String paperfileseversmall = FileServerSmall.fileseversmall(paper);

        //如果其中一个等于 false  则文章上传失败
        if (imgfileseversmall==null || paperfileseversmall ==null){
            return new Userreturn<>(errorpaperupload);
        }

        Integer insertpaper = ppaper.insertpaper(userId, s, title, paperabout, imgfileseversmall, paperfileseversmall, tag, module, String.valueOf(timeMillis), String.valueOf(timeMillis));
        if (insertpaper==1){
            return new Userreturn<>();
        }
        return new Userreturn<>(unknown);
    }

    /**
     * 查看文章   只有user:common 权限用户可以查看
     * @param title
     * @param pageNump
     * @return
     */

    @GetMapping("/user/paper")
    public Object selectpaper(@RequestParam(value = "title", required = false)String title,
                              @RequestParam(value ="pageNump", required = false,defaultValue = "1")Integer pageNump, @RequestParam(value ="tag", required = false) String tag,HttpServletResponse response)  {
        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("user:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        if (pageNump==null){
            pageNump=1;
        }

        Integer startSize = (pageNump-1)*8;
        Integer pageSize = 8;

        if (tag!=null&&title!=null){
          String url = "/user/paper";
          response.setStatus(302);
          return new Userreturn<>(302,"网页跳转","url:"+url);
        }


        if (StringUtils.isEmpty(title)){
            //查看所有文章
            List<TsudioPaperOther> selectpaper = ppaper.selectpaper(startSize,pageSize);

            List<Map> paper = paperServer.selectpaper(selectpaper);

            return new Userreturn<>(paper);
        }

        //如果title不为空  则按照title查询
        if (StringUtils.isNotEmpty(title)){
            List<TsudioPaperOther> selectpaperbytitle = ppaper.selectpaperbytitle(title, startSize, pageSize);
            List<Map> selectpaper = paperServer.selectpaper(selectpaperbytitle);
            return new Userreturn<>(selectpaper);
        }
        return new Userreturn<>(unknown);
    }


    /**
     * 修改文章
     * @param img
     * @param paper
     * @param title
     * @param paperabout
     * @param tag
     * @param module
     * @param paperid
     * @return
     * @throws IOException
     */
    @PostMapping("/other/upaper")
    public Userreturn updatepaper(@RequestParam("img") MultipartFile img,@RequestParam("paper") MultipartFile paper,
                                      @RequestParam("title") String title,
                                      @RequestParam("paperabout") String paperabout,
                                      @RequestParam("tag") String tag,
                                      @RequestParam("module")String module,
                                      @RequestParam("paperid")String paperid
                                                                                 ) throws IOException {
        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("user:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        //time
        long timeMillis = System.currentTimeMillis();

        //图片url
        String imgfileseversmall = FileServerSmall.fileseversmall(img);

        //文章url
        String paperfileseversmall = FileServerSmall.fileseversmall(paper);

        //如果其中一个等于 false  则文章上传失败
        if (imgfileseversmall==null || paperfileseversmall ==null){
            return new Userreturn<>(errorpaperupload);
        }

        Integer updatepaper = ppaper.updatepaper(paperid, title, paperabout, imgfileseversmall, paperfileseversmall, tag, module, String.valueOf(timeMillis));
        if (updatepaper==1){
            return new Userreturn<>();
        }
        return new Userreturn<>(unknown);
    }


    /**
     * 删除文章（管理员可以删除全部文章，用户只能删除自己的文章）
     * @param studioPaperOther2
     * @return
     * @throws Exception
     */
    @PostMapping("/user/ldpaper")
    public Userreturn logicdelectpaper( @RequestBody StudioPaperOther2 studioPaperOther2) throws Exception {
        //判断是否有角色
        boolean b = StpUtil.hasRole("user:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        String[] paperId = studioPaperOther2.getPaperId();
        String userId = null;


            //获取登录的账号
            String loginId = StpUtil.getLoginId().toString();
            //根据账号拿到user的userid
            StudioUser getrole = userLogin.getrole(loginId);


            //如果查询结果为空，则进入管理员权限判断，如果有管理员权限则进行删除
            if (getrole==null){
                //判断是否有角色  管理员
                boolean c = StpUtil.hasRole("admin:common");
                if (c){
                    int i = 0;
                    for (String s : paperId) {
                        //文章的删除
                        Integer logicdelectpaper = ppaper.logicdelectpaper(s);
                            if (logicdelectpaper==1){
                                i++;
                                if (paperId.length==i){
                                    return new Userreturn<>();
                                }
                            }
                        }
                    return new Userreturn<>(unknown);
                    }
                }else {
                userId = getrole.getUserId();
            }



            int i = 0;
            for (String s : paperId) {

                String paperuserId = null;
                try {
                    StudioPaper paperidgetuserid = ppaper.paperidgetuserid(s);
                    paperuserId = paperidgetuserid.getUserId();
                } catch (java.lang.Exception e) {
                    return new Userreturn<>(errorpapernull);
                }

                //文章的userid和登录用户的userid进行比较，比较成功则可以对该文章进行删除  比较失败异常退出
                if (userId.equals(paperuserId)){
                    Integer logicdelectpaper = ppaper.logicdelectpaper(s);
                    if (logicdelectpaper==1){
                        i++;
                        if (paperId.length==i){
                            return new Userreturn<>();
                        }
                    }
                }else {
                    return new Userreturn<>(errorpapernotpartofyou);
                }

            }

        return new Userreturn<>(unknown);


    }
}
