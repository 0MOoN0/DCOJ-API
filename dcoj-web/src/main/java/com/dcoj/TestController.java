package com.dcoj;


import com.dcoj.config.DcojConfig;
import com.dcoj.util.FileUploadUtils;
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


    /**
     * 保存头像
     */
    @PostMapping("/updateAvatar")
    @ResponseBody
    public Object updateAvatar(@RequestParam("avatarfile") MultipartFile file) {
        System.out.println("file:"+file);
        try {
            if (!file.isEmpty()) {
                String avatar = FileUploadUtils.uploadPicture(DcojConfig.getAvatarPath(), file);
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
    @PostMapping("/updateFile")
    @ResponseBody
    public Object updateFile(@RequestParam("file") MultipartFile file) {
        System.out.println("file:"+file);
        try {
            if (!file.isEmpty()) {
                String fileName = FileUploadUtils.uploadText(DcojConfig.getUploadPath(), file);
                System.out.println("fileName:" + fileName);
                // TODO: 将file保存到数据库
            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }



//    @GetMapping("/download")
//    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
//        String fileName = "dalaoyang.jpeg";// 文件名
//        if (fileName != null) {
//            //设置文件路径
//            File file = new File("/Users/dalaoyang/Documents/dalaoyang.jpeg");
//            //File file = new File(realPath , fileName);
//            if (file.exists()) {
//                response.setContentType("application/force-download");// 设置强制下载不打开
//                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while (i != -1) {
//                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
//                    }
//                    return "下载成功";
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        return "下载失败";
//    }


    @RequestMapping(value = "/noLogin/readImageFile",method = RequestMethod.GET)
    @ResponseBody
    public void getUrlFile(String url, HttpServletRequest request, HttpServletResponse response) {
        url = "D:/profile/upload/2019/05/17/a859f740a59f1ad64d65efd24f323102.java";

        File file = new File(url);
        // 后缀名
        String suffixName = url.substring(url.lastIndexOf("."));
        //判断文件是否存在如果不存在就返回默认图标
        if (!(file.exists() && file.canRead())) {
//            file = new File(request.getSession().getServletContext().getRealPath("/")
//                    + "resource/icons/auth/root.png");
            System.out.println("文件不存在");
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            //setContentType("text/plain; charset=utf-8"); 文本
//            response.setContentType("image/png;charset=utf-8");
            response.setContentType("text/html;charset=utf-8");
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
