package com.english.english_vision.mapper;


import com.english.english_vision.pojo.Logininfor;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@Mapper
public interface LogininforMapper{
    int insert(Logininfor record);
}
