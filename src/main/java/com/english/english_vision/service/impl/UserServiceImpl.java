package com.english.english_vision.service.impl;


import com.english.english_vision.Base.ResponseResult;
import com.english.english_vision.core.AsyncFactory;
import com.english.english_vision.core.event.OnRegistrationCompleteEvent;
import com.english.english_vision.exception.UserNotExistsException;
import com.english.english_vision.exception.UserPasswordNotMatchException;
import com.english.english_vision.mapper.UserMapper;
import com.english.english_vision.pojo.User;
import com.english.english_vision.service.IUserService;
import com.english.english_vision.vo.UserConditionVo;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hehe
 * @since 2021-08-29
 */
@Service
public class UserServiceImpl  implements IUserService {
@Autowired
    UserMapper userMapper;
@Autowired
private ApplicationEventPublisher publisher;
    private final int OPERATE_DELAY_TIME = 10;
    @Override
    public User selectUserByLoginName(String userName,String password) {

            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password))
            {//校验空
                //信息写入到用户操作日志中
                //    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
                throw new UserNotExistsException();
            }
          if(userMapper.selectUserByLoginName(userName,password)==null)
          {
              throw new UserPasswordNotMatchException();
        }
        User user=userMapper.selectUserByLoginName(userName,password);
        publisher.publishEvent(new OnRegistrationCompleteEvent(user));
        scheduledExecutorService().schedule( AsyncFactory.recordLogininfor(userName,"over","SUCCESS"), OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
        return userMapper.selectUserByLoginName(userName,password);
    }


    public int deleteById(Integer id) {
        return 0;
    }


    public ResponseResult insert(User record) {
        userMapper.insert(record);
   publisher.publishEvent(new OnRegistrationCompleteEvent(record));
        return ResponseResult.success(record);
    }
   public boolean checkNameUnique(UserConditionVo user)
   {
String name = user.getUserName();
       User newUser = selectByName(name);
       Optional<User> predict = Optional.ofNullable(newUser);
     return  predict.isPresent();

   }

    public int insertByFilter(Object record) {
        return 0;
    }


    public Object selectById(Integer id) {
        return null;
    }
public User selectByName(String name)
{
    return userMapper.selectByName(name);
}

    public int updateByIdFilter(Object record) {
      int n= userMapper.update(record);
      return n;
    }


    public int updateById(Object record) {
        return 0;
    }
    protected ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(10,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);

            }
        };
    }
}
