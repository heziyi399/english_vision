package com.english.english_vision.mapper;

import com.english.english_vision.pojo.Comment;
import com.english.english_vision.pojo.Suggestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SuggestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Suggestion record);

    int insertSelective(Suggestion record);

    Suggestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Suggestion record);

    int updateByPrimaryKey(Suggestion record);
    List<Suggestion> selectAll();
}