package com.af.blog.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * 文件工具类
 */
public class FileUtils {

    /**
     * 文件上传方法
     * @param file 文件对象
     * @return 返回信息
     */
    public static String upload(MultipartFile file, String type) {

        long timestamp = new Date().getTime();   // 时间戳重命名图片

        String suffixName = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf('.'));
        String filePath = "/data/images/" + type + "/";
        String fileName = timestamp + suffixName;
        File dest = new File(filePath + fileName);

        try {
            file.transferTo(dest);
            return type + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
