package com.english.english_vision.pojo;

import com.english.english_vision.enums.OnlineStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * @Author
 * @Description在线用户属性
 * @Date 9.1
 **/
public class OnlineSession extends SimpleSession{

        private static final long serialVersionUID = 1L;

        /** 用户ID */
        private Long userId;

        /** 用户名称 */
        private String userName;


        /** 登录IP地址 */
        private String host;

        /** 浏览器类型 */
        private String browser;

        /** 操作系统 */
        private String os;



        /** 属性是否改变 优化session数据同步 */
        private transient boolean attributeChanged = false;

        @Override
        public String getHost()
        {
            return host;
        }

        @Override
        public void setHost(String host)
        {
            this.host = host;
        }

        public String getBrowser()
        {
            return browser;
        }

        public void setBrowser(String browser)
        {
            this.browser = browser;
        }

        public String getOs()
        {
            return os;
        }

        public void setOs(String os)
        {
            this.os = os;
        }

        public Long getUserId()
        {
            return userId;
        }

        public void setUserId(Long userId)
        {
            this.userId = userId;
        }

        public String getUserName()
        {
            return userName;
        }

        public void setLoginName(String loginName)
        {
            this.userName = loginName;
        }




        public void markAttributeChanged()
        {
            this.attributeChanged = true;
        }

        public void resetAttributeChanged()
        {
            this.attributeChanged = false;
        }

        public boolean isAttributeChanged()
        {
            return attributeChanged;
        }



        @Override
        public void setAttribute(Object key, Object value)
        {
            super.setAttribute(key, value);
        }

        @Override
        public Object removeAttribute(Object key)
        {
            return super.removeAttribute(key);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                    .append("userId", getUserId())
                    .append("userName", getUserName())


                    .append("host", getHost())
                    .append("browser", getBrowser())
                    .append("os", getOs())

                    .append("attributeChanged", isAttributeChanged())
                    .toString();
        }


}
