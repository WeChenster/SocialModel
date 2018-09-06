package com.zhuoan.util;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成工具类
 * @Copyright Copyright (c) 2015
 * @Company zhouan
 * @author wph
 * @version 0.1
 */
public class CreateQrCodeUtill {
	
	/**
	 * 二维码信息
	 */
	private static String name;
	private static String tel;
	private static String company;
	private static String pos;
	private static String url;
	

	/**
	 * 根据输入文字生成二维码图片
	 * @param str
	 * @param w
	 * @param h
	 * @throws WriterException 
	 */
	public static void createQRCode(String str,int w,int h, OutputStream outpush) throws Exception{
		 Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		 // 内容所使用编码 ,生成二维码
		 hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		 hints.put(EncodeHintType.MARGIN, 1);  
		 hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		 BitMatrix matrix = new MultiFormatWriter().encode(str,BarcodeFormat.QR_CODE, w, h, hints);
		 BufferedImage image=MatrixToImageWriter.toBufferedImage(matrix);
		 ImageIO.write(image, "png", outpush);//将内存中的图片通过流动形式输出到客户端
	}

	/**
	 * 创建名片保存二维码
	 * @param w
	 * @param h
	 * @param outpush
	 * @throws Exception 
	 */
	public static void createQRCOde(int w,int h, OutputStream outpush) throws Exception{
		StringBuffer text = new StringBuffer();
		text.append("BEGIN:VCARD\n" + "VERSION:3.0\n");
		if (name != null) {
			text.append("N:" + name + "\n");
		}
		if (tel != null)// 电话
		{
			text.append("TEL:" + tel + "\n");
		}
		if (company != null)// 组织
		{
			String temp = "ORG:" + company;
			text.append(temp + "\n");
		}
		if (pos != null)// 职位
		{
			text.append("TITLE:" + pos + "\n");
		}
		// 个人(公司)主页地址
		
		if (url != null)// 备注
		{
			text.append("URL:"+url+"\n");
		}
		text.append("END:VCARD");
		
		createQRCode(text.toString(), w, h, outpush);
		clear();
	}

	/**
	 * 清空数据
	 */
	private static void clear(){
		name=null;
		tel=null;
		company=null;
		pos=null;
		url=null;
	}
	
	public static void setName(String name) {
		CreateQrCodeUtill.name = name;
	}


	public static void setTel(String tel) {
		CreateQrCodeUtill.tel = tel;
	}


	public static void setCompany(String company) {
		CreateQrCodeUtill.company = company;
	}


	public static void setPos(String pos) {
		CreateQrCodeUtill.pos = pos;
	}


	public static void setUrl(String url) {
		CreateQrCodeUtill.url = url;
	}
	
	
}
