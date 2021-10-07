package com.english.english_vision.controller;


import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.pojo.Blog;
import com.english.english_vision.pojo.Comment;
import com.english.english_vision.pojo.User;
import com.english.english_vision.pojo.Word;
import com.english.english_vision.service.IBlogService;
import com.english.english_vision.service.impl.BlogService2Impl;
import com.english.english_vision.service.impl.CommentServiceImpl;
import com.english.english_vision.vo.BlogVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hehe
 * @since 2021-09-03
 */
@Api(tags = {"帖子操作接口，两类帖子，一类是问答贴，一类是经验总结贴"})
@RestController
@RequestMapping("/blog")
public class BlogController {
@Autowired
IBlogService service2;

    @Autowired
    private CommentServiceImpl commentService;
    @ApiOperation(value="发布帖子")
    @PostMapping("/publish")
    public ResponseResult insertblog(@ApiParam(name="title",value="标题",required=true)@RequestParam("title")String title,
                                     @ApiParam(name="content",value="内容",required=true)@RequestParam("content")String content)
{
    User user = (User) SecurityUtils.getSubject().getPrincipal();

Blog blog = new Blog();
blog.setUserId(user.getId());
blog.setContent(content);
blog.setTitle(title);
blog.setCreateTime(new Date());

return ResponseResult.success();
    }
    @ApiOperation(value="查看系统的全部发帖列表，不分类型")
    @PostMapping("/list/all")
    public ResponseResult<PageInfo>bloglists(
            @ApiParam(name="pageNum",value="开头页",required=true)@RequestParam("pageNum")int pageNum,
            @ApiParam(name="pageSize",value="页的大小",required=true)@RequestParam("content")int pageSize,
            @ApiParam(name="order",value="boolean类型，为true代表是，为false代表否",required=true)@RequestParam("order")boolean order
    )
    {
        List<Blog> blogs = new LinkedList<>();
        if(order == false){ blogs  =  service2.selectall();}
        else   blogs = service2.selectall();
        List<BlogVo>blogVos = blogs.stream().map(e->{
            BlogVo blogVo = new BlogVo();
            BeanUtils.copyProperties(e,blogVo);
            return blogVo;
        }).collect(Collectors.toList());

    PageInfo pageInfo = new PageInfo(blogs);
    pageInfo.setList(blogVos);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);

    return ResponseResult.success(pageInfo);
    }
    @ApiOperation(value="查看其他用户的总结帖,type：summary和query,，可选择是否按时间排序")
    @PostMapping("/list/summary")
    public ResponseResult<PageInfo>bloglists1(
            @ApiParam(name="type",value="帖子类型",required=true)@RequestParam("type")String type,
            @ApiParam(name="pageNum",value="开头页",required=true)@RequestParam("pageNum")int pageNum,
            @ApiParam(name="pageSize",value="页的大小",required=true)@RequestParam("content")int pageSize,
            @ApiParam(name="order",value="boolean类型，为true代表是，为false代表否",required=true)@RequestParam("order")boolean order
    ){
        List<Blog> blog = new LinkedList<>();
        if(order == false){ blog  =  service2.selectByQuestion(type);}
        else   blog = service2.selectByQuestion(type);
        if(blog == null) return ResponseResult.error("暂无该类型");
        List<BlogVo> blogVos =blog.stream().map(e->{
            BlogVo blogVo = new BlogVo();
            BeanUtils.copyProperties(e,blogVo);
            return blogVo;
        }).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(blog);
        pageInfo.setList(blogVos);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        return ResponseResult.success(pageInfo);

    }
    @ApiOperation(value="查看其他用户的问答帖，可选择是否按时间排序")
    @PostMapping("/list/question")
    public ResponseResult<PageInfo>bloglists2(  @ApiParam(name="type",value="帖子类型,query/summary",required=true)@RequestParam("type")String type,
                                               @ApiParam(name="pageNum",value="开头页",required=true)@RequestParam("pageNum")int pageNum,
                                               @ApiParam(name="pageSize",value="页的大小",required=true)@RequestParam("content")int pageSize,
     @ApiParam(name="order",value="boolean类型，为true代表是，为false代表否",required=true)@RequestParam("order")boolean order)
    {


        List<Blog> blog = new LinkedList<>();
        if(order == false){ blog  =  service2.selectByQuestion(type);}
        else   blog = service2.selectByQuestion(type);
        List<BlogVo> blogVos =blog.stream().map(e->{
            BlogVo blogVo = new BlogVo();
            BeanUtils.copyProperties(e,blogVo);
            return blogVo;
        }).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(blog);
        pageInfo.setList(blogVos);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        return ResponseResult.success(pageInfo);
    }

    @ApiOperation(value="给帖子点赞")
    @PostMapping("/appreation")
    public ResponseResult addAppreciation(  @ApiParam(name="id",value="帖子id",required=true)@RequestParam("id")int id){
        Blog blog = service2.selectById(id);
      int num = blog.getAppreciation()+1;
        blog.setAppreciation(num);
        service2.saveOrUpdate(blog);
        return ResponseResult.success();
    }
    @ApiOperation(value="给帖子评论")
    @PostMapping("/addcomment")
    public ResponseResult addComment(  @ApiParam(name="id",value="帖子id",required=true)@RequestParam("id")int id,
                                       @ApiParam(name="content",value="评论内容",required=true)@RequestParam("content")String content){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
Comment comment = new Comment();
comment.setContent(content);
comment.setBlogId(id);
comment.setUserId(user.getId());
comment.setAvatar(user.getAvatar());
       boolean num = commentService.save(comment);
       if(num)
        return ResponseResult.success(comment);
       else return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
    }
    /*已测试*/
    @ApiOperation(value="查看系统推荐的帖子列表")
    @PostMapping("/recommend")
    public ResponseResult<PageInfo>getBlogRecommend(  @ApiParam(name="pageNum",value="当前页",required=true)@RequestParam("pageNum")Integer pageNum,
                                                      @ApiParam(name="pageSize",value="每页的记录条数",required=true) @RequestParam("pageSize")Integer pageSize)
            throws ExecutionException, InterruptedException {
        List<BlogVo> blogRecommend = service2.getBlogRecommend();
        if(blogRecommend!=null) {

            PageInfo pageInfo = new PageInfo(blogRecommend);
            pageInfo.setPageNum(pageNum);
            pageInfo.setPageSize(pageSize);
            pageInfo.setList(blogRecommend);
            return ResponseResult.success(pageInfo);

        }
        else return ResponseResult.error(ResponseEnum.ERROR);
    }
    @ApiOperation(value="点击查看某个帖子的详情")
    //已经测试v
    @PostMapping("/detail")
    public ResponseResult blogDetail( @ApiParam(name="id",value="帖子id",required=true)@RequestParam("id")Integer id)
    {
        Blog blog = service2.selectById(id);
        BlogVo blogVo = new BlogVo();
        blog.setViews(blog.getViews()==0?1:blog.getViews()+1);
        service2.updateById(blog);
        BeanUtils.copyProperties(blog,blogVo);
        if(blogVo!=null) return ResponseResult.success(blogVo);
        else  return ResponseResult.error(ResponseEnum.OPERATE_ERROR);
    }

    @ApiOperation(value="查看自己的发帖列表")
    //已经测试v
    @PostMapping("/mylist")
    public ResponseResult<PageInfo> myBlog( @ApiParam(name="pageNum",value="当前页",required=true)@RequestParam("pageNum")Integer pageNum,
                                            @ApiParam(name="pageSize",value="每页的记录条数",required=true) @RequestParam("pageSize")Integer pageSize)
    {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<BlogVo> blogVos = service2.selectByUserID(user.getId()).stream().map(  e->{
            BlogVo blogVo = new BlogVo();
            BeanUtils.copyProperties(e,blogVo);
            return blogVo;
        }).collect(Collectors.toList());
         if(blogVos!=null) {
             PageInfo pageInfo = new PageInfo(blogVos);
             pageInfo.setPageSize(pageSize);
             pageInfo.setPageNum(pageNum);
             pageInfo.setList(blogVos);
             return ResponseResult.success(pageInfo);

         }
         else return ResponseResult.error(ResponseEnum.BLOG_NOT_EXIST);
    }

    @ApiOperation(value="按浏览量从高到低查看帖子列表")
    //已经测试v
    @GetMapping("/selectByView")
    public ResponseResult<PageInfo> selectByView(@ApiParam(name="pageNum",value="当前页",required=true)@RequestParam("pageNum")Integer pageNum,
                                                 @ApiParam(name="pageSize",value="每页的记录条数",required=true)Integer pageSize)
    {

  List<BlogVo> blogVos = service2.selectByView().stream().map( e->{
      BlogVo blogVo = new BlogVo();
      BeanUtils.copyProperties(e,blogVo);
      return blogVo;
  }).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(blogVos);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        pageInfo.setList(blogVos);
  return ResponseResult.success(pageInfo);
    }

}
