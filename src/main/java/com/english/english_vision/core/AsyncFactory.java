package com.english.english_vision.core;

import com.english.english_vision.pojo.LoginLog;
import com.english.english_vision.pojo.Logininfor;
import com.english.english_vision.pojo.OnlineSession;
import com.english.english_vision.pojo.UserOnline;
import com.english.english_vision.service.ILoginLogService;
import com.english.english_vision.service.impl.ISysUserOnlineService;
import com.english.english_vision.service.impl.LogininforServiceImpl;
import com.english.english_vision.util.Constants;
import com.english.english_vision.util.ServletUtils;
import com.english.english_vision.util.ShiroUtils;
import com.english.english_vision.util.SpringUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.catalina.security.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.TimerTask;

/**
 * @Author
 * @Description
 * @Date
 **/
@Component
public class AsyncFactory {
    @Autowired
    private static ISysUserOnlineService onlineService;
    @Autowired
    private static ILoginLogService loginLogService;
    @Autowired
    private static LogininforServiceImpl logininforService;
    /**
     * 同步session到数据库
     *
     * @param session 在线用户会话
     * @return 任务task
     */
    public static TimerTask syncSessionToDb(final OnlineSession session)
    {

        return new TimerTask()
        {
            @Override
            public void run()
            {

                UserOnline online = new UserOnline();
                online.setSessionId(String.valueOf(session.getId()));

                online.setUserName(session.getUserName());
                online.setStartTimestamp(session.getStartTimestamp());
                online.setLastAccessTime(session.getLastAccessTime());
                online.setExpireTime(session.getTimeout());
                online.setIpaddr(session.getHost());
                online.setLoginLocation(session.getHost());
                online.setBrowser(session.getBrowser());
                online.setOs(session.getOs());

                onlineService.saveOnline(online);

            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final LoginLog operLog)
    {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                //  operLog.setIp(operLog.getOperIp());
                loginLogService.insert(operLog);
            }
        };

    }

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message, final Object... args)
    {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = ShiroUtils.getIp();
        return new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("记录登录");
                String address = ip;
                StringBuilder s = new StringBuilder();
                s.append(ip);
                s.append(address);
                s.append(username);
                s.append(status);
                s.append(message);
                // 打印信息到日志
              //  sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
             Logininfor logininfor = new Logininfor();
             logininfor.setInfoId(1L);
                logininfor.setLoginName(username);
                logininfor.setIpaddr(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setLoginTime(new Date());
                logininfor.setMsg(message);
                // 日志状态
                if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER))
                {
                    logininfor.setStatus(Constants.SUCCESS);
                }
                else if (Constants.LOGIN_FAIL.equals(status))
                {
                    logininfor.setStatus(Constants.FAIL);
                }
                // 插入数据
                System.out.println("logininfor:"+logininfor.toString());
         SpringUtils.getBean(LogininforServiceImpl.class).insert(logininfor);
     //  int num=   logininforService.insert(logininfor);
             //   System.out.println("插入记录的结果："+num);
            }
        };
    }
}
