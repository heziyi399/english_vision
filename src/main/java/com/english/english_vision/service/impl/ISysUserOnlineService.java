package com.english.english_vision.service.impl;

import com.english.english_vision.mapper.UserOnlineMapper;
import com.english.english_vision.pojo.UserOnline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author
 * @Description
 * @Date
 **/
@Service
public class ISysUserOnlineService {
    @Autowired
    private UserOnlineMapper userOnlineDao;
    /**
     * 保存会话信息
     *
     * @param online 会话信息
     */
    public void saveOnline(UserOnline online){

        userOnlineDao.saveOnline(online);
    }

    /**
     * 查询会话集合
     *
     * @param userOnline 分页参数
     * @return 会话集合
     */
    public List<UserOnline> selectUserOnlineList(UserOnline userOnline){
        return userOnlineDao.selectUserOnlineList(userOnline);
    }
    /**
     * 清理用户缓存
     *
     * @param loginName 登录名称
     * @param sessionId 会话ID
     */
//    public void removeUserCache(String loginName, String sessionId){
//        userOnlineDao.deleteOnlineById()
//    }

    /**
     * 查询会话集合
     *
     * @param expiredDate 有效期
     * @return 会话集合
     */
 //   public List<UserOnline> selectOnlineByExpired(Date expiredDate);

}
