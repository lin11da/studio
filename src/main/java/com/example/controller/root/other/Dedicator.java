package com.example.controller.root.other;

import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.Other;
import com.example.pojo.entity.StudioMember;
import com.example.pojo.entity.StudioWebDedicator;
import com.example.pojo.other.StudioDicatorOther;
import com.example.server.FileServer;
import com.example.utils.FileUtils;
import com.example.utils.Userreturn;
import com.example.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *  网站奉献者
 * @author chen
 * @version 1.0
 * @date 2021/9/17 21:22
 */
@CrossOrigin
@RestController
public class Dedicator {
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
     * 添加网站奉献者信息
     * @param file
     * @param name
     * @param about
     * @param timeline
     * @return
     */
    @PostMapping("/admin/adddedicator")
    public Userreturn inserthonorwall(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
                                      //成员简介                                    时间线
                                      @RequestParam("about") String about, @RequestParam("timeline") String timeline) throws IOException {

        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("admin:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        //id
        String dedicatorid = Utils.RandomStr(16);

        //time
        long timeMillis = System.currentTimeMillis();

        Map<String, String> map = new HashMap<>();

        // 获得原始文件名+格式
        String fileName = file.getOriginalFilename();

        // 获取文件后缀名  在后面加 + 1 则是拿到后缀名  没有 （.）
        String fil_extension = fileName.substring(fileName.lastIndexOf("."));

        if (!".png".equals(fil_extension)){
            return new Userreturn<>(errorfileformat);
        }
        //随机文件名+后缀
        String getfile = FileUtils.generateFileName() + fil_extension;

        String path = "/usr/springboot/img/dedicator/" + getfile;

        fileServer.upload(file,path);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File filein = new File(path);

        if (!filein.exists()) {
            System.out.println("文件不存在");
        }else {
            map.put("logourl",path);
            System.out.println("文件存在");

            Integer insertmembers = other.insertdedicator(dedicatorid, name, path, about, timeline, String.valueOf(timeMillis), String.valueOf(timeMillis));
            if (insertmembers==1){
                return new Userreturn<>();
            }
        }
        return new Userreturn<>(errorfilenotexist);
    }






    /**
     * 查看网站奉献者信息
     * @return
     */
    @GetMapping("/admin/selectdedicator")
    public Userreturn selecthonorwall(){

        List<Map> list = new LinkedList<>();

        //遍历出去全部的荣誉信息
        List<StudioWebDedicator> selectdedicator = other.selectdedicator();

        for (StudioWebDedicator studioWebDedicator : selectdedicator) {

            Map<String, String> map = new HashMap<>();
            String dedicatorId = studioWebDedicator.getDedicatorId();
            String dedicatorName = studioWebDedicator.getDedicatorName();
            String dedicatorImgUrl = studioWebDedicator.getDedicatorImgUrl();
            String dedicatorAbout = studioWebDedicator.getDedicatorAbout();
            String timeline = studioWebDedicator.getTimeline();

            try {
                //判断是否有角色 管理员
                boolean b = StpUtil.hasRole("admin:common");
                if (b){
                    map.put("id",dedicatorId);
                }
            } catch (java.lang.Exception e) {
            }
            map.put("name",dedicatorName);
            map.put("about",dedicatorAbout);
            map.put("url",dedicatorImgUrl);
            map.put("timeline",timeline);

            list.add(map);
        }
        return new Userreturn<>(list);
    }




    /**
     * 更新某个成员介绍
     * @param file
     * @param file
     * @param name
     * @param about
     * @param timeline
     * @return
     * @throws IOException
     */
    @PostMapping("/admin/updatededicator")
    public Userreturn updatehonorwall(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
                                      //成员简介                                    时间线
                                      @RequestParam("about") String about, @RequestParam("timeline") String timeline,@RequestParam("dedicatorid")String dedicatorid) throws IOException {
        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("admin:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }


        //time
        long timeMillis = System.currentTimeMillis();

        Map<String, String> map = new HashMap<>();

        // 获得原始文件名+格式
        String fileName = file.getOriginalFilename();

        // 获取文件后缀名  在后面加 + 1 则是拿到后缀名  没有 （.）
        String fil_extension = fileName.substring(fileName.lastIndexOf("."));

        if (!".png".equals(fil_extension)){
            return new Userreturn<>(errorfileformat);
        }

        //随机文件名+后缀
        String getfile = FileUtils.generateFileName() + fil_extension;

        String path = "/usr/springboot/img/members/" + getfile;

        fileServer.upload(file,path);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File filein = new File(path);

        if (!filein.exists()) {
            System.out.println("文件不存在");
        }else {
            map.put("logourl",path);
            System.out.println("文件存在");

            Integer updatededicator = other.updatededicator(dedicatorid, name, path, about, timeline, String.valueOf(timeMillis));
            if (updatededicator==1){
                return new Userreturn<>();
            }
        }
        return new Userreturn<>(errorfilenotexist);
    }


    /**
     * 删除某个或几个网站奉献者信息
     * @param studioDicatorOther
     * @return
     */
    @PostMapping("/admin/deletededicator")
    public Userreturn deletehonorwall(@RequestBody StudioDicatorOther studioDicatorOther){
        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        int i = 0;

        String[] dedicatorId = studioDicatorOther.getDedicatorId();

        for (String s : dedicatorId) {
            Integer deletededicator = other.deletededicator(s);
            if (deletededicator==1){
                i++;
                if (dedicatorId.length==i){
                    return new Userreturn<>();
                }
            }
        }
        return new Userreturn<>(Exception);
    }
}
