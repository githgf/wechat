package cn.hgf.wechatdev.model;

import cn.hgf.wechatdev.common.constant.CommonParam;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

//指定根节点的名字
@JacksonXmlRootElement(localName = "xml")
public class TextMessageInfo extends BaseMessageInfo{
    String Content;

    @JacksonXmlProperty(localName = "Content")
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public TextMessageInfo replySuccess(TextMessageInfo textMessageInfo){
        textMessageInfo.setContent(CommonParam.WECHAT_REPLY_TEXT_SUCCESS);
        return textMessageInfo;
    }
}

class ImageMessageInfo extends BaseMessageInfo{
    /** 图片链接（由系统生成）*/
    String PicUrl;
    /** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。*/
    String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}