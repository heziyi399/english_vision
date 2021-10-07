package com.english.english_vision.mapper;

import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.pojo.Word;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Word record);

    int insertSelective(Word record);

    Word selectByPrimaryKey(Integer id);
    Word selectByEnglish(String english);
    //Word selectByChinese(String chinese);
    int updateByPrimaryKeySelective(Word record);

    int updateByPrimaryKey(Word record);
    List<Word> selectAll();
    Word selectByChinese(String chinese);
    int deleteByEnglish(String english);
}