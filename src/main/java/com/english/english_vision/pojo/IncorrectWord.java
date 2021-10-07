package com.english.english_vision.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author hehe
 * @since 2021-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_incorrect_word")
@NoArgsConstructor
@AllArgsConstructor

public class IncorrectWord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 错过的单词表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer wordId;

    private Integer userId;

    /**
     * 错过的总次数
     */
    private int totalTimes;

    private int wordLevel;
private String word;

}
