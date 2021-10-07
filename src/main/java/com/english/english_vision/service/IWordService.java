package com.english.english_vision.service;

import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.pojo.Word;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hehe
 * @since 2021-08-31
 */
public interface IWordService extends BaseService {
    public ResponseResult<List<Word>> selectAll();
    public ResponseResult<Word> selectByEnglish(String english);
    public ResponseResult<Word> selectByChinese(String chinese);

}
