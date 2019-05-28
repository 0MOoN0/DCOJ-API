package com.dcoj;


import com.dcoj.config.DcojConfig;
import com.dcoj.judge.LanguageEnum;
import com.dcoj.util.FileUploadUtils;
import com.dcoj.util.WebUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author WANGQING
 */
@Controller
public class TestController {

    public void testMethod() throws IOException{
        // 上传源码 需抛出异常
        String code = "";
        //需传入3个参数
        //第一个参数 默认固定就那样
        //第二个参数 code 字符串
        //第三个参数 LanguageEnum
        String fileName = FileUploadUtils.uploadCode(DcojConfig.getUploadPath(),
                code,
                LanguageEnum.valueOf("JAVA8"));

        // ==================================================================================

        // 读取文件
        // 通过url创建文件,只需传入String url的参数
        String url = "";
        File file = new File(url);
        // 后缀名
        String suffixName = url.substring(url.lastIndexOf("."));
        //判断文件是否存在如果不存在就返回异常
        WebUtil.assertIsSuccess((file.exists() && file.canRead()), "文件不存在");
        FileInputStream inputStream = null;
        // 读取文件，将数据保存到content里
        String content = "";
        try {
//            inputStream = new FileInputStream(file);
//            byte[] data = new byte[(int) file.length()];
//            int length = inputStream.read(data);
//            inputStream.close();
            content = FileUtils.readFileToString(file, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 保存头像
     */
    @PostMapping("/updateAvatar")
    @ResponseBody
    public Object updateAvatar(@RequestParam("avatarfile") MultipartFile file) {
        System.out.println("file:"+file);
        try {
            if (!file.isEmpty()) {
                String avatar = FileUploadUtils.uploadAvatar(DcojConfig.getAvatarPath(), file);
                System.out.println("avatar:" + avatar);
                // TODO: 将avatar保存到数据库
            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }


    /**
     * 保存文件
     */
    @GetMapping("/updateFile")
    @ResponseBody
    public Object updateFile(String code, LanguageEnum lang) {
        try{
            code = "\"package com.dcoj.controller;\\r\\n\\r\\nimport com.dcoj.entity.ResponseEntity;\\r\\nimport com.dcoj.service.ProgramProblemService;\\r\\nimport com.dcoj.util.WebUtil;\\r\\nimport com.github.pagehelper.Page;\\r\\nimport com.github.pagehelper.PageHelper;\\r\\nimport io.swagger.annotations.Api;\\r\\nimport io.swagger.annotations.ApiImplicitParam;\\r\\nimport io.swagger.annotations.ApiImplicitParams;\\r\\nimport io.swagger.annotations.ApiOperation;\\r\\nimport org.springframework.beans.factory.annotation.Autowired;\\r\\nimport org.springframework.http.MediaType;\\r\\nimport org.springframework.validation.annotation.Validated;\\r\\nimport org.springframework.web.bind.annotation.*;\\r\\n\\r\\nimport java.util.List;\\r\\n\\r\\n/**\\r\\n * 对多个ProgramProblem进行操作\\r\\n *\\r\\n * @author WANGQING\\r\\n */\\r\\n@RestController\\r\\n@Validated\\r\\n@Api\\u0028tags = \\\"多个编程题管理\\\"\\u0029\\r\\n@RequestMapping\\u0028value = \\\"/program-problems\\\", produces = MediaType.APPLICATION_JSON_UTF8_VALUE\\u0029\\r\\npublic class ProgramProblemsController {\\r\\n @Autowired\\r\\n private ProgramProblemService programProblemService;\\r\\n\\r\\n @ApiOperation\\u0028\\\"获取所有题目\\\"\\u0029\\r\\n @ApiImplicitParams\\u0028{\\r\\n @ApiImplicitParam\\u0028name = \\\"page_num\\\", value = \\\"页码\\\", required = true, paramType = \\\"query\\\"\\u0029,\\r\\n @ApiImplicitParam\\u0028name = \\\"page_size\\\", value = \\\"每页显示数量\\\", required = true, paramType = \\\"query\\\"\\u0029,\\r\\n @ApiImplicitParam\\u0028name = \\\"tag_list\\\", value = \\\"标签列表\\\", paramType = \\\"query\\\"\\u0029,\\r\\n @ApiImplicitParam\\u0028name = \\\"uid\\\", value = \\\"用户id\\\", paramType = \\\"query\\\"\\u0029,\\r\\n @ApiImplicitParam\\u0028name = \\\"difficult\\\", value = \\\"题目难度\\\", paramType = \\\"query\\\"\\u0029,\\r\\n @ApiImplicitParam\\u0028name = \\\"query\\\", value = \\\"查询关键字\\\", paramType = \\\"query\\\"\\u0029\\r\\n }\\u0029\\r\\n @GetMapping\\r\\n public ResponseEntity listAll\\u0028@RequestParam\\u0028name = \\\"tag_list\\\", required = false\\u0029 List\\u003CInteger\\u003E list,\\r\\n @RequestParam\\u0028name = \\\"uid\\\", required = false\\u0029 Integer uid,\\r\\n @RequestParam\\u0028name = \\\"difficult\\\", required = false\\u0029 Integer difficult,\\r\\n @RequestParam\\u0028name = \\\"query\\\", required = false\\u0029 String query,\\r\\n @RequestParam\\u0028name = \\\"page_num\\\"\\u0029 int pageNum,\\r\\n @RequestParam\\u0028name = \\\"page_size\\\"\\u0029 int pageSize\\u0029 {\\r\\n // pageNum 页码\\r\\n // pageSize 每页显示数量\\r\\n Page pager = PageHelper.startPage\\u0028pageNum, pageSize\\u0029;\\r\\n return new ResponseEntity\\u0028WebUtil.generatePageData\\u0028pager, programProblemService.listAll\\u0028list, uid, difficult, query\\u0029\\u0029\\u0029;\\r\\n }\\r\\n\\r\\n}\\r\\n\"";

            String fileName = FileUploadUtils.uploadCode(DcojConfig.getUploadPath(),
                    code,
                    LanguageEnum.valueOf("JAVA8"));
            System.out.println("fileName:"+fileName);
            return "success";
        }catch (Exception e){
            return "error";
        }

    }


    @RequestMapping(value = "/noLogin/readImageFile",method = RequestMethod.GET)
    @ResponseBody
    public String getUrlFile(String url, HttpServletRequest request, HttpServletResponse response) {
//        url = "D:/profile/upload/2019/05/20/01017f12b88d9f4e79dc38060e3883da.java";

        // 通过url创建文件
        File file = new File(DcojConfig.getUploadPath()+"\\"+url);
        // 后缀名
        String suffixName = url.substring(url.lastIndexOf("."));
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
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
