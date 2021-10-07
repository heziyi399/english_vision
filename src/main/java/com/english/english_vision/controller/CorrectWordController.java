package com.english.english_vision.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
@Api(tags = {"用户错过的单词操作接口"})
@RestController
@RequestMapping("/correctword")
public class CorrectWordController {
    @ApiOperation(value="查看")
    @PostMapping("/list")
public void wordlist(){

    }

}
