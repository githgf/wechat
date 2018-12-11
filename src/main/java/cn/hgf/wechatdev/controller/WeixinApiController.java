package cn.hgf.wechatdev.controller;

import cn.hgf.wechatdev.common.api.WeixinApi;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;

@Controller("weixinapi")
public class WeixinApiController {
    @Resource
    WeixinApi weixinApi;

    @RequestMapping(value = "getArticleImageUrl",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getArticleImageUrl(@RequestParam(value = "path",required = false) String filePath){
        File file = new File(filePath);
        return weixinApi.getArticleImageUrl(file);
    }
}
