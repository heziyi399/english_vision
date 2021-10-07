package com.english.english_vision.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Description 用户个人数据展示
 * @Date
 **/
@ApiModel(value="user对象",description="用户对象user")

@Data
@EqualsAndHashCode(callSuper = false)
//@TableName("t_user")

public class UserConditionVo implements Serializable{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="头像",name="avatar",example="http://192.168.43.124:5000/my.png")
    private String avatar;
    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名",name="username",example="xingguo")
    private String userName;
    @ApiModelProperty(value="密码",name="password",example="123456")
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    private Integer age;
    @ApiModelProperty(value="生日",name="birthDay",example="2004-04-08")
  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date birthDay;

    /**
     * 学生年级(1-12)
     */
    private Integer userLevel;

    private String phone;




}
