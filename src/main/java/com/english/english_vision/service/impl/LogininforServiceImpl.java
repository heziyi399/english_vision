package com.english.english_vision.service.impl;


import com.english.english_vision.mapper.LogininforMapper;
import com.english.english_vision.pojo.Logininfor;
import com.english.english_vision.service.ILogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@Service
public class LogininforServiceImpl implements ILogininforService {
@Autowired
LogininforMapper mapper;

    public int deleteById(Integer id) {
        return 0;
    }


    public int insert(Logininfor record) {
        return mapper.insert(record);
    }


    public int insertByFilter(Object record) {
        return 0;
    }


    public Object selectById(Integer id) {
        return null;
    }


    public int updateByIdFilter(Object record) {
        return 0;
    }


    public int updateById(Object record) {
        return 0;
    }
}
