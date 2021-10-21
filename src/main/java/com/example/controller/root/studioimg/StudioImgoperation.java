package com.example.controller.root.studioimg;

import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.Studioimg;
import com.example.pojo.entity.StudioAdminHomePageImg;
import com.example.pojo.entity.StudioLogo;
import com.example.server.FileServer;
import com.example.utils.FileUtils;
import com.example.utils.Userreturn;
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
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/15 18:53
 */
@CrossOrigin
@RestController
public class StudioImgoperation {

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
    private FileServer fileServer;

    @Autowired
    Studioimg studioimg;

    /**
     * 更新logo
     * @param file
     * @return
     * @throws IOException
     */

    @PostMapping("/admin/uploadlogofile")
    public Userreturn upload(@RequestParam("file") MultipartFile file) throws IOException {
        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        Map<String, String> map = new HashMap<>();

        String logoid = "logoid";

        //time
        long timeMillis = System.currentTimeMillis();

        // 获得原始文件名+格式
        String fileName = file.getOriginalFilename();

        // 获取文件后缀名  在后面加 + 1 则是拿到后缀名  没有 （.）
        String fil_extension = fileName.substring(fileName.lastIndexOf("."));

        if (!".png".equals(fil_extension)){
            return new Userreturn<>(errorfileformat);
        }

        //随机文件名+后缀
        String getfile = FileUtils.generateFileName() + fil_extension;

        String path = "/usr/springboot/img/logo/" + getfile;

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

            Integer updatelogourl = studioimg.updatelogourl(path, String.valueOf(timeMillis), logoid);
            if (updatelogourl==1){
                return new Userreturn<>(map);
            }
        }
        return new Userreturn<>(errorfilenotexist);
    }

    /**
     * 查看logo
     * @return
     */
    @GetMapping("/logo")
    public Userreturn logo(){

        Map<String, String> map = new HashMap<>();
        StudioLogo logourl = studioimg.logourl();
        String logoUrl = logourl.getLogoUrl();

        try {
            //判断是否有角色  超级管理员
            boolean b = StpUtil.hasRole("admin:*");
            if (b){
                map.put("logoid",logourl.getLogoId());
            }
        } finally {
            map.put("url",logoUrl);
            return new Userreturn<>(map);
        }

    }

    /**
     * =============================================================================================================
     */


    /**
     * 轮播图更改
     * @param file
     * @param imgid
     * @return
     * @throws IOException
     */
    @PostMapping("/admin/uploadhomeimgfile")
    public Userreturn homeimg(@RequestParam("file") MultipartFile[] file,@RequestParam("imgid") String[] imgid) throws IOException {
        //判断是否有角色  超级管理员
        boolean b = StpUtil.hasRole("admin:*");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        int i = 0;

        Map<String, String> map = new HashMap<>();
        List<String> list = new LinkedList<>();


        for (String s : imgid) {
            list.add(s);
        }

        //time
        long timeMillis = System.currentTimeMillis();


        for (MultipartFile multipartFile : file) {
            // 获得原始文件名+格式
            String fileName = multipartFile.getOriginalFilename();

            // 获取文件后缀名  在后面加 + 1 则是拿到后缀名  没有 （.）
            String fil_extension = fileName.substring(fileName.lastIndexOf("."));

            if (!".png".equals(fil_extension)){
                return new Userreturn<>(errorfileformat);
            }

            //随机文件名+后缀
            String getfile = FileUtils.generateFileName() + fil_extension;

            String path = "src/main/resources/img/homeimg/" + getfile;

            fileServer.upload(multipartFile,path);

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
                String s = list.get(i);

                Integer updatelogourl = studioimg.updatehomeurl(path, String.valueOf(timeMillis), s);
                if (updatelogourl==1){
                    i++;
                    if (file.length==i){
                        return new Userreturn<>();
                    }

                }
            }

        }
        return new Userreturn<>(errorfilenotexist);
    }

    /**
     * 轮播图url
     * @return
     */
    @GetMapping("/homeimg")
    public Userreturn homeimg(){

        List<Map> listmap = new LinkedList<>();

        List<StudioAdminHomePageImg> selecthomeurl = studioimg.selecthomeurl();
        for (StudioAdminHomePageImg studioAdminHomePageImg : selecthomeurl) {
            Map<String, String> map = new HashMap<>();
            String imgId = studioAdminHomePageImg.getImgId();
            String imgUrl = studioAdminHomePageImg.getImgUrl();


            //判断是否有角色 管理员
            try {
                boolean b = StpUtil.hasRole("admin:common");
                if (b){
                    map.put("imgid",imgId);
                    map.put("imgurl",imgUrl);
                    listmap.add(map);
                }
            } catch (Exception e){
                map.put("imgurl",imgUrl);
                listmap.add(map);
            }

        }

        return new Userreturn<>(listmap);
    }

}
