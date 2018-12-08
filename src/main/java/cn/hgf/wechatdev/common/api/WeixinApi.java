package cn.hgf.wechatdev.common.api;

import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

@Component
public class WeixinApi extends BaseApi{

    @Value("${weixin.api.host}")
    private String weixinHost;

    /**
     * @param appId		第三方用户唯一凭证
     * @param secret	第三方用户唯一凭证密钥，即appsecret
     * @return
     */
    public JSONObject getAccessToken(String appId,String secret){
        Client client = Client.create();
        client.setConnectTimeout(1000);
        //grant_type	是	获取access_token填写client_credential
        String urlStr = String.format("%s/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",weixinHost,appId,secret);
        ClientResponse clientResponse = client.resource(urlStr)
                                              .type(MediaType.APPLICATION_JSON_TYPE)
                                              .get(ClientResponse.class);

        return getJsonObjectData(clientResponse);
    }
}
