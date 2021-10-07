package com.english.english_vision.service;


import com.english.english_vision.pojo.ExamPaper;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
public interface IExamPaperService extends BaseService{
List<ExamPaper> papers (int level);
}
