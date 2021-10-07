package com.english.english_vision.service.impl;


import com.english.english_vision.mapper.ExamPaperMapper;
import com.english.english_vision.pojo.ExamPaper;
import com.english.english_vision.service.IExamPaperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@Service
public class ExamPaperServiceImpl implements IExamPaperService {
    @Autowired
    private ExamPaperMapper examPaperMapper;

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int insert(Object record) {
        return 0;
    }

    @Override
    public int insertByFilter(Object record) {
        return 0;
    }

    @Override
    public ExamPaper selectById(Integer id) {
        return examPaperMapper.selectById(id);
    }

    @Override
    public int updateByIdFilter(Object record) {
        return 0;
    }

    @Override
    public int updateById(Object record) {
        return 0;
    }

    @Override
    public List<ExamPaper> papers(int level) {
        return examPaperMapper.selectByLevel(level);
    }
}
