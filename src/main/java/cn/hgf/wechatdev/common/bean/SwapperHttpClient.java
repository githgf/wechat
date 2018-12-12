package cn.hgf.wechatdev.common.bean;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class SwapperHttpClient {
    private HttpClient httpClient;
    private HttpRequestBase httpRequestBase;
    private String url;
    private JSONObject requestParam;

    public SwapperHttpClient(String url) {
        this.url = url;
        init();
    }

    private void init(){
        this.httpClient = HttpClientBuilder.create().build();
    }

    public SwapperHttpClient get(){
        httpRequestBase = new HttpGet(this.url);
        return this;
    }

    public SwapperHttpClient post(){
        httpRequestBase = new HttpPost(this.url);
        return this;
    }

    public SwapperHttpClient addHeader(String key,String value){
        httpRequestBase.addHeader(key, value);
        return this;
    }

    public SwapperHttpClient addRequestParam(String key,Object value){
        if (requestParam == null)requestParam = new JSONObject();

        requestParam.put(key,value);
        return this;
    }

    public HttpResponse execute(HttpEntity httpEntity){
        if (httpClient instanceof HttpEntityEnclosingRequestBase){
            ((HttpEntityEnclosingRequestBase) httpClient).setEntity(httpEntity);
        }

        try {
            return httpClient.execute(httpRequestBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpResponse execute(){
        try {
            if (((HttpEntityEnclosingRequestBase) httpClient) != null && requestParam != null){
                StringEntity stringEntity = new StringEntity(requestParam.toString());
                ((HttpEntityEnclosingRequestBase) httpClient).setEntity(stringEntity);

            }
            return httpClient.execute(httpRequestBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
