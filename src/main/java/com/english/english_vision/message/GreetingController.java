package com.english.english_vision.message;

import com.english.english_vision.mapper.AdminMessageMapper;
import com.english.english_vision.message.Message;
import com.english.english_vision.pojo.AdminMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * @Author
 * @Description
 * @Date
 **/
@Controller
public class GreetingController {
    @Autowired
    private AdminMessageMapper adminMessageMapper;
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(Message message){
       // com.english.english_vision.pojo.Message message2;
        AdminMessage adminMessage = new AdminMessage();
        System.out.println(message.toString());
  //  BeanUtils.copyProperties(adminMessage,message);
    adminMessage.setName(message.getName());
    adminMessage.setContent(message.getContent());
    adminMessage.setTitle(message.getTitle());
//adminMessage.setId(1);
    adminMessage.setCreateTime(new Date());
        System.out.println(adminMessage.toString());
    adminMessageMapper.insert(adminMessage);

        return message;
    }
//    根据第2步的配置，@MessageMapping("/hello")注解的方法将用来接收
//    “/app/hello”路径发送来的消息，在注解方法中对消息进行处理后，
//    再将消息转发到@SendTo定义的路径上，
//    而@SendTo路径是一个前缀为“/topic”的路径，
//    因此该消息将被交给消息代理broker，再由broker进行广播。


}
