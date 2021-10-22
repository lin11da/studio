package com.example.controller.root.other;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.example.mapper.Other;
import com.example.pojo.other.List_class;
import com.example.pojo.other.Operation_Log.StudioWorks;
import com.example.server.FileServer;
import com.example.utils.FileUtils;
import com.example.utils.Userreturn;
import com.example.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/10/21 21:42
 */
@RestController
@CrossOrigin
public class Works {
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
     * 添加作品
     * @param file_files
     * @param work_name
     * @param work_about
     * @param work_time
     * @param workid
     * @return
     * @throws IOException
     */
    @SaCheckRole("admin:*")
    @PostMapping("/admin/insert_works")
    public Userreturn insert_Works(@RequestParam("file_files") MultipartFile file_files,
                                   @RequestParam("work_name")String work_name,@RequestParam("work_about")String work_about,
                                   @RequestParam("work_time")String work_time,@RequestParam("workid")String workid) throws IOException {
        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("admin:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        //time
        long timeMillis = System.currentTimeMillis();

        // 获得原始文件名+格式
        String fileName = file_files.getOriginalFilename();

        // 获取文件后缀名  在后面加 + 1 则是拿到后缀名  没有 （.）
        String fil_extension = fileName.substring(fileName.lastIndexOf("."));

        //随机文件名+后缀
        String getfile = FileUtils.generateFileName() + fil_extension;

//        String path = "/usr/springboot/img/" + getfile;
        String path = "D:\\JAVA\\Demo2\\studio\\src\\main\\resources\\img\\" + getfile;

        fileServer.upload(file_files,path);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File filein = new File(path);

        if (!filein.exists()) {
            System.out.println("文件不存在");
        }else {
            Integer integer = other.insert_work(workid, work_name, work_about, work_time, path, String.valueOf(timeMillis), String.valueOf(timeMillis));
            if (integer==1){
                return new Userreturn<>();
            }
        }
        return new Userreturn<>(unknown);

    }

    /**
     * 添加作品_img  和上面联动
     * @param file_img
     * @param workid
     * @return
     * @throws IOException
     */
    @SaCheckRole("admin:*")
    @PostMapping("/admin/insert_works_img")
    public Userreturn insert_Works_img(@RequestParam("file_img") MultipartFile file_img,@RequestParam("workid")String workid) throws IOException {

        //判断是否有角色  管理员
        boolean b = StpUtil.hasRole("admin:common");
        if (!b){
            return new Userreturn<>(401,errorauthority);
        }

        // 获得原始文件名+格式
        String fileName = file_img.getOriginalFilename();

        // 获取文件后缀名  在后面加 + 1 则是拿到后缀名  没有 （.）
        String fil_extension = fileName.substring(fileName.lastIndexOf("."));

        //随机文件名+后缀
        String getfile = FileUtils.generateFileName() + fil_extension;

//        String path = "/usr/springboot/img/" + getfile;
        String path = "D:\\JAVA\\Demo2\\studio\\src\\main\\resources\\img\\" + getfile;

        fileServer.upload(file_img,path);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File filein = new File(path);

        if (!filein.exists()) {
            System.out.println("文件不存在");
        }else {
            Integer integer =  other.insert_work_img(workid,path);
            if (integer==1){
                return new Userreturn<>();
            }
        }
        return new Userreturn<>(unknown);
    }

    /**
     * 查找作品
     * @return
     */
    @SaCheckRole("user:common")
    @GetMapping("/admin/select_works")
    public Userreturn select_works(){
        List<StudioWorks> studioWorks = other.select_work();
        return new Userreturn<>(studioWorks);
    }


    /**
     * 删除作品
     * @param list_class
     * @return
     */
    @SaCheckRole("admin:*")
    @PostMapping("/admin/delect_works")
    public Userreturn delect_works(@RequestBody List_class list_class){
        String[] workid = list_class.getWorkid();
        int i = 0;
        for (String s : workid) {
            Integer integer = other.delete_works(s);
            if (integer==1){
                i++;
                if(workid.length ==i){
                    return new Userreturn<>();
                }
            }
        }
        return new Userreturn<>(unknown);
    }

}
