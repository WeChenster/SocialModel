package com.zhuoan.dto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.Random;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.zhuoan.util.TimeUtil;


/**
 * 系统常量bean
 * @Copyright Copyright (c) 2016
 * @Company zhouan
 * @version 0.1
 */
public class Dto {
	
	// basis start

	public static final String DOMAIN = "/dgb";
	
	public static final String REDIRECT = "redirect:/";

	public static final String PC_REDIRECT = "redirect:/work/pc/";

	public static final String MOBILE_REDIRECT = "redirect:/work/mobile/";

    public static final String MSG_CODE = "msgCode";

    public static int ALL_TRUE=1;//全局  是
	
	public static int ALL_FALSE=0;//全局   否
	
	public static String PAGEINDEX="";//当前页数
	
	public static String SECRET_KEY="zhoan";//全局加密密钥
	

	//平台基础数据 start：
	public static String PC_PAGE_PATH="pc";

	public static String MOBILE_PAGE_PATH="mobile";

	public static String PLAT_TYPE_NAME="playType"; //平台类型sessionKey

	public static String LOGIN_USER="loginUser";//登录用户sessionKey

	public static String LOGIN_ADMIN="loginAdmin"; //登录后台管理员
	
	public static String LOGIN_SYS="loginSys"; //基础数据session
	//平台基础数据 end
	
	
	//全局定义
	public static int FRIENDS_APPLE_TYPE_WAIT=-1;//好友处理类型：等待验证
	public static int FRIENDS_APPLE_TYPE_ADD=0;//好友处理类型：添加
	public static int FRIENDS_APPLE_TYPE_ACCEPT=1;//好友处理类型：接受
	public static int FRIENDS_APPLE_TYPE_ADDED=2;//好友处理类型：已添加

	public static int USER_COMMENT_TYPE_PRAISE=0;//评论表类型：点赞
	public static int USER_COMMENT_TYPE_COM=1;//评论表类型：评论
	public static int USER_COMMENT_TYPE_FORWRAD=2;//评论表类型：转发

	
	//end全局定义

	
	
	
    //登陆跳转  start 
	
	public static final String LOGIN_USER_SEND = "users/userIndex.html";
	public static final String LOGIN_SHOP_SEND = "shop/shopIndex.html";
	
	//end 跳转结束
	
	//自动登录信息 start
	public static String  REM_LOGIN_USERNAME="remloginusername";
	public static String  REM_LOGIN_PASSWORD="remloginpassword";
	
	public static String USER_TOKEN="usertoken";
	//自动登录信息 end 
	
	
	//地区选择 start
	
	public static final String ACCESS_ADDRESS="access_address";//当前访问地址
	//地区选择 end
	
	

	//微信支付 start
	public static String MONEY_OF_CHARGE="charge_money";//支付金额
	public static String CALL_BACK_URL="call_back_url";//回调地址
	
	public static String ARRAY_SHOPID="array_shopid";//存付款订单ID
	
	public static int CHARGE_DONE=0;//充值成功
	public static int CHARGE_UNPAY=1;//未支付
	
	public static String getCharge_stata(String type) {
		return (CHARGE_DONE+"").equals(type) ? "充值成功" : "未支付";
	}
	//微信支付 end
	
	//微信登录 start
	public static String WEIXIN_USER_OPENID="user_openid";//存储当前用户的微信openid
	public static String USER_WEIXIN_INFO="user_weixin_info";//用户微信信息
	//微信登录 end
	
	
	//日志私有对象
	private static Logger log;
	

	
	/**
	 * 生成订单编号
	 * @return orderCode
	 */
	public static String getorderCode(){
		String Time=TimeUtil.getNowDate("yyyyMMddHHmmSS");
		String str = "";
			str += (int)(Math.random()*9+1);
			for(int i = 0; i < 5; i++){
			str += (int)(Math.random()*10);
		}
		String uporderCode=Time+str;
		return uporderCode;
	}
	
	/**
	 * 根据长度随机生成
	 * 26英文字和0~9随机生成
	 * @param length
	 * @return entNum
	 */
	public static String getEntNumCode(int length){
		String val = "";  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            // 输出字母还是数字  
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";   
            // 字符串  
            if ("char".equalsIgnoreCase(charOrNum)) {  
                // 取得大写字母还是小写字母  
                int choice = 65;   
                val += (char) (choice + random.nextInt(26));  
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
       return val;
	}
	/**
	 * 根据传入平台类型返回页面目录
	 * @param type 0：pc 1：移动
	 * @return
	 */
	public static String getPagePath(int type){
		return type==0?PC_PAGE_PATH:MOBILE_PAGE_PATH;
	}
	

	/**
	 * ajax 返回
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	public static void printMsg(HttpServletResponse response, String msg) throws IOException{
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		response.getWriter().write(msg);
	}
	
	/**
	 * 获得本机IP
	 * @param request
	 * @return
	 * @throws UnknownHostException 
	 */
	public static String getLocalIp(HttpServletRequest request) throws UnknownHostException{
		return request.getRemoteAddr();
	}
	
	/**
	 * 接收远程传递的post消息
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static String receivePostMsg(ServletInputStream inputStream) throws IOException{
		BufferedReader input=new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		String line=null;
		String result = "";
		try {
			while((line=input.readLine())!=null)
				result+=line+"\r\n";
		}catch(Exception e){
			e.printStackTrace();
		}finally{input.close();}
		
		return result;
	}
	
	/**
	 * 输出日志
	 * @param msg
	 */
	public static void writeLog(String msg){
		log=Logger.getLogger(Dto.class);
		log.info(msg);
	}
		
	
	/**
	 * String 转为 JSONArray
	 * @param msg
	 */
	public static String string_Array(String key,String values){
		
		if(values==null){
			return null;
		}
		
		JSONArray array=new JSONArray();
		
		JSONObject data=new JSONObject();
		
		data.element(key, values);
		
		array.add(data);
		
		return array.toString();
	}
	
	
	
	/**
	 * 判断String是否为空
	 * @param msg
	 * @return true or false (等于空返回true   不等于看返回false)
	 */
	public static boolean stringIsNULL(String values){
		
		if(values==null || "".equals(values) || "null".equals(values) || "undefined".equals(values)){
			return true;
		}
		return false;
		
	}	

	/**
	 * 限制String长度
	 * @param progress
	 * @return
	 */
	public static String StringSubstring(String values,int num){
		
		if(!Dto.stringIsNULL(values)){
			
			if(values.length()>=num){
				values = values.substring(0, num);
				return values+"...";
			}
		}
		return "";
		
		
	}
	
	/**
	 * 限制String长度(针对JSONArray)
	 * @param progress
	 * @return
	 */
	public static JSONArray StringSub_Array(JSONArray array,String key,int num){
		
		for(int i=0;i<array.size();i++)
		{
			JSONObject tmpobj=array.getJSONObject(i);
			if(("null").equals(tmpobj.getString(key)))
				continue;
			else{
				String value=tmpobj.getString(key);
				
				if(!Dto.stringIsNULL(value)){
					
					if(value.length()>=num){
						value = value.substring(0, num);
						tmpobj.element(key, value+"...");
					}
				}
				
			}
		}
		return array;
	}
	
	//范围内的随机数double
    public static double nextDouble(double min,double max) throws Exception {
    	 
        if (max < min) {
            throw new Exception("min < max");
        }
        if (min == max) {
            return min;
        }
        double temp = min + ((max - min) * new Random().nextDouble());
        BigDecimal   b   =   new   BigDecimal(temp);
        return b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
    }
    
    /**
	 * 下载文件
	 * @param path
	 * @param response
	 */
	public static void printFileStream(String path,HttpServletResponse response){
		try{
			File file = new File(path);
			String filename = file.getName();

	        // 以流的形式下载文件。
	        InputStream fis = new BufferedInputStream(new FileInputStream(path));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        // 清空response
	        response.reset();
	        // 设置response的Header
	        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
	        response.addHeader("Content-Length", "" + file.length());
	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	        response.setContentType("application/octet-stream");
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close();
		}catch(Exception e){e.printStackTrace();}
	}
	
}
