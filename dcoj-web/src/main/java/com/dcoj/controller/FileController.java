package com.dcoj.controller;


import com.alibaba.fastjson.JSONObject;
import com.dcoj.config.DcojConfig;
import com.dcoj.controller.exception.WebErrorException;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.util.FileUploadUtils;
import com.dcoj.util.WebUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.annotation.ElementType;

/**
 * @author WANGQING
 * @author Leon
 */
@RestController
@RequestMapping(value = "/file", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FileController {

    @GetMapping(value = "/code/**")
    public JSONObject getUrlFile(HttpServletRequest request) {
        String uri = request.getRequestURI().substring(10);
        // 通过url创建文件
        File file = new File(DcojConfig.getUploadPath()+"\\"+uri);
        //判断文件是否存在如果不存在就返回异常
        WebUtil.assertIsSuccess((file.exists() && file.canRead()), "文件不存在");
        FileInputStream inputStream = null;
        String content = "";
        try {
            inputStream = new FileInputStream(file);
//            byte[] data = new byte[(int) file.length()];
//            int length = inputStream.read(data);
            inputStream.close();
            content = FileUtils.readFileToString(file, "UTF-8");
        } catch (FileNotFoundException e) {
            throw new WebErrorException("文件不存在");
        } catch (IOException e) {
            throw new WebErrorException("读取错误");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", content);
        return jsonObject;
    }
}
