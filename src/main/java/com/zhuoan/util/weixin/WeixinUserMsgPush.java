package com.zhuoan.util.weixin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;


import com.zhuoan.util.HttpReqUtil;

import net.sf.json.JSONObject;

/**
 * 微信公众号消息推送
 * @Copyright Copyright (c) 2015
 * @Company zhouan
 * @author wph
 * @version 0.1
 */
public class WeixinUserMsgPush {

	/**
	 * 向用户推送消息
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static JSONObject pushMsgToUser(JSONObject data) throws UnsupportedEncodingException{
		if(data!=null){
			String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
			url=url.replace("ACCESS_TOKEN", Doweixin.getAccessToken().getString("access_token"));
			String backResult=HttpReqUtil.doPost(url, null, data.toString(), "utf-8");
			return JSONObject.fromObject(backResult);
		}else
			return null;
	}
	
	/**
	 * 创建菜单
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static JSONObject createWXMenu(JSONObject data) throws UnsupportedEncodingException{
		if(data!=null){
			String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
			url=url.replace("ACCESS_TOKEN", Doweixin.getAccessToken().getString("access_token"));
			String backResult=HttpReqUtil.doPost(url, null, data.toString(), "utf-8");
			return JSONObject.fromObject(backResult);
		}else
			return null;
	}
	
	/**
	 * 获得已有的菜单
	 * @param data
	 * @return
	 */
	public static JSONObject getWxMenuByApi(JSONObject data){
		if(data!=null){
			String url="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
			url=url.replace("ACCESS_TOKEN", Doweixin.getAccessToken().getString("access_token"));
			String backResult=HttpReqUtil.doGet(url, "", "UTF-8");
			return JSONObject.fromObject(backResult);
		}else
			return null;
	}
	
	/**
	 * 删除微信菜单
	 * @param data
	 * @return
	 */
	public static JSONObject deleteWxMenuByApi(JSONObject data){
		if(data!=null){
			String url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
			url=url.replace("ACCESS_TOKEN", Doweixin.getAccessToken().getString("access_token"));
			String backResult=HttpReqUtil.doGet(url, "", "UTF-8");
			return JSONObject.fromObject(backResult);
		}else
			return null;
	}
	
	/**
	 * 获得带参数二维码的ticket
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static JSONObject getTicket(JSONObject data) throws UnsupportedEncodingException{
		String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		url=url.replace("ACCESS_TOKEN", Doweixin.getAccessToken().getString("access_token"));
		String backResult=HttpReqUtil.doPost(url, null, data.toString(), "utf-8");
		return JSONObject.fromObject(backResult);
	}
	
	/**
	 * 通过ticket获得二维码图片
	 * @param ticket
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws MalformedURLException 
	 */
	public static String createQrCode(HttpServletRequest request,String ticket,String fileName) throws UnsupportedEncodingException, MalformedURLException{
		//0.获得项目真实路径
		String sRootPath=request.getSession().getServletContext().getRealPath("");
		String sBasePath=sRootPath+"/upload/img/qrcode/";//获得存储根路径
		String savePath="/upload/img/qrcode/"+fileName;
		
		//1.从远程地址读入数据
		String remoteUrl="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+
						  URLEncoder.encode(ticket, "utf-8");
		URL url = new URL(remoteUrl);
		
		try {
			//读取数据
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			
			//输出数据
			FileOutputStream fos=new FileOutputStream(new File(sBasePath+fileName));
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) > 0)
				fos.write(buffer, 0, len);
			fos.flush();
	    	fos.close();
	    	
	    	return savePath;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	} 
}
