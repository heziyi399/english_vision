package com.english.english_vision.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

/**
 * @Author
 * @Description回复列表
 * @Date
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVo {

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
    private String userName;//被回复用户昵称
    /**
     * 发表回复用户昵称
     */
    private String fromUser;

    private String avatar;
    //发表回复的用户id
    private Integer replyId;
    private String content;

    private Date createTime;

}
