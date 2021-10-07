package com.english.english_vision.controller;

import com.english.english_vision.Base.PageResult;
import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.pojo.Blog;
import com.english.english_vision.pojo.Comment;
import com.english.english_vision.pojo.ReplyDTO;
import com.english.english_vision.pojo.User;
import com.english.english_vision.service.IBlogService;
import com.english.english_vision.service.ICommentService;
import com.english.english_vision.service.impl.CommentServiceImpl;
import com.english.english_vision.vo.CommentVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author
 * @Description
 * @Date
 **/
@RestController
@RequestMapping("/comment")
@Api(tags = {"帖子评论操作接口，用于系统管理员和发帖人"})
public class CommentController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private IBlogService blogService;
    @ApiOperation(value="某一篇帖子下面的评论列表")
    @PostMapping("/list")
    public ResponseResult<PageResult<Comment>> selectAll( @ApiParam(name="pageNum",value="当前页",required=true)
                                                              @RequestParam("pageNum")Integer pageNum,
                                                          @ApiParam(name="pageSize",value="每页的记录条数",required=true)
                                                          @RequestParam("pageSize")Integer pageSize,
                                                          @ApiParam(name="blogId",value="帖子id",required=true)
                                        @RequestParam("blogId") int blogId)
    {
        Blog blog = blogService.selectById(blogId);
return ResponseResult.success(commentService.listComments(blogId,pageNum,pageSize));

    }
    @ApiOperation(value="删除评论")
    @DeleteMapping("/delete")
    public ResponseResult deleteBlog(@ApiParam(name="id",value="帖子id",required=true)
                                         @RequestParam("id") int id){
        commentService.removeById(id);
        return ResponseResult.success();
    }
    @ApiOperation(value="用户查看自己收到过的全部评论")
    @PostMapping("/mylist")
    public ResponseResult<PageInfo> allComment(@ApiParam(name="pagenum",value="当前页",required=true)@RequestParam("pageNum")Integer pageNum,
                                               @ApiParam(name="pagesize",value="每页的记录条数",required=true) @RequestParam("pageSize")Integer pageSize){

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Comment> commentList = commentService.selectByUser(user.getId());
        PageInfo pageInfo = new PageInfo(commentList);
        pageInfo.setList(commentList);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        return ResponseResult.success(pageInfo);
    }

    @ApiOperation(value = "查询评论下的回复")
    @ApiImplicitParam(name = "commentId", value = "评论id", required = true, dataType = "Integer")
    @GetMapping("/{commentId}/replies")
    public ResponseResult<List<ReplyDTO>> listRepliesByCommentId(@PathVariable("commentId") Integer commentId) {
        return ResponseResult.success(commentService.listRepliesByCommentId(commentId));
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("/add")
    public ResponseResult saveComment(@RequestBody CommentVo commentVO) {
        commentService.saveComment(commentVO);
        return ResponseResult.success(commentVO);
    }

}
