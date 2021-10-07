package com.english.english_vision.service;


import com.english.english_vision.pojo.LoginLog;

/**
 * <p>
 * 系统登录日志 服务类
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
public interface ILoginLogService  {
    public int insert(LoginLog record);
    public int insertByFilter(LoginLog record);
}
