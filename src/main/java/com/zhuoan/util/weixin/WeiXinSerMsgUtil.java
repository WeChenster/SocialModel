package com.zhuoan.util.weixin;

import java.io.UnsupportedEncodingException;

import com.zhuoan.util.HttpReqUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 微信客服消息工具类
 * @Copyright Copyright (c) 2015
 * @Company zhouan
 * @author wph
 * @version 0.1
 */
public class WeiXinSerMsgUtil {
	
	/**
	 * 根据openID列表群发消息
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject pushGroupMsg(JSONArray openIdList,String pushMsg) throws UnsupportedEncodingException{
		String url="https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
		url=url.replace("ACCESS_TOKEN", Doweixin.getAccessToken().getString("access_token"));
		
		//0.组织数据
		JSONObject data=new JSONObject();
		data.element("touser", openIdList).element("msgtype", "text")
			.element("text", (new JSONObject()).element("content", pushMsg));
		
		//1.调用接口  执行操作
		String backResult=HttpReqUtil.doPost(url, null, data.toString(), "utf-8");
		return JSONObject.fromObject(backResult);
	}
}
