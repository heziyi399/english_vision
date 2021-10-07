package com.english.english_vision.mapper;

import com.english.english_vision.pojo.CollectBlog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hehe
 * @since 2021-09-04
 */
@Mapper
public interface CollectBlogMapper extends BaseMapper<CollectBlog> {
int addBlog(CollectBlog collectBlog);
}
