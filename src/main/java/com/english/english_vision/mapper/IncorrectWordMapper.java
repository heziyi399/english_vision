package com.english.english_vision.mapper;

import com.english.english_vision.pojo.IncorrectWord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hehe
 * @since 2021-09-05
 */
@Mapper
public interface IncorrectWordMapper extends BaseMapper<IncorrectWord> {

  public   List<IncorrectWord> selectByUserId(Integer id);
public int selectCount(String word);
 public    IncorrectWord selectByEnglish(String word);

    int updateCount(IncorrectWord word);
}
