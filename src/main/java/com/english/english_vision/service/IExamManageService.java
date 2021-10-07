package com.english.english_vision.service;

import com.english.english_vision.pojo.ExamManage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
public interface IExamManageService extends IService<ExamManage> {
  public   List<Integer> getbypid(Integer id);

}
