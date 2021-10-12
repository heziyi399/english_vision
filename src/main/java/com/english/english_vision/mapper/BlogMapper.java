package com.english.english_vision.mapper;

import com.english.english_vision.pojo.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.english.english_vision.vo.BlogUser;
import com.english.english_vision.vo.BlogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hehe
 * @since 2021-09-03
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
int insertid(Blog blog);
List<Blog>selectall();
    List<Blog>selectallDesc();
    List<Blog> selectBySum(String type);
    List<Blog> selectBySumDesc(String type);
    List<Blog> selectByQuestion(String type);
    List<Blog> selectByQuestionDesc(String type);
    Blog selectById(int id);

    List<Blog> selectByUserId(int id);
    @Select("Select * from t_blog where user_id = #{id} order by create_time")
    List<BlogVo> selectByUserID(Integer id);
    @Select("Select * from t_blog order by views desc")
    List<Blog> selectByView();
@Select("SELECT COUNT(*)as num,user_name,age,user_id FROM  (SELECT * from t_user) as users,t_blog " +
        "WHERE users.id = t_blog.user_id GROUP BY user_id ORDER BY  COUNT(*) desc")
    List<BlogUser> selectByNum();
}
