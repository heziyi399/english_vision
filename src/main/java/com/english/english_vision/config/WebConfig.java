package com.english.english_vision.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author
 * @Description 跨域
 * @Date
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //全局配置类，配置跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //预访问的路径 请求来源 方法 允许携带的
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowCredentials(true);
    }
    @Override
public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
   registry.addResourceHandler("/img/**")
    .addResourceLocations("file:C:\\Users\\14172\\Pictures\\");//file不能丢，否则访问不到
        registry.addResourceHandler("/song/**").addResourceLocations("file:C:\\Users\\14172\\Desktop\\开发\\share\\data\\data\\song\\");
    }

}