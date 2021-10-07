package com.english.english_vision.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@TableName("t_logininfor")
public class Logininfor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 访问ID
     */
 //   @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录状态（0成功 1失败）
     */
    private String status;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 访问时间
     */
    private Date loginTime;


}
