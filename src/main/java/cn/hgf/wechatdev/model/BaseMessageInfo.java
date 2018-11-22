package cn.hgf.wechatdev.model;


import java.io.Serializable;

/**
 *  基本的消息体
 */
public class BaseMessageInfo implements Serializable {
    //开发者微信号
    String ToUserName;
    //发送方(访问微信公众号客户)账号（openID）
    String FromUserName;
    //消息创建时间
    long CreateTime;
    //消息ID 64位
    int MsgId;
    //消息类型（text、image、voice..）
    String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public int getMsgId() {
        return MsgId;
    }

    public void setMsgId(int msgId) {
        MsgId = msgId;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
