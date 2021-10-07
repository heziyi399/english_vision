package com.english.english_vision.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author
 * @Description
 * @Date
 **/
@Data
public class DiaryVo {
    private String title;

    private String content;

    private String type;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")//加上这个注解以后在接收到后端数据显示时间时显示的都是自己设置的格式
    private Date createTime;
}
