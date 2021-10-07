package com.english.english_vision.vo;

import com.english.english_vision.pojo.Blog;
import lombok.Data;

import java.util.List;

/**
 * @Author
 * @Description 用户-帖子关系
 * @Date
 **/
@Data
public class BlogUser {
    private int num;
    private List<BlogVo>blogs;
    private int userId;
    private int age;
    private String userName;
}
