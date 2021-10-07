package com.english.english_vision.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.english.english_vision.vo.ReplyVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "评论")
@Data
@TableName("t_comment")
public class Comment {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "userId", value = "被回复用户ID", dataType = "Integer")
    private Integer userId;
    @ApiModelProperty(name = "avatar", value = "回复用户头像", dataType = "String")
    private String avatar;
    @ApiModelProperty(name = "content", value = "评论内容", dataType = "String")
    private String content;

    private Date createTime;
private int parentId;
    @ApiModelProperty(name = "fromUser", value = "发表评论的用户昵称", dataType = "String")
private String fromUser;
    @ApiModelProperty(name = "replyId", value = "回复用户id", dataType = "Integer")
private int replyId;
    @ApiModelProperty(name = "replyUserId", value = "帖子id", dataType = "Integer")
    private int blogId;
    /**
     * 回复量
     */
    private Integer replyCount;
    /**
     * 回复列表
     */
    private List<ReplyVo> replyDTOList;
}