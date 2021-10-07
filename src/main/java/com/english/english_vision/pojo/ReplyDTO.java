package com.english.english_vision.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
public class ReplyDTO {
    /**
     * 评论id
     */
    private Integer id;

    /**
     * 父评论id
     */
    private Integer parentId;

    /**
     * 被回复用户id
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String avatar;



    /**
     * 回复用户id
     */
    private Integer replyId;

    /**
     * 被回复用户昵称
     */
    private String replyUsername;


    /**
     * 评论内容
     */
    private String content;



    /**
     * 评论时间
     */
    private Date createTime;
}
