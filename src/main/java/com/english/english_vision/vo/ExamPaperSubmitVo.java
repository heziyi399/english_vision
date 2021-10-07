package com.english.english_vision.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author
 * @Description
 * @Date
 **/
@Data

public class ExamPaperSubmitVo {
    private int score;
    private int total;//题目数量
    private int errorTotal;//错题数量
    private Date date;

}
