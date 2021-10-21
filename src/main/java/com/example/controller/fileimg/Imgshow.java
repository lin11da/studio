package com.example.controller.fileimg;

import com.example.pojo.other.FileImg;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/16 17:07
 */
@CrossOrigin
@RestController
public class Imgshow {
    //文件下载，视频播放，图片预览
    @PostMapping("/downfile")
    public void download(HttpServletResponse response, @RequestBody FileImg fileImg) throws IOException {
//        String basePath = "D:\\JAVA\\Demo\\SStudy\\springboot-fileupload-demo\\src\\main\\resources\\static\\";
//        System.out.println("---------------------------------------------");
//        System.out.println(basePath);
//        String filename = "1357668936.mp4";
//        System.out.println(filename);
//        System.out.println("---------------------------------------------");

        String filepath = fileImg.getFilepath();

        //读取文件内容
        FileInputStream is = new FileInputStream(filepath);
//        response.setHeader("content-disposition", "inline;fileName=" + URLEncoder.encode(filename, "UTF-8"));  //下载
        response.setHeader("Access-Control-Allow-Origin", "*");//图片
//        response.setContentType("video/mp4"); // 设置返回的文件类型   视频'

        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }
}
