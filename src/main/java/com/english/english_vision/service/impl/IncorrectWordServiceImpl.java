package com.english.english_vision.service.impl;

import com.english.english_vision.pojo.IncorrectWord;
import com.english.english_vision.mapper.IncorrectWordMapper;
import com.english.english_vision.service.BaseService;
import com.english.english_vision.service.IIncorrectWordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hehe
 * @since 2021-09-05
 */
@Service
public class IncorrectWordServiceImpl extends ServiceImpl<IncorrectWordMapper, IncorrectWord> implements IIncorrectWordService {
@Autowired
private IncorrectWordMapper mapper;

    public int deleteById(Integer id) {
        return 0;
    }


    public int insert(IncorrectWord record) {
        return mapper.insert(record);
    }

   public int selectCount(String word){
        return mapper.selectCount(word);
    }
    public int insertByFilter(Object record) {
        return 0;
    }


    public List<IncorrectWord> selectByUserId(Integer id) {
        return mapper.selectByUserId(id);
    }
public IncorrectWord selectByEnglish(String word) {
    return mapper.selectByEnglish(word);
}

    public int updateByIdFilter(Object record) {
        return 0;
    }


    public int updateCount(IncorrectWord con) {
      return   mapper.updateCount(con);
    }
}
