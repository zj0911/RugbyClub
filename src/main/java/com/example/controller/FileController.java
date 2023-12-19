package com.example.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    //文件上传路径
    private static final String filePath = System.getProperty("user.dir")+"/file/";

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        synchronized (FileController.class){
            //时间戳（防止同名
            String flag = System.currentTimeMillis() + " ";
            String fileName = file.getOriginalFilename();
            try{
                if(!FileUtil.isDirectory(filePath)){
                    FileUtil.mkdir(filePath);
                }
                //存储形式：时间-名称
                FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
                System.out.println(fileName + "--文件上传成功");
                Thread.sleep(1L);
            }catch (Exception e){
                System.err.println(fileName + "-- 文件上传失败");
            }
            return Result.success(flag);
        }
    }

    /**
     * 文件下载
     */
    @GetMapping("/{flag}")
    public void avatarPath (@PathVariable String flag, HttpServletResponse response){
        if(!FileUtil.isDirectory(filePath)){
            FileUtil.mkdir(filePath);
        }
        OutputStream os;
        List<String> fileName = FileUtil.listFileNames(filePath);
        String avatar = fileName.stream().filter(name-> name.contains(flag)).findAny().orElse("");
        try{
            if(StrUtil.isNotEmpty(avatar)){
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + avatar);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e){
            System.out.println("文件下载失败");
        }
    }
}
