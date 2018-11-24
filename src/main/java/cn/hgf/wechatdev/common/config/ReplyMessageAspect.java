package cn.hgf.wechatdev.common.config;

import cn.hgf.wechatdev.model.BaseMessageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@Aspect
public class ReplyMessageAspect{

    @Pointcut("execution(public * cn.hgf.wechatdev.controller..*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Object postHandlerReplyMessage(ProceedingJoinPoint proceedingJoinPoint){
        BaseMessageInfo baseMessageInfo = null;
        try {
            baseMessageInfo = (BaseMessageInfo) proceedingJoinPoint.proceed();


            String fromUserName = baseMessageInfo.getFromUserName();
            String toUserName = baseMessageInfo.getToUserName();

            baseMessageInfo.setToUserName(fromUserName);
            baseMessageInfo.setFromUserName(toUserName);


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return baseMessageInfo;
    }

}
