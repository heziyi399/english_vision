package com.english.english_vision.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.english.english_vision.mapper.BlogMapper;
import com.english.english_vision.pojo.Blog;
import com.english.english_vision.service.IBlogService;
import com.english.english_vision.vo.BlogUser;
import com.english.english_vision.vo.BlogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.english.english_vision.enums.BusinessStatus;
/**
 * @Author
 * @Description
 * @Date
 **/
@Service
public class BlogService2Impl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {
  @Autowired
  private BlogMapper blogMapper;
    @Override
    public List<Blog> selectall() {
        return blogMapper.selectallDesc();
    }

    @Override
    public List<Blog> selectBySum(String type) {
        return blogMapper.selectBySumDesc(type);
    }

    @Override
    public List<Blog> selectByQuestion(String type) {
        return blogMapper.selectByQuestionDesc(type);
    }

    @Override
    public Blog selectById(int id) {
        return blogMapper.selectById(id);
    }

    public List<Blog> selectByUserId(int id){
        return blogMapper.selectByUserId(id);
    }

    @Override
    public List<BlogVo> getBlogRecommend() throws ExecutionException, InterruptedException {
        // 查看推荐文章列表
        CompletableFuture<List<BlogVo>> recommendBlog = CompletableFuture.supplyAsync(()->{
            List<BlogVo> blogs = blogMapper.selectList(new LambdaQueryWrapper<Blog>().select(Blog::getAppreciation,
                    Blog::getContent, Blog::getCreateTime, Blog::getDescription, Blog::getFirstPicture, Blog::getTitle, Blog::getType)
                    .eq(Blog::getRecommend, 1).orderByDesc(Blog::getCreateTime)).stream().map(e->
            {
                BlogVo vo = new BlogVo();
                BeanUtils.copyProperties(e,vo);
                return vo;
            }).collect(Collectors.toList());
          return blogs;
        });
return recommendBlog.get();

    }

    @Override
    public List<Blog> selectByView() {
        return blogMapper.selectByView();
    }

    @Override
    public List<BlogVo> selectByUserID(Integer id) {
        return blogMapper.selectByUserID(id);
    }

    @Override
    public List<BlogUser> selectByArticleNums() {
        return blogMapper.selectByNum();
    }

    /**
     * 更新文章浏览量
     *
     * @param articleId 文章id
     */
    @Async
    public void updateArticleViewsCount(Integer articleId) {

    }

}
