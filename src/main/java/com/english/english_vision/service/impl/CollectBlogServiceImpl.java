package com.english.english_vision.service.impl;

import com.english.english_vision.pojo.CollectBlog;
import com.english.english_vision.mapper.CollectBlogMapper;
import com.english.english_vision.service.ICollectBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public int addBlog(CollectBlog collectBlog) {
        return mapper.addBlog(collectBlog);
    }
}
