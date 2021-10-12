package com.english.english_vision.mapper;

import com.english.english_vision.pojo.CollectBlog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.english.english_vision.pojo.CollectBlogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

@Select("SELECT  blog_type,blog_id,count(*) as num FROM `t_collect_blog` GROUP BY blog_id ORDER BY count(*) DESC")
    List<CollectBlogDTO> selectByMostCollect();
}
