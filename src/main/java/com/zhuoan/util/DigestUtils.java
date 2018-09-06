package com.zhuoan.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* @ClassName: DigestUtils
* @Description: 加密工具
* @author LHP
* @date 2015-5-28
*
*/ 
public class DigestUtils {

	/**
	 * MD5加密
	 * @param str
	 * @return
	 */
	public static String toMD5(String str) {
		
		char digits[] = {'0','1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f' };
		
		char strChar[] = new char[32];   
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] b = md.digest();
			
			int k = 0;
			
			for (int i = 0; i < 16; i++) {
				
				strChar[k++] = digits[b[i] >>> 4 & 0xf];
				strChar[k++] = digits[b[i] & 0xf];
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return new String(strChar).toLowerCase();
	}
	
	public static void main(String[] args) {
		
		System.err.println(toMD5("104"));
	}
	
}
