package com.english.english_vision.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.english.english_vision.pojo.Comment;
import com.english.english_vision.pojo.ReplyCountDTO;
import com.english.english_vision.pojo.ReplyDTO;
import com.english.english_vision.vo.ReplyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Arrays;
import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    List<Comment>selectAll();
    List<Comment>selectByUser(int id);
@Select("select count(*) from t_comment where blog_id = #{articleId} and parent_id <=> null ")
    Integer selectCount(Integer articleId);

    List<Comment> listComments(@Param("pageNum") Integer limitCurrent,@Param("pageSize") Integer size,@Param("articleId") Integer articleId);
List<ReplyVo>listReplies(@Param("commentIdList")List<Integer> commentIdList);

    /**
     * 根据评论id查询回复总量
     *
     * @param commentIdList 评论id集合
     * @return 回复数量
     */
    List<ReplyCountDTO> listReplyCountByCommentId(@Param("commentIdList") List<Integer> commentIdList);

    List<ReplyDTO> listRepliesByCommentId(@Param("current")Long limitCurrent, @Param("size") Long size,@Param("commentId") Integer commentId);
}