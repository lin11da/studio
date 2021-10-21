package com.example.server;


import com.example.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServer {


    /**
     * 上传文件
     */
    public void upload(MultipartFile file,String path) throws IOException {

        FileUtils.write(path, file.getInputStream());

    }


}
