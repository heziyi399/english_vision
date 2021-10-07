package com.english.english_vision.controller;

import com.english.english_vision.Base.BaseController;
import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.pojo.AdminMessage;
import com.english.english_vision.service.impl.AdminMessageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author
 * @Description
 * @Date
 **/
@Api(tags = {"管理员发布通知接口"})
@RestController
public class AdminMessageController extends BaseController {
@Autowired
private AdminMessageServiceImpl adminMessageService;
    @ApiOperation(value="管理员发布系统通知")
    @PostMapping("/admin/post")
    public ResponseResult publish(@ApiParam(name="title",value="标题",required=true)@RequestParam("title")String title,
                                  @ApiParam(name="content",value="内容",required=true)@RequestParam("content")String content)
    {
        AdminMessage adminMessage = new AdminMessage();
        return ResponseResult.success();
    }


}
