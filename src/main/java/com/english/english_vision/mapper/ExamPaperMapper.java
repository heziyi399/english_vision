package com.english.english_vision.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.english.english_vision.pojo.ExamPaper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@Mapper
public interface ExamPaperMapper extends BaseMapper<ExamPaper> {
public ExamPaper selectAll();

    List<ExamPaper> selectByLevel(int level);

    List<Integer> getbypid(Integer id);
}
