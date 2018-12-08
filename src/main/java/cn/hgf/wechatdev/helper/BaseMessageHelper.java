package cn.hgf.wechatdev.helper;

import cn.hgf.wechatdev.common.constant.CommonParam;
import cn.hgf.wechatdev.model.BaseMessageInfo;
import cn.hgf.wechatdev.model.ImageMessageInfo;
import cn.hgf.wechatdev.model.TextMessageInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BaseMessageHelper {
    public Object parseMessageInfo(JSONObject jsonObject){
        if (jsonObject == null)return null;

        String messageType = jsonObject.getString(CommonParam.MESSAGE_TYPE);

        BaseMessageInfo replyBaseMessageInfo = null;
        switch (messageType){
            case CommonParam.MESSAGE_TYPE_TEXT:
                replyBaseMessageInfo = parseTextMessageInfo(JSON.parseObject(jsonObject.toJSONString(),TextMessageInfo.class));
                break;
            case CommonParam.MESSAGE_TYPE_IMAGE:
                replyBaseMessageInfo = parseImageMessageInfo(JSON.parseObject(jsonObject.toJSONString(),ImageMessageInfo.class));
                break;
        }

        return replyBaseMessageInfo;
    }

    public BaseMessageInfo parseTextMessageInfo(TextMessageInfo textMessageInfo){
        String content = textMessageInfo.getContent();

        if (content.contains("123")){

//            BeanUtils.copyProperties(textMessageInfo,);
        }

        textMessageInfo.setContent("欢迎关注帆影ing公众号");
        return textMessageInfo;
    }

    public BaseMessageInfo parseImageMessageInfo(ImageMessageInfo imageMessageInfo){
        return replyImageMessageInfo("lEG_cZ7oTsGUSrGmlPDIJVtkJKZXAIDHhdLuD3jNSRA",imageMessageInfo.getToUserName(),imageMessageInfo.getFromUserName());
    }

    /**
     * 回复图片消息的格式
     * ToUserName	是	接收方帐号（收到的OpenID）
     * FromUserName	是	开发者微信号
     * CreateTime	是	消息创建时间 （整型）
     * MsgType	是	image
     * MediaId	是	通过素材管理中的接口上传多媒体文件，得到的id。
     *
     * <xml>
     * 	<ToUserName>< ![CDATA[toUser] ]></ToUserName>
     * 	<FromUserName>< ![CDATA[fromUser] ]></FromUserName>
     * 	<CreateTime>12345678</CreateTime>
     * 	<MsgType>< ![CDATA[image] ]></MsgType>
     * 	<Image>
     * 		<MediaId>< ![CDATA[media_id] ]></MediaId>
     * 	</Image>
     * </xml>
     *
     * @see     cn.hgf.wechatdev.model.ImageMessageInfo
     * @return
     */
    public ImageMessageInfo replyImageMessageInfo(String mediaId,String fromUser,String toUser){
        ImageMessageInfo imageMessageInfo = new ImageMessageInfo();
        imageMessageInfo.setImage(new ImageMessageInfo.Image(mediaId));
        imageMessageInfo.setFromUserName(fromUser);
        imageMessageInfo.setToUserName(toUser);
        imageMessageInfo.setCreateTime(new Date().getTime());
        imageMessageInfo.setMsgType(CommonParam.MESSAGE_TYPE_IMAGE);

        return imageMessageInfo;
    }

    /**
     * 回复文本消息的格式
     *
     * ToUserName	是	接收方帐号（收到的OpenID）
     * FromUserName	是	开发者微信号
     * CreateTime	是	消息创建时间 （整型）
     * MsgType	是	text
     * Content	是	回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     * @return
     */
    public TextMessageInfo replyTextMessageInfo(String content,String fromUser,String toUser){
        TextMessageInfo textMessageInfo = new TextMessageInfo();
        textMessageInfo.setFromUserName(fromUser);
        textMessageInfo.setToUserName(toUser);
        textMessageInfo.setContent(content);
        textMessageInfo.setMsgType(CommonParam.MESSAGE_TYPE_TEXT);
        textMessageInfo.setCreateTime(new Date().getTime());

        return textMessageInfo;
    }
}
