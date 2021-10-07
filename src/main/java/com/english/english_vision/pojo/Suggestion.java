package com.english.english_vision.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Suggestion {
    private Integer id;

    private Integer userId;
    @ApiModelProperty(value="用户反馈的内容")
    private String content;
    @ApiModelProperty(value="是否已经被处理，int类型",name="solveBoolean",example="1是0否")
    private Integer solveBoolean;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSolveBoolean() {
        return solveBoolean;
    }

    public void setSolveBoolean(Integer solveBoolean) {
        this.solveBoolean = solveBoolean;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}