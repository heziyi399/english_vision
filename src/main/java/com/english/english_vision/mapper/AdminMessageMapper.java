package com.english.english_vision.mapper;

import com.english.english_vision.pojo.AdminMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper//不加这个注解会报错
public interface AdminMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminMessage record);

    int insertSelective(AdminMessage record);

    AdminMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminMessage record);

    int updateByPrimaryKey(AdminMessage record);
}