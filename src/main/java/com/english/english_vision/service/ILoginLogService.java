package com.english.english_vision.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.english.english_vision.pojo.LoginLog;

import java.util.List;

/**
 * <p>
 * 系统登录日志 服务类
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
public interface ILoginLogService extends IService<LoginLog> {
    public int insert(LoginLog record);
    public int insertByFilter(LoginLog record);
   List<LoginLog> selectAll();
}
