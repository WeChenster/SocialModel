package com.zhuoan.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;


public class ShortMessageUtil {
	/***
	 * 发送短信
	 * @param tel 手机号码
	 * @param request 验证码
	 * @return 发送状态      0>>>成功
	 * @throws UnsupportedEncodingException 
	 * */
	public static String getCode(String tel,HttpServletRequest request) throws UnsupportedEncodingException {
		Random rd=new Random();
		int co = rd.nextInt(899999)+100000;
	    request.getSession().setAttribute("msgCode", co);
	    System.out.println(co);
	    String msg=String.format("【掌上保姆社区】验证码：%s。验证码很重要，请勿泄露给他人。",co);
		String url = String.format("http://182.254.141.209:8888/Modules/Interface/http/Iservicesbsjy.aspx?flag=sendsms&loginname=zhoan&password=zhoan10086&p=%s&c=%s&d=2012-07-27",
						tel,URLEncoder.encode(msg,"utf-8") );
		System.out.println(url);
		String returnStr = HttpReqUtil.doGet(url, "", "utf-8");
		System.out.println(returnStr);
		return returnStr.substring(0,1);
//		return "0";
	}
	
	/***
	 * 验证短信
	 * @param code 验证码
	 * @return 验证状态      0>>>成功
	 * */
	public static String Contrast(String code, HttpServletRequest request){
		Object ConCode=request.getSession().getAttribute("msgCode");
//		String ConCode = request.getSession().getAttribute("msgCode").toString();
		if(ConCode!=null && code.equals(ConCode.toString())){
			return "0";
		}
		return "1";
	}

	/**
	 * 发送密码短信
	 * @param sendMsg
	 * @param tel
	 * @throws UnsupportedEncodingException 
	 */
	public static void sendMessage(String sendMsg,String tel) throws UnsupportedEncodingException{
		String url = String.format("http://182.254.141.209:8888/Modules/Interface/http/Iservicesbsjy.aspx?flag=sendsms&loginname=zhoan&password=zhoan10086&p=%s&c=%s&d=2012-07-27",
				tel,URLEncoder.encode(sendMsg,"utf-8") );
		System.out.print(url+"\n");
//		String code=HttpReqUtil.doGet(url, "", "utf-8");
//		System.out.print(code+"\n");
	}
}
