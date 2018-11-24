package cn.hgf.wechatdev.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParseHandler extends DefaultHandler {
    JSONObject resultObj;
    String currentTag;
    String currentValue;


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        currentValue = new String(ch, start, length);
        if (!StringUtils.isEmpty(currentValue.trim()) && !"\n".equals(currentValue.trim()) && !StringUtils.isEmpty(currentTag)){
            resultObj.put(currentTag,currentValue);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        resultObj = new JSONObject();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        currentTag = qName;
    }

    public JSONObject getResultObj(){
        return resultObj;
    }
}
