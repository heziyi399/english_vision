package com.english.english_vision.service;


import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.pojo.CollectBlog;
import com.english.english_vision.pojo.CollectWord;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
public interface ICollectService {
ResponseResult<Integer>insertSelective(String english);
    public int deleteByEn(Integer id) ;
    ResponseResult<PageInfo> list(int uid, Integer pageNum, Integer pageSize);
    List<CollectWord> searchAllByStuId(Integer stuId);
    int insertCollect(CollectWord collect);

    CollectWord selectByEng(String english);

    void updateState(CollectWord collectWord);

    List<CollectWord> selectByState(int masterBoolean);

    void deleteByEnglish(String english);


}
