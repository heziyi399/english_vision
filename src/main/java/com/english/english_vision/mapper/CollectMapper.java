package com.english.english_vision.mapper;

import com.english.english_vision.pojo.CollectWord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectMapper {
    int deleteByPrimaryKey(CollectWord key);

    int insert(CollectWord record);

   int insertSelective(CollectWord record);

    CollectWord selectByPrimaryKey(Integer id,Integer stuId);
List<CollectWord> searchAllByStuId(Integer stuId);
    int updateByPrimaryKeySelective(CollectWord record);

    int updateByPrimaryKey(CollectWord record);
    int insertByEnglish(CollectWord record);

    void dedeleteByEnglish(String english);

    CollectWord selectByEng(String english);

    List<CollectWord> selectByState(int masterBoolean);
}