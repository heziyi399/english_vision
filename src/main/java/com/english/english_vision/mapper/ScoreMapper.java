package com.english.english_vision.mapper;

import com.english.english_vision.pojo.Score;

public interface ScoreMapper {
    int deleteByPrimaryKey(Integer scoreid);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer scoreid);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
}