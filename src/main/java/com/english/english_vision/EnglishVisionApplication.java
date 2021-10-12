package com.english.english_vision;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableCaching//加上这个注解以后在业务层使用@Cacheable可以开启springboot的默认缓存，如果 不加这个注解则@Cacheable不会起作用
@SpringBootApplication
@MapperScan("com.english.english_vision.mapper")
public class EnglishVisionApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnglishVisionApplication.class, args);
    }

}
