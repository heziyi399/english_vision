package com.english.english_vision.controller;

import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.mapper.SuggestionMapper;
import com.english.english_vision.pojo.Suggestion;
import com.english.english_vision.pojo.User;
import com.english.english_vision.service.impl.SuggestionServiceImpl;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author
 * @Description
 * @Date
 **/
@RestController
@RequestMapping("/suggest")
@Api(tags = {"用户反馈操作接口"})
public class SuggestionController {
    @Autowired
    private SuggestionServiceImpl service;
    @ApiOperation(value="管理员查看用户反馈的列表")
    @PostMapping("/list")
    public ResponseResult<PageInfo> getList(
            @ApiParam(name="pageNum",value="开头页",required=true)@RequestParam("pageNum")int pageNum,
            @ApiParam(name="pageSize",value="页的大小",required=true)@RequestParam("content")int pageSize
    ){
        List<Suggestion> suggestions = service.selectAll();
        PageInfo pageInfo = new PageInfo(suggestions);
        pageInfo.setList(suggestions);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        return ResponseResult.success(pageInfo);
    }
    @ApiOperation(value="用户提交反馈")
    @PostMapping("/add")
    public ResponseResult addSug(
            @ApiParam(name="content",value="反馈的内容",required=true)
            @RequestParam("content")String content){
User user = (User) SecurityUtils.getSubject().getPrincipal();
Suggestion suggestion = new Suggestion();
suggestion.setContent(content);
suggestion.setUserId(user.getId());
suggestion.setSolveBoolean(0);
suggestion.setCreateTime(new Date());
int num = service.insertByFilter((Suggestion) suggestion);
if(num > 0) return ResponseResult.success();
else return ResponseResult.error(ResponseEnum.OPERATE_ERROR);

    }

}
