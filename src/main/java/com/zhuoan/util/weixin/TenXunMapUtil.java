package com.zhuoan.util.weixin;

import com.zhuoan.util.HttpReqUtil;

import net.sf.json.JSONObject;

/**
 * 腾讯地图weserviceApi工具类
 * @Copyright Copyright (c) 2015
 * @Company zhouan
 * @author WPH
 * @version 0.1
 */
public class TenXunMapUtil {

	/**
	 * 根据经纬度定位当前坐标
	 * @param lonStr
	 * @return
	 */
	public static JSONObject getMyPosByLon(String lonStr){
		String url="http://apis.map.qq.com/ws/geocoder/v1/?location=%s&get_poi=1&key=%s";
		url=String.format(url, lonStr,Configure.TENCENT_MAP_KEY);
		String result=HttpReqUtil.doGet(url, "", "utf-8");
		
		return JSONObject.fromObject(result);
	}
	
	/**
	 * 坐标转换
	 * @param lanList
	 * @param type
	 * @return
	 */
	public static JSONObject transLan(String[] lanList,int type){
		String url="http://apis.map.qq.com/ws/coord/v1/translate?locations=%s&type=%d&key=%s";
		String lanStr="";
		for(String str:lanList){
			lanStr+=str+";";
		}
		lanStr=lanStr.substring(0, lanStr.length() - 1);
		url=String.format(url, lanStr,type,Configure.TENCENT_MAP_KEY);
		String result=HttpReqUtil.doGet(url, "", "utf-8");
		
		return JSONObject.fromObject(result);
	}
}
