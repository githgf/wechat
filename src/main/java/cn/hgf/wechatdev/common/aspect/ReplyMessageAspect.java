package cn.hgf.wechatdev.common.aspect;

import cn.hgf.wechatdev.model.BaseMessageInfo;
import com.thoughtworks.xstream.XStream;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@Aspect
public class ReplyMessageAspect{

    @Pointcut("execution(public * cn.hgf.wechatdev.controller..*.*(..)) && @annotation(cn.hgf.wechatdev.common.annotation.MessageHandler)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object postHandlerReplyMessage(ProceedingJoinPoint proceedingJoinPoint){
        BaseMessageInfo baseMessageInfo = null;
        try {
            Object proceed = proceedingJoinPoint.proceed();
            baseMessageInfo = (BaseMessageInfo) proceed;


            String fromUserName = baseMessageInfo.getFromUserName();
            String toUserName = baseMessageInfo.getToUserName();

            baseMessageInfo.setToUserName(fromUserName);
            baseMessageInfo.setFromUserName(toUserName);

            XStream xstream = new XStream();
            xstream.alias("xml", proceed.getClass());
            return xstream.toXML(baseMessageInfo);


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return baseMessageInfo;
    }

}
