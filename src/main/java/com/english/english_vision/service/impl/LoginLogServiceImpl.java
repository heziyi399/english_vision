package com.english.english_vision.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.english.english_vision.mapper.LoginLogMapper;
import com.english.english_vision.pojo.LoginLog;
import com.english.english_vision.service.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统登录日志 服务实现类
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper,LoginLog> implements ILoginLogService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int insert(LoginLog record) {
        return loginLogMapper.insertSelective(record);
    }

    @Override
    public int insertByFilter(LoginLog record) {
        return 0;
    }

    @Override
    public List<LoginLog> selectAll() {
        return loginLogMapper.selectAll();
    }


    public LoginLog selectById(Integer id) {
        return null;
    }


    public int updateByIdFilter(LoginLog record) {
        return 0;
    }



}
