package com.example.config.aop;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.mapper.OperationLog;
import com.example.mapper.RootLogin;
import com.example.pojo.entity.StudioRoot;
import com.example.utils.IpUntil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * TODO
 * 切面处理类，操作日志异常日志记录处理
 * @author chen
 * @version 1.0
 * @date 2021/9/30 18:32
 */
@Aspect
@Component
public class OperLogAspect {


    @Autowired
    OperationLog operationLog;

    @Autowired
    RootLogin rootLogin;

    /**





    /**
     * 用于拦截异常日志信息
     *
     * @param joinPoint 切入点
     */
    @AfterReturning(value = "execution(public * com.example.controller..*.*(..))",returning="rvt")
    public void saveExceptionLog(JoinPoint joinPoint,Object rvt) throws Exception {

        String userid = null;
        String message= null;
        String messagedate = null;
        //        String usernumber=null;

        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request;
        request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();

        //获得请求方式
        String method = request.getMethod();

        //获取请求路径
        String actionUrl = request.getRequestURI();

        //获取IP地址
        String ipAddr = IpUntil.getIpAddr(request);

        //获取当前日期时间
        long timeMillis = System.currentTimeMillis();

        //如果是post请求  则拿post请求的数据
        if ("POST".equals(method)){
            try {
                //拿到登录的时候的请求
                Object[] args = joinPoint.getArgs();
                Object arg = args[0];
                ObjectMapper map = new ObjectMapper();
                //操作的时候的信息
                messagedate = JSONObject.toJSONString(arg);
                System.out.println(messagedate);
            } catch (Exception e) {
            }
        }else if ("GET".equals(method)){
            messagedate = Arrays.asList(joinPoint.getArgs()).toString();
        }


        try {
            String number = StpUtil.getLoginId().toString();

            //拿到userid
            StudioRoot permissions = rootLogin.getrole(number);
            userid = permissions.getUserId();
        } catch (Exception e) {
        }


        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 获取切入点所在的方法
        Method method1 = signature.getMethod();

        OperLog opLog = method1.getAnnotation(OperLog.class);

        String operDesc = null;
        String operModul = null;
        String operType = null;
        if (opLog != null) {
            operDesc = opLog.operDesc(); // 操作描述
            operModul = opLog.operModul(); // 操作模块
            operType = opLog.operType();   // 操作类型
        }

        String code = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            //对象转map
            Map m2 = mapper.readValue(mapper.writeValueAsString(rvt), Map.class);
            code = m2.get("code").toString();
            if (!"200".equals(code)){
                message = m2.get("message").toString();
            }
        } catch (Exception e) {
            //no nothing
        }

        operationLog.insertOperationLog(userid, operModul, operDesc, operType, actionUrl, ipAddr,messagedate, code,  message, String.valueOf(timeMillis), String.valueOf(timeMillis), String.valueOf(timeMillis));
    }

}
