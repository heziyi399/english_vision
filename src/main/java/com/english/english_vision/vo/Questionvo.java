package com.english.english_vision.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author
 * @Description
 * @Date
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "题目")
public class Questionvo {
    @ApiModelProperty(name = "id", value = "题目id", dataType = "Integer")
   private Integer id;
    @ApiModelProperty(name = "content", value = "题目内容", dataType = "String")
    private String content;
    @ApiModelProperty(name = "questionLevel", value = "题目等级", dataType = "Integer")
    private Integer questionLevel;

    /**
     * 正确答案
     */
    @ApiModelProperty(name = "score", value = "题目分数", dataType = "Integer")
    private Integer score;
    @ApiModelProperty(name = "correct", value = "题目正确答案", dataType = "String")
    private String correct;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;

}
