package com.english.english_vision.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Description
 * @Date
 **/
@Data
@NoArgsConstructor
public class BlogVo implements Serializable {
    private Integer appreciation;
private String title;


    private String content;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date createTime;
private int views;
    private String description;

    private String firstPicture;
    private String type;


}
