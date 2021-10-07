package com.english.english_vision.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @Author
 * @Description 用户答卷
 * @Date
 **/
@Data
@NoArgsConstructor
public class UserAnserVo {
    private int paperId;

    private HashMap<Integer,String> userAnswer;

}
