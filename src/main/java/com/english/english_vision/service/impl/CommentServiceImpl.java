package com.english.english_vision.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.english.english_vision.Base.PageResult;
import com.english.english_vision.mapper.CommentMapper;
import com.english.english_vision.pojo.Comment;
import com.english.english_vision.pojo.ReplyCountDTO;
import com.english.english_vision.pojo.ReplyDTO;
import com.english.english_vision.pojo.User;
import com.english.english_vision.service.BaseService;
import com.english.english_vision.service.ICommentService;
import com.english.english_vision.util.PageUtils;
import com.english.english_vision.util.StringUtils;
import com.english.english_vision.vo.CommentVo;
import com.english.english_vision.vo.ReplyVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author
 * @Description
 * @Date
 **/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper,Comment> implements ICommentService  {
    @Autowired
    private CommentMapper commentMapper;

    //public boolean deleteById(Integer id) {
//        return commentMapper.rem;
//    }

    public int insertComment(Comment record) {
        return 0;
    }

    @Override
    public List<ReplyDTO>  listRepliesByCommentId(Integer commentId) {
        // 转换页码查询评论下的回复
        List<ReplyDTO> replyDTOList = commentMapper.listRepliesByCommentId(PageUtils.getLimitCurrent(), PageUtils.getSize(), commentId);

        return replyDTOList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveComment(CommentVo commentVO) {
        // 判断是否需要审核
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Comment comment = Comment.builder()
                .replyId(user.getId())
                .userId(commentVO.getReplyUserId())//被回复用户id
                .blogId(commentVO.getBlogId())
                .content(commentVO.getContent())
                .parentId(commentVO.getParentId())
.avatar(user.getAvatar())
                .createTime(new Date())
                .build();
        commentMapper.insert(comment);


    }


    public int insertByFilter(Comment record) {
        return 0;
    }






    @Override
    public int updateByCommentId(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }
    public List<Comment> selectAll() {
        return commentMapper.selectAll();
    }

    @Override
    public List<Comment> selectByUser(int id) {
        return commentMapper.selectByUser(id);
    }

    @Override
    public PageResult<Comment> listComments(Integer articleId,Integer pageNum,Integer pageSize) {
        Integer commentCount = commentMapper.selectCount(articleId);
if(commentCount == 0) return new PageResult<Comment>();
        // 分页查询评论集合
        List<Comment> commentDTOList = commentMapper.listComments( pageNum,pageSize,articleId);
        if(StringUtils.isEmpty(commentDTOList))
        return new PageResult<>();
        List<Integer> commentIdList = commentDTOList.stream().map(Comment::getId).collect(Collectors.toList());
        // 根据评论id集合查询回复数据
        List<ReplyVo> replyDTOList = commentMapper.listReplies(commentIdList);

        //===============================================================================
//根据评论id分组回复数据
        Map<Integer, List<ReplyVo>> replyMap = replyDTOList.stream()
                .collect(Collectors.groupingBy(ReplyVo::getParentId));
        // 根据评论id查询回复量
        Map<Integer, Integer> replyCountMap = commentMapper.listReplyCountByCommentId(commentIdList)
                .stream().collect(Collectors.toMap(ReplyCountDTO::getCommentId, ReplyCountDTO::getReplyCount));
        // 封装评论数据
        commentDTOList.forEach(item -> {

            item.setReplyDTOList(replyMap.get(item.getId()));
            item.setReplyCount(replyCountMap.get(item.getId()));
        });
        return new PageResult<>(commentDTOList,commentCount);//参数为List 评论列表和count评论数量
    }
}
