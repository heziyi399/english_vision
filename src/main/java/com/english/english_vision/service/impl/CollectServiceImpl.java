package com.english.english_vision.service.impl;


import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.mapper.CollectMapper;
import com.english.english_vision.pojo.CollectWord;
import com.english.english_vision.service.ICollectService;
import com.github.pagehelper.PageInfo;
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
public class CollectServiceImpl  implements ICollectService {
@Autowired
    CollectMapper collectMapper;
    @Override
    public int deleteByEn(Integer id) {
        return 0;
    }

    @Override
    public ResponseResult<PageInfo> list(int uid, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<CollectWord> searchAllByStuId(Integer stuId) {
        return collectMapper.searchAllByStuId(stuId);
    }

    @Override
    public int insertCollect(CollectWord collect) {

               int num =  collectMapper.insertSelective(collect);

        return num;
    }

    @Override
    public CollectWord selectByEng(String english) {
        return collectMapper.selectByEng(english);
    }

    @Override
    public void updateState(CollectWord collectWord) {
        collectMapper.updateByPrimaryKey(collectWord);
    }

    @Override
    public List<CollectWord> selectByState(int masterBoolean) {
        return collectMapper.selectByState(masterBoolean);
    }


    @Override
    public ResponseResult<Integer> insertSelective(String english) {
        return null;
    }
@Override
    public void deleteByEnglish(String english) {
        collectMapper.dedeleteByEnglish(english);
    }
}
