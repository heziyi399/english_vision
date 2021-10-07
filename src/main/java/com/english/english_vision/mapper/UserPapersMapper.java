package com.english.english_vision.mapper;

import com.english.english_vision.pojo.UserPapers;

public interface UserPapersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPapers record);

    int insertSelective(UserPapers record);

    UserPapers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPapers record);

    int updateByPrimaryKey(UserPapers record);
}