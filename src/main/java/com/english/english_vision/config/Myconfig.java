package com.english.english_vision.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Description
 * @Date
 **/

@Component
@ConfigurationProperties(prefix = "myenglish")
public class Myconfig {

        /** 项目名称 */
        private static String name;

        /** 版本 */
    private static String profile;

        /** 版权年份 */
        private static String copyrightYear;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Myconfig.name = name;
    }

    public static String getProfile() {
        return profile;
    }

    public static void setProfile(String profile) {
        Myconfig.profile = profile;
    }

    public static String getCopyrightYear() {
        return copyrightYear;
    }

    public static void setCopyrightYear(String copyrightYear) {
        Myconfig.copyrightYear = copyrightYear;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }
}
