package cn.hgf.wechatdev.common.util;

import cn.hgf.wechatdev.common.constant.CommonParam;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignatureUtil {

    public static  boolean checkSignature(String timestamp,String nonce,String signature){

        List<String> checkList = new ArrayList<>();
        checkList.add(CommonParam.WECHAT_SERVER_KEY);
        checkList.add(nonce);
        checkList.add(timestamp);
        Collections.sort(checkList);
        String sha1Hex = DigestUtils.sha1Hex(checkList.get(0) + checkList.get(1) + checkList.get(2));
        if (signature.equals(sha1Hex)){
            return true;
        }
        return false;
    }
    public static void main(String[] args){

        checkSignature("1542885977","1722755572","d836e81fd71e7f7e8312ce900eebef64749591c0");

    }

}
