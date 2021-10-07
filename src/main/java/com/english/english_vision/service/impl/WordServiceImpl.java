package com.english.english_vision.service.impl;

import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.enums.ResponseEnum;
import com.english.english_vision.pojo.Word;
import com.english.english_vision.mapper.WordMapper;
import com.english.english_vision.service.IWordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hehe
 * @since 2021-08-31
 */
@Service
public class WordServiceImpl implements IWordService {
@Autowired
private WordMapper wordMapper;

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
public ResponseResult<List<Word>> selectAll(){
        List<Word> li = wordMapper.selectAll();
        if(li == null) return ResponseResult.error(ResponseEnum.WORD_NOT_EXIST,ResponseEnum.WORD_NOT_EXIST.getDesc());
else return ResponseResult.success(li);
    }

    @Override
    public ResponseResult<Word> selectByEnglish(String english) {
        Word word = wordMapper.selectByEnglish(english);
        if(word == null) return ResponseResult.error(ResponseEnum.WORD_ERROR,ResponseEnum.WORD_ERROR.getDesc());
        else
            return ResponseResult.success(word);
    }

    @Override
    public ResponseResult<Word> selectByChinese(String chinese) {
        Word word = wordMapper.selectByChinese(chinese);
        if(word == null) return ResponseResult.error(ResponseEnum.WORD_ERROR_EXIST,ResponseEnum.WORD_ERROR_EXIST.getDesc());
        else
            return ResponseResult.success(word);
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
