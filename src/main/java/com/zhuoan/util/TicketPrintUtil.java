package com.zhuoan.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 小票打印工具类
 * @author daixinmei
 *
 */
public class TicketPrintUtil{
	
	/**
	 * 通过云端打印订单
	 * @param orderMsg
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static JSONObject printTickeyByCloud(JSONObject orderMsg,JSONObject deviceMsg) throws UnsupportedEncodingException{
		
		//0.组织数据
		StringBuffer content=new StringBuffer();
		content.append("^N1^F1\n");
		content.append("^B3 掌上保姆\n");
		content.append("^B2 "+orderMsg.getString("shop_name")+"\n");
		content.append("*******************************\n");
		
		content.append(orderMsg.getString("orderCode")+"\n");
		content.append("收货人："+orderMsg.getString("recName")+"\n");
		content.append("电话："+orderMsg.getString("recTel")+"\n");
		content.append("地址："+orderMsg.getString("address")+"\n");
		content.append("下单时间："+orderMsg.getString("createTime")+"\n");
		content.append("支付方式："+orderMsg.getString("memo")+"\n");
		content.append("*******************************\n");
		
		content.append("名称　　　　       单价 数量 \n");
		JSONArray itemList=orderMsg.getJSONArray("detList");
		for(int i=0;i<itemList.size();i++)
		{
			JSONObject tmpObj=itemList.getJSONObject(i);
			content.append(tmpObj.getString("name")+"\n");
			content.append("　　　　           "+tmpObj.getDouble("price")+"   "+tmpObj.getInt("count")+"\n");
		}
		content.append("*******************************\n");
		
		content.append("共计："+orderMsg.getDouble("totalPrice")+"元\n");
		
		content.append("足不出户轻松购物\n");
		
		char ascStr=(char) (orderMsg.getString("shop_des").length());
		content.append("^Q"+ascStr+orderMsg.getString("shop_des"));
		
		//1.打印
		return JSONObject.fromObject(doPrint(content.toString(),deviceMsg));
	}
	
	private static String doPrint(String content,JSONObject deviceMsg) throws UnsupportedEncodingException{
		Map<String,String> param=new HashMap<String,String>();
//		param.put("deviceNo", "kdt1023142");
//		param.put("key", "f0761");
//		param.put("times", "1");
		param.put("deviceNo", deviceMsg.getString("deNo"));
		param.put("key", deviceMsg.getString("deKey"));
		param.put("times", "1");
		param.put("printContent", content);
		String result=HttpReqUtil.doPost("http://open.printcenter.cn:8080/addOrder", param, null, "utf-8");
		return result;
	} 
}
