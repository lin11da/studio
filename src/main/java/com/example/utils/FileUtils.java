package com.example.utils;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 文件操作工具类
 */
public class FileUtils {

    /**
     * 写入文件
     *
     * @param path
     * @param src  流
     * @throws IOException
     */
    public static void write(String path, InputStream src) throws IOException {

        OutputStream os = new FileOutputStream(path);
        byte[] buf = new byte[1024];
        int len;
        while (-1 != (len = src.read(buf))) {
            os.write(buf, 0, len);
        }
        os.flush();
        os.close();
    }


    /**
     * 生成随机文件名
     *
     * @return
     */
    public static String generateFileName() {
        return UUID.randomUUID().toString();
    }
}
