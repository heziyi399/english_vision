package com.english.english_vision.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.english.english_vision.pojo.Diary;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface DiaryMapper extends BaseMapper<Diary> {
    int deleteByPrimaryKey(Integer id);

    int insert(Diary record);

    int insertSelective(Diary record);

    Diary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Diary record);
    List<Diary> selectAll();
    int updateByPrimaryKey(Diary record);

    List<Diary> selectByTime(Date begin, Date end);
}