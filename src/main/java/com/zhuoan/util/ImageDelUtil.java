package com.zhuoan.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;


/**
 * 图片文件处理工具类
 * @Copyright Copyright (c) 2015
 * @Company zhouan
 * @author wph
 * @version 0.1
 */
public class ImageDelUtil {

	/**
	 * 远程图片本地化
	 * @param request
	 * @param sUrl
	 * @return 图片保存路径
	 */
	public static String transRemoteToLocal(HttpServletRequest request,String sUrl){
		try {
			//0.获得项目真实路径
			String sRootPath=request.getSession().getServletContext().getRealPath("");
			String sBasePath=sRootPath+"\\upload\\img\\";//获得存储根路径
			//1.获得当前月份，创建文件夹
			String sNowMonth=TimeUtil.getNowDate("yyyyMM");
			File OutfileFolder=new File(sBasePath+"\\"+sNowMonth);
			if (!OutfileFolder.exists() && !OutfileFolder.isDirectory())
				OutfileFolder.mkdir();
			//2.设置存储地址
			String sNowDate=TimeUtil.getNowDate("yyyyMMddHHmmSSss");
			String sSavePath=sBasePath+"\\"+sNowMonth+"\\"+sNowDate+".jpg";//保存物理路径
			String sNetPath="/xiaolv/upload/img/"+sNowMonth+"/"+sNowDate+".jpg";//访问地址
			
			//3.获得远程图片输入流
			URL url =new URL(sUrl);
			DataInputStream dataInputStream = new DataInputStream(url.openStream());
		    FileOutputStream fileOutputStream = new FileOutputStream(new File(sSavePath));
		    
			//4.输出图片
		    byte[] buffer = new byte[1024];
		    int length;
		    while ((length = dataInputStream.read(buffer)) > 0)
		    	fileOutputStream.write(buffer, 0, length);
		    dataInputStream.close(); 
		    fileOutputStream.close(); 
		    
		    return sNetPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
}
