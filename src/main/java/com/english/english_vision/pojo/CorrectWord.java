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
@TableName("t_correct_word")
public class CorrectWord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 错题表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer wordId;

    private Integer userId;

    /**
     * 错过的总次数
     */
    private Date totalTimes;


}
