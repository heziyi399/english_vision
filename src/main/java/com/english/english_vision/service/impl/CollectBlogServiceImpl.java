package com.english.english_vision.service.impl;

import com.english.english_vision.mapper.BlogMapper;
import com.english.english_vision.pojo.Blog;
import com.english.english_vision.pojo.CollectBlog;
import com.english.english_vision.mapper.CollectBlogMapper;
import com.english.english_vision.pojo.CollectBlogDTO;
import com.english.english_vision.service.ICollectBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
@Service
public class CollectBlogServiceImpl extends ServiceImpl<CollectBlogMapper, CollectBlog> implements ICollectBlogService {
@Autowired
private CollectBlogMapper mapper;
@Autowired
private BlogMapper blogMapper;
    @Override
    public int addBlog(CollectBlog collectBlog) {
        return mapper.addBlog(collectBlog);
    }

    @Override
    public List<Blog> selectByMostCollect() {
       List<CollectBlogDTO> collectBlogDTOS=  mapper.selectByMostCollect();
       List<Blog> blogs = collectBlogDTOS.stream().map(e->{
          Blog collectBlog =blogMapper.selectById(e.getBlogId());
           return collectBlog;

       }).collect(Collectors.toList());
       return blogs;
    }
}
