package cn.hgf.wechatdev.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.io.Serializable;

/**
 *  基本的消息体,接收和响应公众号的请求
 */
//指定根节点的名字
@JacksonXmlRootElement(localName = "xml")
public class BaseMessageInfo implements Serializable {
    //如果是接收请求：开发者微信号
    //如果是响应请求：openID
    String ToUserName;
    //(接收请求)发送方(访问微信公众号客户)账号（openID）
    //(响应请求)开发者的微信号
    String FromUserName;
    //消息创建时间
    long CreateTime;
    //消息ID 64位
    long MsgId;
    //消息类型（text、image、voice..）
    String MsgType;
    @JacksonXmlProperty(localName = "ToUserName")
    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }
    @JacksonXmlProperty(localName = "FromUserName")
    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }
    @JacksonXmlProperty(localName = "CreateTime")
    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }
    @JacksonXmlProperty(localName = "MsgId")
    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
    @JacksonXmlProperty(localName = "MsgType")
    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

}
