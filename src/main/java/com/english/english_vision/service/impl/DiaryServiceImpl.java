package com.english.english_vision.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.english.english_vision.mapper.DiaryMapper;
import com.english.english_vision.pojo.Diary;
import com.english.english_vision.pojo.User;
import com.english.english_vision.service.IDiaryService;
import com.english.english_vision.vo.DiaryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.function.Function;

/**
 * @Author
 * @Description
 * @Date
 **/
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper,Diary> implements IDiaryService {
    @Autowired
    IDiaryService service;
    @Autowired
    private DiaryMapper diaryMapper;



    public int insert(Diary diary) {
        return diaryMapper.insert(diary);
    }



    @Override
    public List<Diary> selectAll() {
        return diaryMapper.selectAll();
    }


    public void saveOrUpdate(DiaryVo entity, User user)
    {
        if(entity == null) return;
        Diary diary = new Diary();
        BeanUtils.copyProperties(entity,diary,getNullPropertyNames(entity));
        diary.setUserName(user.getUserName());
        diary.setUserId(user.getId());
      saveOrUpdate(diary);
    }

    @Override
    public List<Diary> selectByTime(Date begin, Date end) {
        return diaryMapper.selectByTime(begin,end);
    }

    @Override
    public boolean saveOrUpdate(Diary entity) {
      //  entity.setCreateTime(new Date());
      return service.save(entity);

    }
    @Override
public int deleteDiary(int id)
{
  return   diaryMapper.deleteByPrimaryKey(id);
}
    @Override
    public Diary getOne(Wrapper<Diary> queryWrapper, boolean throwEx) {
        return service.getOne(queryWrapper);
    }


    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] property = beanWrapper.getPropertyDescriptors();
        HashSet<String> set = new HashSet<>();
        for(PropertyDescriptor pro:property)
        {
            Object name = pro.getName();
            if(name==null) set.add((String) name);
        }
        String[]nullPro = new String[set.size()];
        return set.toArray(nullPro);
    }
}
