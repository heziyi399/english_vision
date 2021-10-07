package com.english.english_vision.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.InputStreamReader;
import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author hehe
 * @since 2021-09-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("t_blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户博客表
     */
    @TableId(type = IdType.AUTO)
    private int id;

    /**
     * 被收藏的数量
     */
    private Integer appreciation;


    @JsonInclude(JsonInclude.Include.NON_NULL)//表示如果此属性为空则不参与序列化
    private String content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean published;

    /**
     * 是否推荐至首页
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean recommend;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean shareStatement;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date updateTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer views;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
private String type;
private String firstPicture;
    public Blog(int i, String tr, String etre) {
        this.id = i;
        this.title = tr;
        this.content = etre;
    }
}
