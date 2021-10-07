package com.english.english_vision.service.impl;

import com.english.english_vision.mapper.ExamPaperMapper;
import com.english.english_vision.pojo.ExamManage;
import com.english.english_vision.mapper.ExamManageMapper;
import com.english.english_vision.service.IExamManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
@Service
public class ExamManageServiceImpl extends ServiceImpl<ExamManageMapper, ExamManage> implements IExamManageService {
@Autowired
    ExamPaperMapper mapper;
    @Override
    public List<Integer> getbypid(Integer id) {
        return mapper.getbypid(id);
    }


}
