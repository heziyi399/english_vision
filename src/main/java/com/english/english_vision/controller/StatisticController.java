package com.english.english_vision.controller;

import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.pojo.Blog;
import com.english.english_vision.service.IBlogService;
import com.english.english_vision.service.IUserService;
import com.english.english_vision.service.IWordService;
import com.english.english_vision.vo.BlogUser;
import com.english.english_vision.vo.BlogVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author
 * @Description 用户数据统一交互模块
 * @Date
 **/
@Api(tags = {"用户数据模块"})
@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    IUserService userService;
    @Autowired
    IWordService wordService;
    @Autowired
    IBlogService blogService;
    @ApiOperation(value="用户所发经验帖量排行榜,已测试")

    @GetMapping("selectByArticle")
    public ResponseResult<PageInfo> selectByArticle( @ApiParam(name="pagenum",value="当前页",required=true)@RequestParam("pageNum")Integer pageNum,
                                                     @ApiParam(name="pagesize",value="每页的记录条数",required=true) @RequestParam("pageSize")Integer pageSize)
    {
        List<BlogUser>blogUsers = blogService.selectByArticleNums();
        blogUsers.stream().map(e->{
            List<BlogVo> blogs = blogService.selectByUserID(e.getUserId());
            e.setBlogs(blogs);
            return e;
        }).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(blogUsers);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setList(blogUsers);
        return ResponseResult.success(pageInfo);
    }
}
