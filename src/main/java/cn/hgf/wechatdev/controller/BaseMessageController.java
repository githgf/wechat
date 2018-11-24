package cn.hgf.wechatdev.controller;

import cn.hgf.wechatdev.common.annotation.MessageHandler;
import cn.hgf.wechatdev.common.constant.CommonParam;
import cn.hgf.wechatdev.common.util.MessageUtil;
import cn.hgf.wechatdev.helper.BaseMessageHelper;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/base")
public class BaseMessageController {
    @Autowired
    BaseMessageHelper baseMessageHelper;

    @RequestMapping(value = "weixin",method = RequestMethod.GET)
    public String getToken(@RequestParam(value = "signature",required = false) String signature,
                           @RequestParam(value = "nonce",required = false) String nonce,
                           @RequestParam(value = "timestamp",required = false) String timestamp,
                           @RequestParam(value = "echostr",required = false) String echostr){
        Logger logger = LoggerFactory.getLogger(BaseMessageController.class);
        logger.info("请求参数是 ==> signature ==>" + signature);
        logger.info("请求参数是 ==> nonce ===>" + nonce);
        logger.info("请求参数是 ==> timestamp ==>" + timestamp);
        logger.info("请求参数是 ==> echostr ==>" + echostr);
        return echostr;
    }

    @RequestMapping(value = "weixin",method = RequestMethod.POST)
    @MessageHandler
    public Object textMessageInfo(HttpServletRequest httpServletRequest){
        JSONObject jsonObject = MessageUtil.xmlToMap(httpServletRequest);
        if (jsonObject.get(CommonParam.MESSAGE_TYPE) != null){

            return baseMessageHelper.parseMessageInfo(jsonObject);
        }
        return CommonParam.WECHAT_REPLY_TEXT_SUCCESS;
    }
}
