package com.example.controller.root.other;

import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.Other;
import com.example.pojo.entity.StudioHonorWall;
import com.example.pojo.other.StudioHonorwallother;
import com.example.server.FileServer;
import com.example.utils.FileUtils;
import com.example.utils.Userreturn;
import com.example.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
 *  荣誉墙
 * @author chen
 * @version 1.0
 * @date 2021/9/16 15:15
 */
@CrossOrigin
@RestController
public class HonorWall {
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
     * 添加荣誉墙内容
     * @param file
     * @param honor
     * @param playingtime
     * @param compete
     * @param project
     * @return
     */
    @PostMapping("/admin/addhonorwall")
    public Userreturn inserthonorwall(@RequestParam("file") MultipartFile file,@RequestParam("honor") String honor,
                                      @RequestParam("playingtime") String playingtime,@RequestParam("compete") String compete,
                                      @RequestParam("project") String project) throws IOException {
        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("admin:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }
        //id
        String id = Utils.RandomStr(16);

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

        String path = "/usr/springboot/img/honorwall/" + getfile;

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

            Integer inserthonorwall = other.inserthonorwall(id, honor, path, playingtime, compete, project, String.valueOf(timeMillis), String.valueOf(timeMillis));
            if (inserthonorwall==1){
                return new Userreturn<>();
            }
        }
        return new Userreturn<>(errorfilenotexist);
    }

    /**
     * 查看荣誉
     * @return
     */
    @GetMapping("/admin/selecthonorwall")
    public Userreturn selecthonorwall(){

        List<Map> list = new LinkedList<>();

        //遍历出去全部的荣誉信息
        List<StudioHonorWall> selecthonorwall = other.selecthonorwall();
        for (StudioHonorWall honorWall : selecthonorwall) {
            Map<String, String> map = new HashMap<>();
            String honor = honorWall.getHonor();
            String compete = honorWall.getCompete();
            String playingTime = honorWall.getPlayingTime();
            String honorImgUrl = honorWall.getHonorImgUrl();
            String project = honorWall.getProject();
            String honorId = honorWall.getHonorId();
            try {
                //判断是否有角色 管理员
                boolean b = StpUtil.hasRole("admin:common");
                if (b){
                    map.put("id",honorId);
                }
            } catch (java.lang.Exception e) {
            }
            map.put("honor",honor);
            map.put("compete",compete);
            map.put("playingtime",playingTime);
            map.put("imgUrl",honorImgUrl);
            map.put("project",project);

            list.add(map);
        }
        return new Userreturn<>(list);
    }


    /**
     * 更新某个荣誉
     * @param file
     * @param honor
     * @param playingtime
     * @param compete
     * @param project
     * @param id
     * @return
     * @throws IOException
     */
    @PostMapping("/admin/updatehonorwall")
    public Userreturn updatehonorwall(@RequestParam("file") MultipartFile file,@RequestParam("honor") String honor,
                                      @RequestParam("playingtime") String playingtime,@RequestParam("compete") String compete,
                                      @RequestParam("project") String project,@RequestParam("id") String id) throws IOException {
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

        String path = "/usr/springboot/img/honorwall/" + getfile;

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

            Integer inserthonorwall = other.updatehonorwall(id, honor, path, playingtime, compete, project, String.valueOf(timeMillis));
            if (inserthonorwall==1){
                return new Userreturn<>();
            }
        }
        return new Userreturn<>(errorfilenotexist);
    }

    /**
     * 删除某个荣誉
     * @param studioHonorwallother
     * @return
     */
    @PostMapping("/admin/deletehonorwall")
    public Userreturn deletehonorwall(@RequestBody StudioHonorwallother studioHonorwallother){
        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        int i = 0;

        String[] honorId = studioHonorwallother.getHonorId();

        for (String s : honorId) {
            Integer deletehonorwall = other.deletehonorwall(s);
            if (deletehonorwall==1){
                i++;
                if (honorId.length==i){
                    return new Userreturn<>();
                }
            }
        }
        return new Userreturn<>(Exception);

    }


}
