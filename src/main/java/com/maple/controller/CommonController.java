package com.maple.controller;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.maple.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * ClassName:CommonController
 * Package: com.maple.controller
 * Description:
 * Author maple
 * Create 2023-01-16
 * Version: v1.0
 */

@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${reggie.path}")
    private String basePath;
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){

        String name = file.getOriginalFilename();
        // 后缀添加一个uuid来防止重复
        String uuid = UUID.randomUUID().toString();
        // 截取后缀的信息
        int i = name.lastIndexOf('.');
        String suffix = name.substring(i);
        // 实际保存的目录
        String fileName = name.substring(0,i) + uuid + suffix;
        File dir = new File(basePath);
        // 判断当前的目录是否存在, 若不存在就创建一个
        if(!dir.exists())
        {
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    @GetMapping("/download")
    public void downLoad(String name, HttpServletResponse response) {
        // 通过输入流读取文件的内容
        ServletOutputStream outputStream = null;
        FileInputStream inputStream = null;
        response.setContentType("image/jpeg");
        try {
            inputStream = new FileInputStream(new File(basePath + name));
            outputStream = response.getOutputStream();
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
