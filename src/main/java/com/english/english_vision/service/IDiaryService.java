package com.english.english_vision.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.pojo.Diary;
import com.english.english_vision.pojo.User;
import com.english.english_vision.vo.DiaryVo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface IDiaryService extends IService<Diary> {
    List<Diary> selectAll();

    int insert(Diary diary);
 int deleteDiary(int id);
    void saveOrUpdate(DiaryVo diaryVo, User user);

    List<Diary> selectByTime(Date begin, Date end);
}
