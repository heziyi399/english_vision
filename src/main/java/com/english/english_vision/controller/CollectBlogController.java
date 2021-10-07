package com.english.english_vision.controller;


import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.pojo.Blog;
import com.english.english_vision.pojo.CollectBlog;
import com.english.english_vision.pojo.User;
import com.english.english_vision.service.impl.BlogService2Impl;
import com.english.english_vision.service.impl.CollectBlogServiceImpl;
import com.english.english_vision.vo.BlogVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
@Controller
@RequestMapping("/collectBlog")
@Api(tags = {"帖子收藏操作接口"})
public class CollectBlogController {
    @Autowired
    private BlogService2Impl service;
    @Autowired
    private CollectBlogServiceImpl collectBlogService;
    @ApiOperation(value="查看收藏的帖子列表")
    @PostMapping("/all")
    public ResponseResult<PageInfo> Bloglist(@ApiParam(name="pagenum",value="当前页",required=true)@RequestParam("pageNum")Integer pageNum,
                                             @ApiParam(name="pagesize",value="每页的记录条数",required=true) @RequestParam("pageSize")Integer pageSize){

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Blog> blogList = service.selectByUserId(user.getId());
        List<BlogVo> blogVos = blogList.stream().map(e->{
            BlogVo blogVo = new BlogVo();
            BeanUtils.copyProperties(e,blogVo);
            return blogVo;
        }).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(blogList);
        pageInfo.setList(blogVos);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        return ResponseResult.success(pageInfo);


    }
    @ApiOperation(value="收藏某个帖子")
    @PostMapping("/insert")
   public ResponseResult addBlog(@ApiParam(name="id",value="帖子id",required=true)
    @RequestParam("id") int id){
       User user = (User) SecurityUtils.getSubject().getPrincipal();
        CollectBlog collectBlog = new CollectBlog();
        collectBlog.setBlogId(id);
        collectBlog.setUserId(user.getId());
        collectBlog.setBlogType(service.selectById(id).getType());
        collectBlog.setCreateTime(new Date());
       collectBlogService.addBlog(collectBlog);
       return ResponseResult.success();

    }
    @ApiOperation(value="删除某个收藏的帖子")
    @DeleteMapping("/delete")
    public ResponseResult deleteBlog(@ApiParam(name="id",value="帖子id",required=true)    @RequestParam("id") int id)
    {
        CollectBlog collectBlog = collectBlogService.getById(id);
      boolean flag=  collectBlogService.removeById(id);
      if(flag == true) return ResponseResult.success();
      else return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
    }
}
