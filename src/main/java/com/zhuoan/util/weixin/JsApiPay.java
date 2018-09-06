package com.zhuoan.util.weixin;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.zhuoan.util.weixin.Configure;
import com.zhuoan.util.weixin.RandomStringGenerator;
import com.zhuoan.util.weixin.Signature;


public class JsApiPay {

	public static String getJsApiParameters(String prepay_id) {
		
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String nonceStr = RandomStringGenerator.getRandomStringByLength(32);
		Map<String, String> map = new HashMap<String, String>();
		String packages = "prepay_id="+prepay_id;
		map.put("appId", Configure.getAppid());  
		map.put("timeStamp", timestamp);  
		map.put("nonceStr", nonceStr);  
		map.put("package", packages);  
		map.put("signType", "MD5");
		
		String sign = Signature.getSign(map);
		
		String jsonStr = "{\"appId\":\"" + Configure.getAppid() + "\",\"timeStamp\":\"" + timestamp
				+ "\",\"nonceStr\":\"" + nonceStr + "\",\"package\":\""
				+ packages + "\",\"signType\":\"MD5" + "\",\"paySign\":\""
				+ sign + "\"}";
		
		return jsonStr;
	}
	
	/**
	 * 组织app支付所需参数
	 * @param prepay_id
	 * @return
	 */
	public String getAppJsApiParameters(String prepay_id)
	{
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String nonceStr = RandomStringGenerator.getRandomStringByLength(16);
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", Configure.getAppid());  
		map.put("partnerid", Configure.getMchid());
		map.put("package", "Sign=WXPay");  
		map.put("noncestr", nonceStr);  
		map.put("timestamp", timestamp);  
		map.put("prepayid", prepay_id);
		
		String sign = Signature.getSign(map);
		
		String jsonStr=new JSONObject().element("appid", Configure.getAppid())
									   .element("partnerid", Configure.getMchid())
									   .element("package", "Sign=WXPay")
									   .element("noncestr", nonceStr)
									   .element("timestamp", timestamp)
									   .element("prepayid", prepay_id)
									   .element("sign", sign).toString();
		
		return jsonStr;
	}
}
