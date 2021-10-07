package com.english.english_vision;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.english.english_vision.mapper")
public class EnglishVisionApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnglishVisionApplication.class, args);
    }

}
