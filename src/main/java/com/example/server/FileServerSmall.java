package com.example.server;

import com.example.utils.FileUtils;
import com.example.utils.Userreturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/18 15:33
 */
@Service
public class FileServerSmall {


    public static String fileseversmall(MultipartFile file) throws IOException {


        // 获得原始文件名+格式
        String fileName = file.getOriginalFilename();

        // 获取文件后缀名  在后面加 + 1 则是拿到后缀名  没有 （.）
        String fil_extension = fileName.substring(fileName.lastIndexOf("."));

        //随机文件名+后缀
        String getfile = FileUtils.generateFileName() + fil_extension;

        String path = null;
        if (".png".equals(fil_extension)){
            path = "/usr/springboot/img/paper/paperimg/" + getfile;
        }

        if (".html".equals(fil_extension)){
            path = "/usr/springboot/img/paper/paperfile/" + getfile;
        }

        // FileUtils.write(path, file.getInputStream()); ==  fileServer.upload(file,path);
        FileUtils.write(path, file.getInputStream());

        File filein = new File(path);

        if (!filein.exists()) {
            System.out.println("文件不存在");
            return null;
        }else {
            System.out.println("文件存在");
            return path;
        }
    }


    public static String user_account_pictures(MultipartFile file) throws IOException {


        // 获得原始文件名+格式
        String fileName = file.getOriginalFilename();

        // 获取文件后缀名  在后面加 + 1 则是拿到后缀名  没有 （.）
        String fil_extension = fileName.substring(fileName.lastIndexOf("."));

        //随机文件名+后缀
        String getfile = FileUtils.generateFileName() + fil_extension;

        String path = null;
        if (".png".equals(fil_extension)){
            path = "/usr/springboot/img/paper/user_img/" + getfile;
        }else {
            return "error,invalid_format";
        }

        // FileUtils.write(path, file.getInputStream()); ==  fileServer.upload(file,path);
        FileUtils.write(path, file.getInputStream());

        File filein = new File(path);

        if (!filein.exists()) {
            System.out.println("文件不存在");
            return null;
        }else {
            System.out.println("文件存在");
            return path;
        }


    }
}
