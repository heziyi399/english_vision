package com.english.english_vision.service.impl;

import com.english.english_vision.mapper.SuggestionMapper;
import com.english.english_vision.pojo.Suggestion;
import com.english.english_vision.service.ISuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Description
 * @Date
 **/
@Service
public class SuggestionServiceImpl implements ISuggestionService {
    @Autowired
    private SuggestionMapper suggestionMapper;
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
    public List<Suggestion>selectAll(){
        return suggestionMapper.selectAll();
    }
}
