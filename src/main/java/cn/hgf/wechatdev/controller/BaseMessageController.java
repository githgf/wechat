package cn.hgf.wechatdev.controller;

import cn.hgf.wechatdev.model.TextMessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}
