package com.english.english_vision.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.english.english_vision.Base.PageResult;
import com.english.english_vision.pojo.Comment;
import com.english.english_vision.pojo.ReplyDTO;
import com.english.english_vision.vo.CommentVo;

import java.util.List;

public interface ICommentService extends IService<Comment> {
    List<Comment> selectByUser(int id);
    int updateByCommentId(Comment record);
    PageResult<Comment> listComments(Integer articleId,Integer pageNum,Integer pageSize);
    int insertComment(Comment record);

    List<ReplyDTO>  listRepliesByCommentId(Integer commentId);

    void saveComment(CommentVo commentVO);
}
