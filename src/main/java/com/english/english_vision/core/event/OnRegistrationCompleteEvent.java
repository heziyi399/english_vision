package com.english.english_vision.core.event;

import com.english.english_vision.pojo.User;
import org.springframework.context.ApplicationEvent;

/**
 * @Author
 * @Description
 * @Date
 **/
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final User user;
    public OnRegistrationCompleteEvent(final User user) {
        super(user);
        System.out.println("登录/注册了一个"+user.toString());
        this.user = user;
    }
    public User getUser() {
        return user;
    }
}
