package com.english.english_vision.config.shiro;

import com.english.english_vision.pojo.OnlineSession;
import com.english.english_vision.pojo.UserOnline;
import com.english.english_vision.util.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author
 * @Description
 * @Date
 **/
@Component
public class OnSessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext initData){
        UserOnline session = new UserOnline();
        if(!(initData == null) && initData instanceof WebSessionContext)
        {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if(request != null)
            {
                UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
             //   session.setHost(IpUtils.getIpAddr(request));
                session.setBrowser(browser);
                session.setOs(os);
            }
        }
        return session;
    }
}
