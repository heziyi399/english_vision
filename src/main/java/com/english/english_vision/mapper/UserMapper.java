package com.english.english_vision.mapper;


import com.english.english_vision.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@Mapper
public interface UserMapper  {
    public User selectUserByLoginName(String userName,String password);
    public int insert(User record);

    int update(Object record);

    User selectByName(String name);
}
