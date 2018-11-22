package cn.hgf.wechatdev.controller;

import cn.hgf.wechatdev.common.CommonParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class BaseMessageController {
    @GetMapping("getToken")
    public String getToken(){
        return CommonParam.WECHAT_SERVER_KEY;
    }
}
