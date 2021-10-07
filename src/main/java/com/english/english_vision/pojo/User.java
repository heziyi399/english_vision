package com.english.english_vision.pojo;

import com.english.english_vision.util.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@ApiModel(value="user对象",description="用户对象user")

@Data
@EqualsAndHashCode(callSuper = false)
//@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

   // @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value="头像",name="url地址",example="http://192.168.43.124:5000/my.png")
private String avatar;
    private String userUuid;

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
 @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date birthDay;

    /**
     * 学生年级(1-12)
     */
    private Integer userLevel;

    private String phone;

    /**
     * 1.学生 2.儿童 3.管理员
     */
    private Integer role=1;

    /**
     * 1.启用 2禁用
     */
    private Integer status=1;


    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date modifyTime;

    /**
     * 是否删除
     */
    private Boolean deleted=false;

    /**
     * 学的总单词数
     */
    private Integer totalWords=0;



//    public void setBirthDay(Date birth) {
//        this.birthDay = DateTimeUtil.dateTime(birth.toString(),DateTimeUtil.STANDER_SHORT_FORMAT);
//    }

//    public void setCreateTime(Date createTime) {
//        this.createTime =  DateTimeUtil.dateTime(createTime.toString(),DateTimeUtil.STANDER_SHORT_FORMAT);
//    }
}
