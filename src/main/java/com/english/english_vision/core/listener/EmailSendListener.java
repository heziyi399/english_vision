package com.english.english_vision.core.listener;

import com.english.english_vision.core.event.OnRegistrationCompleteEvent;
import lombok.NonNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Description
 * @Date
 **/
@Component

public class EmailSendListener  {
//implements ApplicationListener<OnRegistrationCompleteEvent>
    @NonNull
    @EventListener(classes=OnRegistrationCompleteEvent.class)
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        //事件发生后回调的方法
        System.out.println("监听"+event.getUser().toString());
    }
}
