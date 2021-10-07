package com.english.english_vision.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_fill_question")
public class FillQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 苹果的英文单词是？
     */
    private String content;

    private Integer score;

    private String questionLevel;

    private String correct;

    private Date createTime;


}
