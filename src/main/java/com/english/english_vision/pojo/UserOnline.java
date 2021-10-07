package com.english.english_vision.pojo;

import lombok.Data;
import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author
 * @Description 在线用户
 * @Date 9.1
 **/
@Data
public class UserOnline extends SimpleSession implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 用户会话id */
    private String sessionId;


    private String userName;

    /** 用户id */
    private String id;

    /** 登录IP地址 */
   private String ipaddr;

    /** 登录地址 */
    private String loginLocation;

    /** 浏览器类型 */
    private String browser;

    /** 操作系统 */
    private String os;

    /** session创建时间 */
    private Date startTimestamp;

    /** session最后访问时间 */
    private Date lastAccessTime;

    /** 超时时间，单位为分钟 */
    private Long expireTime;
}
