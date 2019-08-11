package com.dcoj.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author zxw
 * @Desriiption: 阿里云oss操作工具类
 */
@Configuration
@PropertySource("aliyunoss.properties")
@ConfigurationProperties(prefix = "aliyunoss")
public class AliyunOssUtils {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String urlPrefix;
    /**
     *  获取ossclient实例
     * @return 返回oss对象
     */
    @Bean
    public OSS oSSClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     *  通过文件名获取文件路径
     * @param sourceFileName
     * @return
     */
    public static final String  getFilePath(String sourceFileName) {
        DateTime dateTime = new DateTime();
        return "portrait/" + dateTime.toString("yyyy")
                + "/" + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/" + System.currentTimeMillis() +
                RandomUtils.nextInt(100, 9999) + "." +
                StringUtils.substringAfterLast(sourceFileName, ".");
    }

    /**
     *  图片处理
     * @return
     */
    public static final InputStream handlePor(String sourceFileName, MultipartFile multipartFile){
        String contextPath = getFilePath(sourceFileName);  //portrait/2019/08/03/ss.jpg

        //设置统一图片后缀名
        String suffixName;
        //获取图片后缀名
        String suffixNameOrigin = StringUtils.substringAfterLast(contextPath, ".");

        if ("png".equals(suffixNameOrigin)) {
            suffixName = "png";
        } else {
            suffixName = "jpg";
        }

        //存的项目的中模版图片
        File tempFile = null;
        //上传时从项目中拿到的图片
        File f = null;
        InputStream inputStream = null;
        try{
            //图片在项目中的地址(项目位置+图片名,带后缀名)
            tempFile = new File(contextPath);
            if(!tempFile.exists()){
                //生成图片
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),tempFile);
            }

            //压缩处理
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (image.getHeight() > 300 || image.getWidth() > 180) {
                if (!"png".equals(suffixName)) {
                    Thumbnails.of(contextPath).size(180, 300).outputQuality(0.7f).outputFormat("jpg").toFile(contextPath);
                } else {
                    Thumbnails.of(contextPath).size(180, 300).outputQuality(0.7f).toFile(contextPath);
                }
            } else {
                if (!"png".equals(suffixName)) {
                    Thumbnails.of(contextPath).outputQuality(0.7f).scale(1f).outputFormat("jpg").toFile(contextPath);
                } else {
                    Thumbnails.of(contextPath).outputQuality(0.7f).scale(1f).toFile(contextPath);
                }
            }

            //获取压缩后的图片
            f = new File(contextPath);
            inputStream = new FileInputStream(f);

            return inputStream;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return inputStream;
    }


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }
}
