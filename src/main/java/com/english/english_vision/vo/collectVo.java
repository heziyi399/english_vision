package com.english.english_vision.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author
 * @Description 收藏单词
 * @Date 9.1
 **/
@Data
public class collectVo {

    @ApiModelProperty(value ="单词难度系数")
    private Integer wordLevel;

    private String wordEnglish;
    @ApiModelProperty(value ="单词中文")
    private String wordChinese;
    @ApiModelProperty(value ="单词用法",dataType = "String",example = "i like eating apples")
    private String wordUse;

    @ApiModelProperty(value="是否掌握，1代表是，0代表否",name="masterBoolean",example="1,0")
    private Integer masterBoolean;

}
