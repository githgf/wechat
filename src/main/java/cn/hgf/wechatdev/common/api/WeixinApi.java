package cn.hgf.wechatdev.common.api;

import cn.hgf.wechatdev.common.bean.SwapperHttpClient;
import cn.hgf.wechatdev.common.constant.CommonParam;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class WeixinApi extends BaseApi{

    @Value("${weixin.api.host}")
    private String weixinHost;

    private String token = "16_mKrHPWwHHqySnD0Oo-NukKCy0pp9IDG2haJxnNCZbDZCkoGMWFcjZk6GN9oeautW6opo0t5aNvDglipCcs3DA5tg-79tl-JnLO6xW3RndZ0oIqCOOj5HApAAQdf2BmqbmxIde1fKI8-mIT8oDQOjAIAUHF";

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

    public JSONObject getMediaList(String type,Integer offest,Integer count){
        /*CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost =  new HttpPost(weixinHost + "cgi-bin/material/batchget_material?access_token=" + token);
        httpPost.setConfig(getRequestConfig());

        JSONObject requestParam = new JSONObject();
        requestParam.put("type", type);
        requestParam.put("offest",offest != null ? offest : 0);
        requestParam.put("count",count != null ? count : 20);

        CloseableHttpResponse httpResponse = null;
        try {
            httpPost.setEntity(new StringEntity(requestParam.toJSONString()));
            httpResponse = httpClient.execute(httpPost);

            return getJsonObjectData(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        HttpResponse execute = new SwapperHttpClient(weixinHost + "cgi-bin/material/batchget_material?access_token=" + token)
                                    .addRequestParam("type", type)
                                    .addRequestParam("offest", offest != null ? offest : 0)
                                    .addRequestParam("count", count != null ? count : 20)
                                    .post()
                                    .execute();
        return getJsonObjectData(execute);

    }

    /**
     *  获取图文素材中图片的url
     */
    public JSONObject getArticleImageUrl(File file){
        if (!file.exists())return null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(weixinHost + "cgi-bin/media/uploadimg?access_token=" + token);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setSocketTimeout(20000).build();
        httpPost.setConfig(requestConfig);

        FileBody fileBody = new FileBody(file);
        HttpEntity media = MultipartEntityBuilder.create().addPart("media", fileBody).build();
        httpPost.setEntity(media);

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null && httpResponse.getEntity() != null){
                HttpEntity entity = httpResponse.getEntity();
                String s = EntityUtils.toString(entity);
                return JSON.parseObject(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
