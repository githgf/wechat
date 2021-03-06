package cn.hgf.wechatdev.common.aspect;

import cn.hgf.wechatdev.model.BaseMessageInfo;
import cn.hgf.wechatdev.model.TextMessageInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *  日志切面
 */

@Component
@Aspect
@Order(1)
public class LogAspect {
    @Pointcut("execution(public * cn.hgf.wechatdev.controller..*.*(..))")
    public void requestMapopingPointCut(){};
    Logger logger = LoggerFactory.getLogger(LogAspect.class);


    @Around("requestMapopingPointCut()")
    public Object saveLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object target = proceedingJoinPoint.getTarget();
        Signature signature = proceedingJoinPoint.getSignature();
        if (!(signature instanceof MethodSignature)){
            return proceedingJoinPoint.proceed();
        }

        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = null;
        try {
            method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Object[] methodArgs = proceedingJoinPoint.getArgs();
        JSONObject requestJsonObj = new JSONObject();

        requestJsonObj.put("functionName",method.getName());
        requestJsonObj.put("controllerName",target.getClass().getName());

        StringBuilder paramBuilderStr = new StringBuilder();

        if (methodArgs != null && methodArgs.length > 0){

            for (Object methodArg : methodArgs) {
                if (methodArg == null)continue;
                String name = methodArg.getClass().getName();

                if(name.contains("org.apache.catalina.connector")
                        ||  "org.springframework.web.multipart.commons.CommonsMultipartFile".equals(name)
                        || name.contains("org.springframework")){
                    continue;
                }

                paramBuilderStr.append(JSON.toJSONString(methodArg));
            }

            requestJsonObj.put("requestParam",paramBuilderStr);
        }


        logger.info("接收到请求 ===> " + requestJsonObj);

        Object proceed = null;
        requestJsonObj = new JSONObject();
        try {
            proceed = proceedingJoinPoint.proceed();
            requestJsonObj.put("paramValue", proceed != null ? JSON.toJSONString(proceed) : "null");

        } catch (Throwable throwable) {
            requestJsonObj.put("exception",throwable.getMessage());
            throwable.printStackTrace();
        }

        logger.info("返回请求 ===> " + requestJsonObj);

        return proceed;
    }
}
