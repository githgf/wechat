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

