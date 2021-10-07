package com.english.english_vision.Form;

import com.english.english_vision.exception.UserNotExistsException;
import com.english.english_vision.pojo.User;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.MessageUtils;

/**
 * @Author he
 * @Description 前端的登录表单
 * @Date 8.31
 **/
@Data
@Component
public class LoginForm {
    private String userName;
    private String password;

}
