package com.english.english_vision.util;

import com.english.english_vision.config.Myconfig;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;

import org.springframework.web.multipart.MultipartFile;
//import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author
 * @Description
 * @Date
 **/
public class FileUploadUtils {
    /**
     * 默认上传的地址
     */

    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;
    private static String defaultBaseDir = Myconfig.getProfile();
    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    public static String getDefaultBaseDir()
    {
        return defaultBaseDir;
    }
    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException
    {
        try
        {
            return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }
    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException
    {
        try
        {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }
    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException 如果超出最大大小

     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws IOException
    {
   //     int fileNamelength = file.getOriginalFilename().length(); 校验文件名长度


       // assertAllowed(file, allowedExtension); 校验文件大小
        String fileName = extractFilename(file);

        File desc = getAbsoluteFile(baseDir, fileName);//获得绝对路径
        file.transferTo(desc);
        String pathFileName = getPathFileName(baseDir, fileName);
        return pathFileName;
    }
    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file)
    {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        fileName = "pic" + "/" + UUID.randomUUID().toString() + "." + extension;
        return fileName;
    }

    private static String getExtension(MultipartFile file) {
        String extension = MimeTypeUtils.getExtension(file.getContentType());
        return extension;
    }

    public static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException
    {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.exists())
        {
            if (!desc.getParentFile().exists())
            {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }

    public static final String getPathFileName(String uploadDir, String fileName) throws IOException
    {
        int dirLastIndex = Myconfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        String pathFileName = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
        return pathFileName;
    }





}
