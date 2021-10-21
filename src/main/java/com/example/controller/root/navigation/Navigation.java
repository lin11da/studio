package com.example.controller.root.navigation;

import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.Area;
import com.example.mapper.Studioimg;
import com.example.server.FileServer;
import com.example.utils.FileUtils;
import com.example.utils.Userreturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *  工作室富文本
 * @author chen
 * @version 1.0
 * @date 2021/9/15 21:03
 */
@CrossOrigin
@RestController
public class Navigation {

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
    Studioimg studioimg;

    @Autowired
    Area area;

    /**
     * 工作室文化
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/admin/updateculture")
    public Userreturn culture(@RequestParam("file") MultipartFile file,@RequestParam("areaid") String areaid) throws IOException {



        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        //time
        long timeMillis = System.currentTimeMillis();

        // 获得原始文件名+格式
        String fileName = file.getOriginalFilename();

        // 获取文件后缀名  在后面加 + 1 则是拿到后缀名  没有 （.）
        String fil_extension = fileName.substring(fileName.lastIndexOf("."));

        if (!".html".equals(fil_extension)){
            return new Userreturn<>(errorfileformat);
        }

        //随机文件名+后缀
        String getfile = FileUtils.generateFileName() + fil_extension;

        String path = "/usr/springboot/img/area/" + getfile;

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

            System.out.println("文件存在");

            Integer updatearea = area.updatearea(path, String.valueOf(timeMillis), areaid);
            if (updatearea==1){
                return new Userreturn<>();
            }
        }
        return new Userreturn<>(errorfilenotexist);
    }
}
