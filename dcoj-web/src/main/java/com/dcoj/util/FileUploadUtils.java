package com.dcoj.util;

import com.dcoj.config.DcojConfig;
import com.dcoj.judge.LanguageEnum;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件上传工具类
 *
 * @author WANGQING
 */
public class FileUploadUtils {
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = DcojConfig.getProfile();

    /**
     * 默认文件类型jpg
     */
    public static final String IMAGE_JPG_EXTENSION = ".jpg";


    private static int counter = 0;

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException {
        try {
            return upload(getDefaultBaseDir(), file, FileUploadUtils.IMAGE_JPG_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传头像
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的头像
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadAvatar(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, FileUploadUtils.IMAGE_JPG_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传头像
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的头像
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadText(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, ".txt");
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传代码
     *
     * @param baseDir 相对应用的基目录
     * @param code    上传的代码
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadCode(String baseDir, String code, LanguageEnum lang) throws IOException{
        String suffix = "";
        File file = null;
        FileInputStream inputStream = null;
        MultipartFile multipartFile = null;
        switch (lang) {
            case JAVA8:
                suffix += ".java";
                break;
            case C:
                suffix += ".c";
                break;
            case CPP:
                suffix += ".cpp";
                break;
            default:
                suffix += ".py";
                break;
        }
        try {
            // 创建临时文件，使用6随机数作为文件名
            file = File.createTempFile(RandomValidateCodeUtil.getRandomString(), suffix);
            FileUtils.writeStringToFile(file, code, "utf-8");
            inputStream = new FileInputStream(file);
            // 将file转换为MultipartFile
            multipartFile = new MockMultipartFile(file.getName(), inputStream);
            // 在程序退出时删除临时文件
            file.deleteOnExit();
        } catch (Exception e) {
//            throw new IOException(e.getMessage(), e);
            WebUtil.assertIsSuccess(false, "上传代码失败");
        }
        return upload(baseDir, multipartFile, suffix);
    }

    /**
     * 文件上传
     *
     * @param baseDir   相对应用的基目录
     * @param file      上传的文件
     * @param extension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws IOException 比如读写文件出错时
     */
    public static final String upload(String baseDir, MultipartFile file, String extension) throws IOException {

        int fileNamelength = file.getOriginalFilename().length();
        // 判断文件名长度是否超出限定长度
        WebUtil.assertIsSuccess(fileNamelength <= FileUploadUtils.DEFAULT_FILE_NAME_LENGTH,
                "文件名长度超出限定长度");

        assertAllowed(file);

        String fileName = extractFilename(file, extension);

        File desc = getAbsoluteFile(baseDir, baseDir + fileName);
        file.transferTo(desc);
        return fileName;
    }

    public static final String extractFilename(MultipartFile file, String extension) {
        String filename = file.getOriginalFilename();
        filename = DateUtils.datePath() + "/" + encodingFilename(filename) + extension;
        return filename;
    }

    private static final File getAbsoluteFile(String uploadDir, String filename) throws IOException {
        File desc = new File(File.separator + filename);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 编码文件名
     */
    private static final String encodingFilename(String filename) {
        filename = filename.replace("_", " ");
        filename = Md5Utils.hash(filename + System.nanoTime() + counter++);
        return filename;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     */
    public static final void assertAllowed(MultipartFile file) {//throws FileSizeLimitExceededException {
        long size = file.getSize();
        WebUtil.assertIsSuccess((size <= DEFAULT_MAX_SIZE),
                "文件大小超出最大限定大小");
    }

}
