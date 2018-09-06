package com.zhuoan.util.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * xml解析
 */
public class XMLParser {


    public static Map<String,Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {

        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is =  Util.getStringStream(xmlString);
        Document document = builder.parse(is);

        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, Object> map = new HashMap<String, Object>();
        int i=0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if(node instanceof Element){
                map.put(node.getNodeName(),node.getTextContent());
            }
            i++;
        }
        return map;

    }
    
    
	public static String getXMLFromMap(Map<String, String> map) {

		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			sb.append("<"+key+">");
			sb.append(map.get(key));
			sb.append("</"+key+">");
		}
		sb.append("</xml>");
		return sb.toString();
	} 


}