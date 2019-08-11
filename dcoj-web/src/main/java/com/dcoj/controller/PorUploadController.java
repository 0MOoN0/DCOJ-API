package com.dcoj.controller;

import com.dcoj.entity.PorUploadResultEntity;
import com.dcoj.service.PorUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zxw
 * @Desriiption: 头像上传控制器
 */
@Controller
@RequestMapping(value = "por")
public class PorUploadController {

    @Autowired
    private PorUploadService porUploadService;

    @ResponseBody
    @RequestMapping(value = "/uploadPor",method = RequestMethod.POST)
    public PorUploadResultEntity uploadPor(@RequestParam("portrait") MultipartFile multipartFile){
        return this.porUploadService.upload(multipartFile);
    }
}
