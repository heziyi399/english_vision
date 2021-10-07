package com.english.english_vision.pojo;

import io.swagger.annotations.ApiModelProperty;

public class CollectWord {
    private Integer wordId;
    @ApiModelProperty(value ="单词难度系数")
    private Integer wordLevel;

    private String wordEnglish;
    @ApiModelProperty(value ="单词中文")
    private String wordChinese;
    @ApiModelProperty(value ="单词用法",dataType = "String",example = "i like eating apples")
    private String wordUse;
    @ApiModelProperty(value = "学生备注",dataType = "String",example = "没见过的单词")
    private String stuInfo;
    @ApiModelProperty(value="是否掌握，1代表是，0代表否,默认为0",name="masterBoolean",example="1,0")
    private Integer masterBoolean = 0;
    private Integer id;
    @ApiModelProperty(value = "用户id",dataType = "int")
    private Integer stuId;

//    public Integer getBlogId() {
//        return blogId;
//    }
//
//    public void setBlogId(Integer blogId) {
//        this.blogId = blogId;
//    }

//    @ApiModelProperty(value = "帖子id",dataType = "int")
//    private Integer blogId;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }
    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public Integer getWordLevel() {
        return wordLevel;
    }

    public void setWordLevel(Integer wordLevel) {
        this.wordLevel = wordLevel;
    }

    public String getWordEnglish() {
        return wordEnglish;
    }

    public void setWordEnglish(String wordEnglish) {
        this.wordEnglish = wordEnglish;
    }

    public String getWordChinese() {
        return wordChinese;
    }

    public void setWordChinese(String wordChinese) {
        this.wordChinese = wordChinese;
    }

    public String getWordUse() {
        return wordUse;
    }

    public void setWordUse(String wordUse) {
        this.wordUse = wordUse;
    }

    public String getStuInfo() {
        return stuInfo;
    }

    public void setStuInfo(String stuInfo) {
        this.stuInfo = stuInfo;
    }

    public Integer getMasterBoolean() {
        return masterBoolean;
    }

    public void setMasterBoolean(Integer masterBoolean) {
        this.masterBoolean = masterBoolean;
    }
}