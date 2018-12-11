package cn.hgf.wechatdev.common.api;

import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

@Component
public class WeixinApi extends BaseApi{

    @Value("${weixin.api.host}")
    private String weixinHost;

    private String token = "16_kWqnCO8yOiIvbaIX2nbA79XBuIgeM2y-8JYZG4VK-joJzMBoaR1fHrs5PUuYjBRP2Zo4z-CTsfz1ZKiGGbnLe_1uMqPu8v65sElbU5jR9F2g5jksRVZlWnRnhLNs_A8rt1hu9AMfCgatJ1uHAAZfAFASSR";

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

    public JSONObject getArticleImageUrl(File file){
        if (!file.exists())return null;
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        formDataMultiPart.bodyPart(new FileDataBodyPart("media",file));
        /*Client client = Client.create();
        client.setConnectTimeout(10000);

        ClientResponse clientResponse = client.resource(weixinHost + "cgi-bin/media/uploadimg?access_token=" + token)
                                            .type(MediaType.APPLICATION_FORM_URLENCODED)
                                            .post(ClientResponse.class, formDataMultiPart);*/

        javax.ws.rs.client.Client client = ClientBuilder.newClient();
        Response response = client.target(weixinHost + "cgi-bin/media/uploadimg?access_token=" + token)
                .request()
                .post(Entity.entity(formDataMultiPart, formDataMultiPart.getMediaType()));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("entity",response);

        return jsonObject;
    }
    public static void main(String[] args){
        File file = new File("/Users/linsen/windWork/wechat_picture/dockerfile_03.png");
        System.out.println(file.exists());
    }
}
