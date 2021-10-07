package com.english.english_vision.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("t_question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 题目总分(百分制)
     */
    private Integer score;

    /**
     * 该图片是什么单词？A.apple B.pear C.banana
     */
    private String content;

    /**
     * 级别
     */
    private Integer questionLevel;

    /**
     * 正确答案
     */
    private String correct;

    /**
     * 图片地址
     */
    private String url;

    private Date createTime;
   private String word;



}
