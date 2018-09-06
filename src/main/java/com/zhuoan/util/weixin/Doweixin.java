package com.zhuoan.util.weixin;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

public class Doweixin {

    
	/**
	 * 获取用户授权code
	 * @return
	 */
	public static String getCodeURL(String redirect_url) {
		
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

		url = url.replace("APPID", Configure.getAppid())
				 .replace("REDIRECT_URI", urlEnodeUTF8(redirect_url))
				 .replace("SCOPE", Configure.SCOPE);
		return url;

	}
	
	/**
	 * 获取网页授权接口调用凭证access_token、openID
	 * @return
	 */
	public static JSONObject getAccessToken(String code) {

		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";

		url = url.replace("APPID", Configure.getAppid())
				 .replace("APPSECRET", Configure.appKey)
				 .replace("CODE", code);

		String result = HttpTookit.doGet(url, "UTF-8", false);
		
		JSONObject json = JSONObject.fromObject(result);

		return json;

	}
	
	/**
	 * 获取微信接口调用所需access_tocken
	 * @return
	 */
	public static JSONObject getAccessToken(){
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		
		url = url.replace("APPID", Configure.getAppid())
				 .replace("APPSECRET", Configure.appKey);
		
		String result = HttpTookit.doGet(url, "UTF-8", false);
		JSONObject json = JSONObject.fromObject(result);
		return json;
	}
	
	/**
	 * 获取用户个人信息（UnionID机制）
	 * @return
	 */
	public static JSONObject getUserInfo(String accessToken, String openID) {

		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

		url = url.replace("ACCESS_TOKEN", accessToken)
				 .replace("OPENID", openID);

		String result = HttpTookit.doGet(url, "UTF-8", false);
		
		JSONObject json = JSONObject.fromObject(result);

		return json;

	}
	
	/**
	 * 获取用户个人信息 (公众号接口)
	 * @return
	 */
	public static JSONObject getUserInfoWithoutOauth(String accessToken, String openID) {

		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

		url = url.replace("ACCESS_TOKEN", accessToken)
				 .replace("OPENID", openID);

		String result = HttpTookit.doGet(url, "UTF-8", false);
		
		JSONObject json = JSONObject.fromObject(result);

		return json;

	}

	
	/**
	 * URL编码设置（UTF-8）
	 * @param str
	 * @return
	 */
	public static String urlEnodeUTF8(String str) {

		String result = str;

		try {

			result = URLEncoder.encode(str, "UTF-8");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;

	}
}
