package com.english.english_vision.service;

import com.english.english_vision.pojo.CollectBlog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
public interface ICollectBlogService extends IService<CollectBlog> {
    int addBlog(CollectBlog collectBlog);
}
