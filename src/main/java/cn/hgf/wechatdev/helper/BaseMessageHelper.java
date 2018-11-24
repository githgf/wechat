package cn.hgf.wechatdev.helper;

import cn.hgf.wechatdev.common.constant.CommonParam;
import cn.hgf.wechatdev.model.BaseMessageInfo;
import cn.hgf.wechatdev.model.TextMessageInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BaseMessageHelper {
    public Object parseMessageInfo(JSONObject jsonObject){
        if (jsonObject == null)return null;

        String messageType = jsonObject.getString(CommonParam.MESSAGE_TYPE);
        if ("text".equals(messageType)){
            return parseTextMessageInfo(JSON.parseObject(jsonObject.toJSONString(),TextMessageInfo.class));
        }
        return null;
    }

    public BaseMessageInfo parseTextMessageInfo(TextMessageInfo textMessageInfo){
        String content = textMessageInfo.getContent();

        if (content.contains("123")){

//            BeanUtils.copyProperties(textMessageInfo,);
        }

        textMessageInfo.setContent("欢迎关注帆影ing公众号");
        return textMessageInfo;
    }
}
