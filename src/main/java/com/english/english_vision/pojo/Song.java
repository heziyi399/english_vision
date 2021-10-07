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
 * @since 2021-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_song")
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer singerId;

    private String name;

    private String introduction;

    /**
     * 发行时间
     */
    private Date createTime;

    private Date updateTime;

    private String pic;

    private String lyric;

    private String url;


}
