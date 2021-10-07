package com.english.english_vision.service;

import com.english.english_vision.pojo.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.english.english_vision.vo.BlogUser;
import com.english.english_vision.vo.BlogVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hehe
 * @since 2021-09-03
 */
public interface IBlogService extends IService<Blog> {
    List<Blog> selectall();
    List<Blog> selectBySum(String type);
    List<Blog> selectByQuestion(String type);
    Blog selectById(int id);
  List<BlogVo> getBlogRecommend() throws ExecutionException, InterruptedException;
List<Blog>selectByView();
    List<BlogVo> selectByUserID(Integer id);

    List<BlogUser> selectByArticleNums();
}
