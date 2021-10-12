package com.english.english_vision.core.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.english.english_vision.core.AsyncFactory;
import com.english.english_vision.enums.BusinessStatus;

import com.english.english_vision.mapper.LoginLogMapper;
import com.english.english_vision.pojo.LoginLog;
import com.english.english_vision.pojo.User;
import com.english.english_vision.util.ServletUtils;
import com.english.english_vision.util.ShiroUtils;
import com.english.english_vision.util.StringUtils;
import com.english.english_vision.util.annotation.Log;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 操作日志记录处理
 * 
 * @author ruoyi
 */
@Aspect
@Component
public class LogAspect
{
    @Autowired
    private LoginLogMapper loginLogMapper;
    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /** 排除敏感属性字段 */
    public static final String[] EXCLUDE_PROPERTIES = { "password", "oldPassword", "newPassword", "confirmPassword" };

    // 配置织入点
    @Pointcut("@annotation(com.english.english_vision.util.annotation.Log)")
    public void logPointCut()
    {
        System.out.println("yes999");
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) throws Exception {

        handleLog(joinPoint, null, jsonResult);
    }
    @Before("logPointCut()")
    public void before(){
        System.out.println("before");
    }

    /**
     * 拦截异常操作
     * 
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) throws Exception {

        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) throws Exception {
        try {
            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }

            // 获取当前的用户
            User currentUser = (User) SecurityUtils.getSubject().getPrincipal();

            // *========数据库日志=========*//
            LoginLog operLog = new LoginLog();
            operLog.setStatus(String.valueOf(BusinessStatus.SUCCESS.ordinal()));
            // 请求的地址
            String ip = ShiroUtils.getIp();
            operLog.setIp(ip);
            // 返回参数
//            if (StringUtils.isNotNull(jsonResult))
//            {
//                operLog.setJsonResult(StringUtils.substring(JSON.marshal(jsonResult), 0, 2000));
//            }
//
//            operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            if (currentUser != null) {
                operLog.setLastUpdateBy(currentUser.getUserName());
                operLog.setLastUpdateTime(new Date());
            }

            if (e != null)
            {
                operLog.setStatus(BusinessStatus.FAIL.toString());
             //   operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
//            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
        //    operLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
        //operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
     getControllerMethodDescription(joinPoint, controllerLog, operLog);
            // 保存数据库
            System.out.println("报存："+operLog.toString());
           loginLogMapper.insertSelective(operLog);
      scheduledExecutorService().schedule( AsyncFactory.recordOper(operLog), OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
//  AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
//        }


        }
                catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

        protected  ScheduledExecutorService scheduledExecutorService()
        {
            return new ScheduledThreadPoolExecutor(10,
                    new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build()) {
                @Override
                protected void afterExecute(Runnable r, Throwable t) {
                    super.afterExecute(r, t);

                }
            };
        }
    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log 日志
     * @param operLog 操作日志
     * @throws Exception
     */
        public void getControllerMethodDescription (JoinPoint joinPoint, Log log, LoginLog operLog) throws Exception {
//        // 设置action动作
//            枚举类 ordinal () 方法ordinal () 方法 在java.lang包中可用。
//            ordinal () 方法 用于返回此枚举常量 的 位置，
//            该位置取决于其枚举声明中定义 的 内容，并且枚举常量 的 起始元素 的 位置从0开始。
        operLog.setOprType(log.businessType().toString());
        // 设置标题
        operLog.setTitle(log.title());
        // 设置操作人类别
        operLog.setOperatorType(log.operatorType().toString());
            System.out.println(operLog.toString());
        // 是否需要保存request，参数和值
//        if (log.isSaveRequestData())
//        {
//            // 获取参数的信息，传入到数据库中。
//            setRequestValue(joinPoint, operLog);
//        }
//        }


    }

    /**
     * 获取请求的参数，放到log中
     * 
     * @param operLog 操作日志
     * @throws Exception 异常
     */
//    private void setRequestValue(JoinPoint joinPoint, LoginLog operLog) throws Exception
//    {
//        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
//        if (StringUtils.isNotEmpty(map))
//        {
//            String params = JSONObject.toJSONString(map, excludePropertyPreFilter());
//            operLog.setOperParam(StringUtils.substring(params, 0, 2000));
//        }
//        else
//        {
//            Object args = joinPoint.getArgs();
//            if (StringUtils.isNotNull(args))
//            {
//                String params = argsArrayToString(joinPoint.getArgs());
//                operLog.setOperParam(StringUtils.substring(params, 0, 2000));
//            }
//        }
//    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    /**
     * 忽略敏感属性
     */
    public PropertyPreFilters.MySimplePropertyPreFilter excludePropertyPreFilter()
    {
        return new PropertyPreFilters().addFilter().addExcludes(EXCLUDE_PROPERTIES);
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (int i = 0; i < paramsArray.length; i++)
            {
                if (StringUtils.isNotNull(paramsArray[i]) && !isFilterObject(paramsArray[i]))
                {
                    Object jsonObj = JSONObject.toJSONString(paramsArray[i], excludePropertyPreFilter());
                    params += jsonObj.toString() + " ";
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     * 
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o)
    {
        Class<?> clazz = o.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) o;
            for (Iterator iter = collection.iterator(); iter.hasNext();)
            {
                return iter.next() instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) o;
            for (Iterator iter = map.entrySet().iterator(); iter.hasNext();)
            {
                Map.Entry entry = (Map.Entry) iter.next();
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
