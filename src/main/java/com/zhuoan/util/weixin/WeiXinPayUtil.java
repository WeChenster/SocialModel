package com.zhuoan.util.weixin;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhuoan.dto.Dto;
import com.zhuoan.util.MathDelUtil;



import net.sf.json.JSONObject;

/**
 * 微信支付工具类
 * @Copyright Copyright (c) 2015
 * @Company zhouan
 * @author wph
 * @version 0.1
 */
public class WeiXinPayUtil {

	/**
	 * 生成二维码支付链接
	 * @param orderCode
	 * @return
	 * @throws UnknownHostException 
	 */
	public static String createXMLData(JSONObject chargeRec,HttpServletRequest request,String payTpe) throws UnknownHostException
	{
		//0.组织参与加密的参数
		Map<String,String> map=new HashMap<String, String>();
		map.put("trade_type", payTpe);
		map.put("spbill_create_ip", Dto.getLocalIp(request));
		map.put("product_id", MathDelUtil.getRandomStr(10));
		map.put("body", "选料宝订单支付，金额:"+chargeRec.getString("money")+"元");
		map.put("out_trade_no", chargeRec.getString("code"));
		map.put("total_fee", getMoney(chargeRec.getString("money")));
		map.put("notify_url",Configure.PAY_NOTIFY_URL);
		map.put("appid", Configure.getAppid());
		map.put("mch_id", Configure.getMchid());	
		map.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
		map.put("openid", chargeRec.getString("openid"));
		map.put("sign",Signature.getSign(map));
		
		//1.组织XMl数据
		String xmlData=XMLParser.getXMLFromMap(map);

		return xmlData;
	}
	
	public static String createQueryXMLData(String orderCode,HttpServletRequest request){
		//0.组织参与加密的参数
		Map<String,String> map=new HashMap<String, String>();
		map.put("appid", Configure.getAppid());
		map.put("mch_id", Configure.getMchid());
		map.put("out_trade_no", orderCode);
		map.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
		map.put("sign",Signature.getSign(map));
		
		//1.组织XMl数据
		String xmlData=XMLParser.getXMLFromMap(map);

		return xmlData;
	}
	
	/**
	 * 组织app支付所需参数
	 * @param chargeRec
	 * @param request
	 * @return
	 */
	public static String createAppXMLData(JSONObject chargeRec,HttpServletRequest request){
		//0.组织参与加密的参数
		Map<String,String> map=new HashMap<String, String>();
		map.put("body","选料宝订单支付，金额:"+chargeRec.getString("money")+"元");
		map.put("out_trade_no",chargeRec.getString("code"));
		map.put("total_fee",String.valueOf(chargeRec.getString("money")));
		map.put("spbill_create_ip",request.getRemoteAddr());
		map.put("appid", Configure.getOpenPlatID());
		map.put("mch_id", Configure.getMchid());
		map.put("notify_url", Configure.PAY_NOTIFY_URL);
		map.put("nonce_str", RandomStringGenerator.getRandomStringByLength(16));
		map.put("total_fee", getMoney(chargeRec.getString("money")));
		map.put("trade_type","APP");
		map.put("sign", Signature.getSign(map));
		
		//1.组织XMl数据
		String xmlStr = XMLParser.getXMLFromMap(map);
		return xmlStr;
	}
	
	/**
	 * 元转换成分
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if(amount==null){
			return "";
		}
		// 金额转化为分为单位
		String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
        int index = currency.indexOf(".");  
        int length = currency.length();  
        Long amLong = 0l;  
        if(index == -1){  
            amLong = Long.valueOf(currency+"00");  
        }else if(length - index >= 3){  
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
        }else if(length - index == 2){  
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
        }else{  
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
        }  
        return amLong.toString(); 
	}
}
