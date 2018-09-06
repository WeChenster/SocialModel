package com.zhuoan.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字处理工具类
 * @Copyright Copyright (c) 2015
 * @Company zhouan
 * @change wph
 * @version 0.1
 */
public class MathDelUtil {
	
	/**
	 * 检查是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		if(str==null) return false;
		Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*"); 
	    Matcher isNum = pattern.matcher(str);
	    if( !isNum.matches() ){
	       return false; 
	    } 
	    return true; 
	}

	/**
	 * 获得指定位数的字符串，不够的补0
	 * @param num
	 * @param scale
	 * @param isAddByTop
	 * @return
	 */
	public static String halfUpToStr(Double num,int scale,boolean isAddByTop)
	{
		BigDecimal b =new BigDecimal(num); 
		double value=b.setScale(scale,   BigDecimal.ROUND_HALF_UP).doubleValue();
		String valueStr=String.valueOf(value);
		int scaleSet=valueStr.split("\\.")[1].length();
		
		if(scaleSet<scale){
			for(int i=0;i<scale - scaleSet;i++){
				if(isAddByTop)
					valueStr="0"+valueStr;
				else
					valueStr+="0";
			}		
		}
		
		return valueStr;
	}
	
	/**
	 * 将数字转化为指定位数字符串，不够在前面补0
	 * @param num
	 * @param pos
	 * @return
	 */
	public static String transferNumToStr(int num,int pos){
		String valueStr=String.valueOf(num);
		if(valueStr.length()<pos){
			for(int i=0;i<pos - valueStr.length();i++)
				valueStr="0"+valueStr;
		}
		return valueStr;
	}
	
	/**
	 * double类型四舍五入运算
	 * @param num
	 * @return
	 */
	public static double halfUp(Double num,int scale){
		BigDecimal b =new BigDecimal(num); 
		return b.setScale(scale,   BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 保留若干位小数
	 * @param num
	 * @param scale
	 * @return
	 */
	public static double halfUpWithSmall(Double num,int scale){
		String format="#.";
		for(int i=0;i<scale;i++)
			format+="#";
		DecimalFormat df=new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.DOWN);
		String priceStr=df.format(num);
		return Double.valueOf(priceStr);
	}
	
	/**
	 * 保留若干位小数
	 * @param num
	 * @param scale
	 * @return
	 */
	public static double halfUpWithLarge(Double num,int scale){
		String format="#.";
		for(int i=0;i<scale;i++)
			format+="#";
		DecimalFormat df=new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.UP);
		String priceStr=df.format(num);
		return Double.valueOf(priceStr);
	}
	
	/**
	 * 获得任意位数随机数
	 */
	public static String getRandomStr(int count)
	{
		String str="";
		for(int i=0;i<count;i++)
		{
			str+=(int)(10*(Math.random()));
		}
		return str;
	}
	
	/**
	 * 获得两个经纬度坐标间的距离
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double getDistanceOfTwoPos(Double lat1,Double lon1,Double lat2,Double lon2)
	{
		double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lon1) - Math.toRadians(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;
        return s;
	}
	
	/**
	 * 二进制转十六进制
	 * @param bString
	 * @return
	 */
	public static String binaryString2hexString(String bString)  
    {  
        if (bString == null || bString.equals("") || bString.length() % 8 != 0)  
            return null;  
        StringBuffer tmp = new StringBuffer();  
        int iTmp = 0;  
        for (int i = 0; i < bString.length(); i += 4)  
        {  
            iTmp = 0;  
            for (int j = 0; j < 4; j++)  
            {  
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);  
            }  
            tmp.append(Integer.toHexString(iTmp));  
        }  
        return tmp.toString();  
    } 
	
	/**
	 * 十六进制转二进制
	 * @param hexString
	 * @return
	 */
    public static String hexString2binaryString(String hexString)  
    {  
        if (hexString == null || hexString.length() % 2 != 0)  
            return null;  
        String bString = "", tmp;  
        for (int i = 0; i < hexString.length(); i++)  
        {  
            tmp = "0000"  
                    + Integer.toBinaryString(Integer.parseInt(hexString  
                            .substring(i, i + 1), 16));  
            bString += tmp.substring(tmp.length() - 4);  
        }  
        return bString;  
    } 
    
    /**
     * 比较hash编码相似度
     * @param tarCode
     * @param sourceCode
     * @return
     */
    public static int compareHashCodes(String tarCode,String sourceCode)
    {
        int difference = 0;  
        int len =sourceCode.length();  
                 
        for (int i = 0; i < len; i++) {  
           if(sourceCode.charAt(i) != tarCode.charAt(i)) {  
               difference++;  
           }  
        }  
        
        return difference;
    }
    
    /**
	 * 获得指定位数的随机码
	 */
	public static String getRandomCode(int count) {
		
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < count; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();
		
	}
}
