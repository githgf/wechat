package cn.hgf.wechatdev.controller;

import cn.hgf.wechatdev.common.annotation.MessageHandler;
import cn.hgf.wechatdev.model.BaseMessageInfo;
import cn.hgf.wechatdev.model.TextMessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base")
public class BaseMessageController {
    @GetMapping("getToken")
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

    @PostMapping("getFamilyMessage")
    public String getFamilyMessage(@RequestBody TextMessageInfo textMessageInfo){
        System.out.println(textMessageInfo);
        return "i love my family";
    }

    @PostMapping(value = "textMessageInfo",consumes = { MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_XML_VALUE)
    @MessageHandler
    public TextMessageInfo textMessageInfo(@RequestBody TextMessageInfo textMessageInfo){
        textMessageInfo.setContent("欢迎关注帆影ing公众号");
        return textMessageInfo;

    }@PostMapping(value = "textBaseInfo",consumes = { MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_XML_VALUE)
    public BaseMessageInfo textBase(@RequestBody BaseMessageInfo baseMessageInfo){
        return baseMessageInfo;
    }
}
