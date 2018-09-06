package com.zhuoan.util;

import java.text.NumberFormat;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


import net.sf.json.JSONObject;

public class CheckTypeUtil {
	
	/**
	 * 判断手机号是否正确
	 * @param tel
	 * @return
	 */
	public static boolean isTelNum(String tel) {
		
		Pattern phone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");
		
		Matcher m = phone.matcher(tel);  
		
		return m.matches();
	}
	
	/**
	 * 1.判断是否为空
	 */
	public static boolean isNull(JSONObject obj,String keyname){
		if(obj.containsKey(keyname)&&obj.get(keyname)!=null&&!obj.get(keyname).equals("")&&!obj.get(keyname).equals("null")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 1.判断是否为空
	 * 其中有一个为空，返回false
	 */
	public static boolean isNull(JSONObject obj,String[] keyname){
		for(String str:keyname){
			if(obj.containsKey(str)){
				if(obj.get(str)!=null&&!obj.getString(str).equals("")&&!obj.getString(str).equals("null")){
					continue;
				}
			}
			return false;
		}
		return true;
	}
	/**
	 * 1.判断是否为空
	 */
	public static boolean isNull(Object obj){
		if(obj!=null&&!String.valueOf(obj).equals("null")&&!String.valueOf(obj).equals("")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 1.判断是否为空
	 */
	public static boolean isNull(String obj){
		if(obj!=null&&!obj.equals("null")&&!obj.trim().isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 1.判断是否为空
	 */
	public static boolean isNull(String[] obj){
		boolean back=true;
		if(obj!=null&&obj.length>0){
			for(String str:obj){
				if(str!=null&&!str.equals("null")&&!str.trim().isEmpty()){
					
				}else{
					back=false;
					break;
				}
			}
		}
		return back;
	}
	/**
	 * 1.判断是否为空且是数字
	 */
	public static boolean isNullAndNum(String obj){
		if(obj!=null&&!String.valueOf(obj).equals("null")&&!String.valueOf(obj).equals("")&&isNumber(obj)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 2.判断string是否可以转换为int
	 */
	public static boolean isInteger(String value) {
		if(isNull(value)){
			try {
				Integer.parseInt(value);
				return true;
			} catch (NumberFormatException e) {
			   return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * 3.判断string是否可以转换为float
	 */
	public static boolean isFloat(String value) {
		if(isNull(value)){
			try {
				Float.parseFloat(value);
				if (value.contains(".")){
					return true;
				}else{
					return false;
				}
			} catch (NumberFormatException e) {
			   return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * 4.判断string是否可以转换为double
	 */
	public static boolean isDouble(String value) {
		if(isNull(value)){
			try {
				Double.parseDouble(value);
				if (value.contains(".")){
					return true;
				}else{
					return false;
				}
			} catch (NumberFormatException e) {
			   return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * 5.判断string是否可以转换为long
	 */
	public static boolean isLong(String value) {
		if(isNull(value)){
			try {
				Long.parseLong(value);
				return true;
			} catch (NumberFormatException e) {
			   return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * 6.判断是否是数字
	 */
	public static boolean isNumber(String value) {
		return isLong(value)||isDouble(value);
	}
	/**
	 * 7.判断JSONObject是否为数字
	 */
	public static boolean isNumber(Object obj) {
		return isNumber(String.valueOf(obj));
	}
	/**
	 * 7.判断JSONObject是否为数字
	 */
	public static boolean isNumber(String[] obj) {
		boolean back=true;
		if(obj!=null&&obj.length>0){
			for(String str:obj){
				if(isNumber(String.valueOf(str))){
					
				}else{
					back=false;
					break;
				}
			}
		}
		return back;
	}
	/**
	 * 8.对象转化为JSONObject格式的String
	 */
	public static String getObjToString(Object obj) {
		JSONObject back=new JSONObject();
		back.put("newobj", obj);
		return back.toString();
	}
	/**
	 * Object 转化成double类型
	 * 如果Object不能转换成double则返回0
	 */
	public static double getDouble(Object obj){
		if(isNumber(obj)){
			return Double.valueOf(String.valueOf(obj));
		}
		return 0;
	}
	/**
	 * Object 转化成double类型
	 * 如果Object不能转换成double则返回0
	 */
	public static double getDouble(Object obj,double moren){
		if(isNumber(obj)){
			return Double.valueOf(String.valueOf(obj));
		}
		return moren;
	}
	/**
	 * Object 转化成int类型
	 * 如果Object不能转换成int则返回0
	 */
	public static int getInt(Object obj){
		if(isInteger(String.valueOf(obj))){
			return Integer.valueOf(String.valueOf(obj));
		}
		return 0;
	}
	/**
	 * Object 转化成int类型
	 * 如果Object不能转换成int则返回0
	 */
	public static int getInt(Object obj,int moren){
		if(isInteger(String.valueOf(obj))){
			return Integer.valueOf(String.valueOf(obj));
		}
		return moren;
	}
	/**
	 * Object 转化成long类型
	 * 如果Object不能转换成long则返回0
	 */
	public static long getLong(Object obj){
		if(isLong(String.valueOf(obj))){
			return Long.valueOf(String.valueOf(obj));
		}
		return 0;
	}
	/**
	 * double类型加法
	 * count，保留小数位
	 * type=0，采用四舍五入
	 * type=1，采用进一法
	 * type=2，采用退一法
	 */
	public static double addDouble(double A,double B,int count,int type){
		return getDouble((A+B), count, type);
	}
	/**
	 * double类型减法
	 * count，保留小数位
	 * type=0，采用四舍五入
	 * type=1，采用进一法
	 * type=2，采用退一法
	 */
	public static double jianDouble(double A,double B,int count,int type){
		return getDouble((A-B), count, type);
	}
	/**
	 * double类型乘法
	 * count，保留小数位
	 * type=0，采用四舍五入
	 * type=1，采用进一法
	 * type=2，采用退一法
	 */
	public static double chengDouble(double A,double B,int count,int type){
		return getDouble(A*B, count, type);
	}
	/**
	 * double类型除法
	 * count，保留小数位
	 * type=0，采用四舍五入
	 * type=1，采用进一法
	 * type=2，采用退一法
	 */
	public static double chuDouble(double A,double B,int count,int type){
		return getDouble(A/B, count, type);
	}
	/**
	 * 获取10的count次方
	 * @param count
	 * @return
	 */
	private static int getwei(int count){
		int bei=1;
		for(int i=0;i<count;i++){
			bei=bei*10;
		}
		return bei;
	}
	/**
	 * 把一个数转化为百分数
	 * k:是要转化的数字
	 * count:保留小数位
	 */
	public static String getBaiFenShu(double k,int count){
		if(k>1){
			k=1;
		}
		NumberFormat nt = NumberFormat.getPercentInstance();
		nt.setMinimumFractionDigits(count);
		return nt.format(k);
	}
	/**
	 * 把List<?>数据转换成String[]
	 */
	public static String[] getStringSZ(List<?> obj){
		return obj.toString().substring(1,obj.toString().length()-1).split(",");
	}
	/**
	 * 获取随机字符串
	 */
	public static String generateVerifyCode(int verifySize){
		String VERIFY_CODES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		int codesLen = VERIFY_CODES.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(verifySize);
		for(int i = 0; i < verifySize; i++){
			verifyCode.append(VERIFY_CODES.charAt(rand.nextInt(codesLen-1)));
		}
		return verifyCode.toString();
	}
	
	/**
	 * 获取随机字符串
	 */
	public static String generateStoreNum(int verifySize){
		String VERIFY_CODES = "0123456789";
		int codesLen = VERIFY_CODES.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(verifySize);
		for(int i = 0; i < verifySize; i++){
			verifyCode.append(VERIFY_CODES.charAt(rand.nextInt(codesLen-1)));
		}
		return verifyCode.toString();
	}
	/**
	 * 把key和value存入JSONObject中并返回JSONObject
	 */
	public static JSONObject getJsonObject(String[] key,String[] values){
		JSONObject back=new JSONObject();
		StringBuffer sb=new StringBuffer();
		boolean isfirst=true;
		for(int i=0;i<key.length;i++){
			back.put(key[i], values[i]);
			if(isNull(values[i])){
				if(isfirst){
					isfirst=false;
				}else{
					sb.append(",");
				}
				sb.append(key[i]);
			}
		}
		back.put("inputkeys", sb.toString());
		return back;
	}
	/**
	 * 把key和value存入JSONObject中并返回JSONObject
	 */
	public static JSONObject getJsonObject(String[] key,HttpServletRequest request){
		JSONObject back=new JSONObject();
		StringBuffer sb=new StringBuffer();
		boolean isfirst=true;
		for(int i=0;i<key.length;i++){
			String str=request.getParameter(key[i]);
			back.put(key[i], str);
			if(isNull(str)){
				if(isfirst){
					isfirst=false;
				}else{
					sb.append(",");
				}
				sb.append(key[i]);
			}
		}
		back.put("inputkeys", sb.toString());
		return back;
	}
	
	/**
	 * 判断A数组中的数，比对应B数组中的数大或等于
	 */
	public static boolean isBig(double[] a,double[] b){
		boolean back=true;
		for(int i=0;i<a.length;i++){
			if(a[i]<b[i]){
				back=false;
				break;
			}
		}
		return back;
	}
	/**
	 * 获取double几位小数
	 * count，保留小数位
	 * type=0，采用四舍五入
	 * type=1，采用进一法
	 * type=2，采用退一法
	 */
	public static double getDouble(double a,int count,int type){
		long bei=1;
		for(int i=0;i<count;i++){
			a=a*10;
			bei=bei*10;
		}
		long al=(long) a;
		switch (type) {
		case 0://采用四舍五入
			long ab=(long) (a*10);
			if(ab>(al*10+4)){//进一
				return ((double)(al+1))/bei;
			}else{//退一
				return ((double)al)/bei;
			}
		case 1://采用进一法
			if(a>al){
				return (al+1)/bei;
			}else{
				return ((double)al)/bei;
			}
		case 2://采用退一法
			return ((double)al)/bei;
		default:
			return 0;
		}
	}
	
	/**
	 * 去除零长度字符串
	 * @param str
	 * @return
	 */
	public static String replaceInvisibleStr(String str){
		int[] input = new int[] { 0x7f, 0x80, 0x81, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89, 0x8a,  
                0x8b, 0x8c, 0x8d, 0x8e, 0x8f, 0x90, 0x91, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97, 0x98, 0x99,  
                0x9a, 0x9b, 0x9c, 0x9d, 0x9e, 0x9f, 0xad, 0x483, 0x484, 0x485, 0x486, 0x487, 0x488, 0x489,  
                0x559, 0x55a, 0x58a, 0x591, 0x592, 0x593, 0x594, 0x595, 0x596, 0x597, 0x598, 0x599, 0x59a,  
                0x59b, 0x59c, 0x59d, 0x59e, 0x59f, 0x5a0, 0x5a1, 0x5a2, 0x5a3, 0x5a4, 0x5a5, 0x5a6, 0x5a7,  
                0x5a8, 0x5a9, 0x5aa, 0x5ab, 0x5ac, 0x5ad, 0x5ae, 0x5af, 0x5b0, 0x5b1, 0x5b2, 0x5b3, 0x5b4,  
                0x5b5, 0x5b6, 0x5b7, 0x5b8, 0x5b9, 0x5ba, 0x5bb, 0x5bc, 0x5bd, 0x5bf, 0x5c1, 0x5c2, 0x5c4,  
                0x5c5, 0x5c6, 0x5c7, 0x606, 0x607, 0x608, 0x609, 0x60a, 0x63b, 0x63c, 0x63d, 0x63e, 0x63f,  
                0x674, 0x6e5, 0x6e6, 0x70f, 0x76e, 0x76f, 0x770, 0x771, 0x772, 0x773, 0x774, 0x775, 0x776,  
                0x777, 0x778, 0x779, 0x77a, 0x77b, 0x77c, 0x77d, 0x77e, 0x77f, 0xa51, 0xa75, 0xb44, 0xb62,  
                0xb63, 0xc62, 0xc63, 0xce2, 0xce3, 0xd62, 0xd63, 0x135f, 0x200b, 0x200c, 0x200d, 0x200e,  
                0x200f, 0x2028, 0x2029, 0x202a, 0x202b, 0x202c, 0x202d, 0x202e, 0x2044, 0x2071, 0xf701,  
                0xf702, 0xf703, 0xf704, 0xf705, 0xf706, 0xf707, 0xf708, 0xf709, 0xf70a, 0xf70b, 0xf70c,  
                0xf70d, 0xf70e, 0xf710, 0xf711, 0xf712, 0xf713, 0xf714, 0xf715, 0xf716, 0xf717, 0xf718,  
                0xf719, 0xf71a, 0xfb1e, 0xfc5e, 0xfc5f, 0xfc60, 0xfc61, 0xfc62, 0xfeff, 0xfffc };  
        StringBuilder b = new StringBuilder();  
        int lastContinuous = -1;  
        int span = 0;  
        for (int i = 0; i < input.length; i++) {  
            if (lastContinuous == -1 && i < input.length - 1 && input[i] + 1 == input[i + 1]) {  
                lastContinuous = input[i];  
                span = 1;  
            } else {  
                if (input[i] == lastContinuous + span) {  
                    span++;  
                } else if (lastContinuous != -1) {  
                    if (b.length() > 0)  
                        b.append("|");  
                    b.append(String.format("[\\u%s-\\u%s]", zerolize(Integer.toHexString(lastContinuous)),  
                            zerolize(Integer.toHexString(lastContinuous + span - 1))));  
                    span = 0;  
                    lastContinuous = -1;  
                    i--;  
                } else {  
                    b.append("|\\u" + zerolize(Integer.toHexString(input[i])));  
                }  
            }  
        }  
        if (lastContinuous != -1) {  
            if (b.length() > 0)  
                b.append("|");  
            b.append(String.format("[\\u%s-\\u%s]", zerolize(Integer.toHexString(lastContinuous)),  
                    zerolize(Integer.toHexString(lastContinuous + span - 1))));  
        }  
        Pattern pattern = Pattern.compile(b.toString());  
        Matcher matcher = pattern.matcher(str);  
        return matcher.replaceAll("");
	}
	private static String zerolize(String s) {  
        if (s.length() < 4) {  
            s = "000".substring(0, 4 - s.length()) + s;  
        }  
        return s;  
    } 
	
	
	public static void main(String[] args){
		double a=66d*0.7d;
		double b=chengDouble(66d, 0.7d, 2, 2);
		double c=getDouble(66d*0.7d, 2, 2);
		System.out.println("a="+a+"   b="+b+"   c="+c);
	}
}
