package com.english.english_vision.exception;

import com.baomidou.mybatisplus.extension.api.R;

/**
 * @Author
 * @Description 用户校验异常
 * @Date
 **/
public class UserNotExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UserNotExistsException()
    {
        super("user.not.exists", null);
    }
}
