package com.english.english_vision.service.impl;

import com.english.english_vision.mapper.AdminMessageMapper;
import com.english.english_vision.service.IAdminMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author
 * @Description
 * @Date
 **/
@Service
public class AdminMessageServiceImpl implements IAdminMessageService {
    @Autowired
    private AdminMessageMapper adminMessageMapper;
    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int insert(Object record) {
        return 0;
    }

    @Override
    public int insertByFilter(Object record) {
        return 0;
    }

    @Override
    public Object selectById(Integer id) {
        return null;
    }

    @Override
    public int updateByIdFilter(Object record) {
        return 0;
    }

    @Override
    public int updateById(Object record) {
        return 0;
    }
}
