package com.english.english_vision.exception;

import org.omg.CORBA.UserException;

/**
 * 用户密码不正确或不符合规范异常类
 * 
 * @author ruoyi
 */
public class UserPasswordNotMatchException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super("user.password.not.match", null);
    }
}
