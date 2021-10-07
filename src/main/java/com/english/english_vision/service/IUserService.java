package com.english.english_vision.service;


import com.english.english_vision.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
public interface IUserService  {
    public User selectUserByLoginName(String userName,String password);
}
