package cn.hgf.wechatdev.common.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.ClientResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class BaseApi {
    public <T> Object getJsonObjectData(ClientResponse clientResponse, Class<T> clazz){

        String result = getJSONStr(clientResponse);
        if (!StringUtils.isEmpty(result)){
            return JSON.parseObject(result, clazz);
        }
        return null;
    }

    public JSONObject getJsonObjectData(ClientResponse clientResponse){

        String result = getJSONStr(clientResponse);
        if (!StringUtils.isEmpty(result)){
            return JSON.parseObject(result);
        }
        return null;
    }

    public <T> List<T> getJsonArrayData(ClientResponse clientResponse, Class<T> clazz){

        String result = getJSONStr(clientResponse);
        if (!StringUtils.isEmpty(result)){
            return JSON.parseArray(result, clazz);
        }
        return null;
    }

    public JSONArray getJsonArrayData(ClientResponse clientResponse){

        String result = getJSONStr(clientResponse);
        if (!StringUtils.isEmpty(result)){
            return JSON.parseArray(result);
        }
        return null;
    }


    public String getJSONStr(ClientResponse clientResponse){

        if (clientResponse != null && !StringUtils.isEmpty(clientResponse.getEntity(String.class)) ){
            return clientResponse.getEntity(String.class);
        }

        return null;
    }
}
