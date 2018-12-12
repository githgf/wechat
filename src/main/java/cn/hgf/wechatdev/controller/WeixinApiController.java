package cn.hgf.wechatdev.controller;

import cn.hgf.wechatdev.common.api.WeixinApi;
import cn.hgf.wechatdev.common.constant.CommonParam;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;

@Controller
@RequestMapping("winxinapi")
public class WeixinApiController {
    @Resource
    WeixinApi weixinApi;

    @RequestMapping(value = "getArticleImageUrl",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getArticleImageUrl(@RequestParam(value = "path",required = false) String filePath){
        File file = new File(filePath);
        return weixinApi.getArticleImageUrl(file);
    }

    @RequestMapping(value = "getMediaType",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getMediaList(@RequestParam(value = "mediaType",defaultValue = CommonParam.MESSAGE_TYPE_IMAGE) String mediaType,
                                   @RequestParam(value = "offest",defaultValue = "0") Integer offest,
                                   @RequestParam(value = "count",defaultValue = "20") Integer count){
        return weixinApi.getMediaList(mediaType,offest,count);
    }
}
