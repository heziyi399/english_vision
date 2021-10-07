package com.english.english_vision.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author
 * @Description
 * @Date
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
//
//    @ApiModelProperty(name = "avatar", value = "回复用户头像", dataType = "String")
//    private String avatar;
   // @NotBlank(message = "评论内容不能为空")
    private String content;
    @ApiModelProperty(name = "replyUserId", value = "被回复用户id", dataType = "Integer")
    private Integer replyUserId;
    private Integer parentId;
    @ApiModelProperty(name = "replyUserId", value = "帖子id", dataType = "Integer")
    private Integer blogId;
    private Date createTime;
}
