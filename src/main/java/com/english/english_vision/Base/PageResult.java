package com.english.english_vision.Base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author
 * @Description 分页对象
 * @Date 10.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "分页对象")
public class PageResult <T>{
    @ApiModelProperty(name = "recordList", value = "分页列表", required = true, dataType = "List<T>")
    private List<T> recordList;
    @ApiModelProperty(name = "count", value = "总数", required = true, dataType = "Integer")
    private Integer count;


}
