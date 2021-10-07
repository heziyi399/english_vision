package com.english.english_vision.service.impl;

import com.english.english_vision.pojo.Question;
import com.english.english_vision.mapper.QuestionMapper;
import com.english.english_vision.service.BaseService;
import com.english.english_vision.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
@Autowired
private QuestionMapper questionMapper;

    public int deleteById(Integer id) {
        return 0;
    }


    public int insert(Object record) {
        return 0;
    }


    public int insertByFilter(Object record) {
        return 0;
    }

@Override
    public Question selectById(Integer id) {
        return questionMapper.selectById(id);
    }


    public int updateByIdFilter(Object record) {
        return 0;
    }




}
