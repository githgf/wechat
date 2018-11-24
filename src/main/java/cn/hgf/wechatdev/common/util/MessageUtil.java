package cn.hgf.wechatdev.common.util;

import com.alibaba.fastjson.JSONObject;
import org.xml.sax.SAXException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 *  消息转换类
 */

public class MessageUtil {
    public static final SAXParserFactory SAX_PARSER_FACTORY = SAXParserFactory.newInstance();

    /**
     *  sax 将请求转为 xml
     *      sax 解析适合读xml，速度快，小巧
     * @param request
     * @return
     */
    public static JSONObject xmlToMap(HttpServletRequest request){
        if (request == null)return null;
        try {
            return xmlToMap(request.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject xmlToMap(InputStream inputStream){
        if (inputStream == null)return null;
        try {
            SAXParser saxParser = SAX_PARSER_FACTORY.newSAXParser();
            XmlParseHandler xmlParseHandler = new XmlParseHandler();
            saxParser.parse(inputStream,xmlParseHandler);

            return xmlParseHandler.getResultObj();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
