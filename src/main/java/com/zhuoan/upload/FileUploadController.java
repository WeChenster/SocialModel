package com.zhuoan.upload;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.zhuoan.dto.Dto;
import com.zhuoan.util.MathDelUtil;
import com.zhuoan.util.TimeUtil;

@Controller
public class FileUploadController {
	/**
	 * 上传文件
	 * @param file
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("fileupload")
	public void fileUpload(@RequestParam(value="file",required=false) MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//0.获得项目真实路径
		String sRootPath=request.getSession().getServletContext().getRealPath("");
		String sBasePath=sRootPath+"/upload/files/";//获得存储根路径
		
		//1.获得当前月份，创建文件夹
		String sNowMonth=TimeUtil.getNowDate("yyyyMM");
		File OutfileFolder=new File(sBasePath+"/"+sNowMonth);
		if (!OutfileFolder.exists() && !OutfileFolder.isDirectory())
			OutfileFolder.mkdir();
		
		//2.设置存储地址
		String originName=file.getOriginalFilename();
		String sContentType=originName.split("\\.")[1];
		String sNowDate=TimeUtil.getNowDate("yyyyMMddHHmmSSss");
		String sSavePath=sBasePath+"/"+sNowMonth+"/"+sNowDate+"."+sContentType;//保存物理路径
		
		//3.输出文件
		InputStream input=file.getInputStream();
		commonFileOutPut(input, sContentType, sSavePath);
		
		//4.返回结果
		JSONObject msg=new JSONObject();
		msg.element("url", "/upload/files/"+sNowMonth+"/"+sNowDate+"."+sContentType);
		msg.element("originName", originName);
		Dto.printMsg(response, msg.toString());
	}

	
	/**
	 * 上传图片
	 * @param file
	 * @param request
	 * @param response
	 */
	@RequestMapping("imgupload")
	public void imUpload(@RequestParam(value="file",required=false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		JSONObject oRtnMsg=new JSONObject();
		try{
			//0.輸出圖片
			JSONObject sNetPath=inputImg(request, response, file);
	    	//2.返回消息
	    	oRtnMsg.element("path", "/upload/img/"+sNetPath.getString("url"));
	    	oRtnMsg.element("msg", "上传成功");
	    	oRtnMsg.element("imgSign", sNetPath.getJSONObject("imgSign"));
		}catch(Exception e){
			e.printStackTrace();
			oRtnMsg.element("msg", "上传失败");
		}
		
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("utf-8");
			out =response.getWriter();
			out.write(oRtnMsg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{out.close();}
	}

	/**
	 * 文本编辑器后台控制
	 * @param request
	 * @param response
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("init_edit")
	public void initEditor(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="upfile",required=false) MultipartFile file) throws Exception{
		//0.获得控制参数
		String action=request.getParameter("action");
		
		//1.执行对应操作
		String out="";
		if(action.equals("config"))
			out=initEdit(request);
		else if(action.equals("uploadimage"))
			out=inputImg(request, response, file).toString();
		
		Dto.printMsg(response, out.toString());
	}
	
/*****************************************************************辅助方法******************************************************************************/	

	/**
	 * 初始化文本编辑器
	 * @param request
	 * @return
	 */
	private String initEdit(HttpServletRequest request) throws Exception{
		//0.获得项目真实路径
		String sRootPath=request.getSession().getServletContext().getRealPath("");
		
		//1.组织编辑器设置json所在路径
		String savePath=sRootPath+"/WEB-INF/classes/config.json";
		
		//2.读取配置文件
		InputStream stream=new FileInputStream(new File(savePath));
		
		//3.输出字符串流
		StringBuffer out = new StringBuffer();  
		byte[] b = new byte[4096];  
		for (int n; (n = stream.read(b)) != -1;) {  
		    out.append(new String(b, 0, n));  
		}
		return out.toString();
	}
	
	/**
	 * 文本編輯器上傳圖片
	 * @param file
	 * @param request
	 * @param response
	 */
	private JSONObject inputImg(HttpServletRequest request,HttpServletResponse response,MultipartFile file) throws IOException{
		JSONObject result=new JSONObject();
		
		//0.获得项目真实路径
		String sRootPath=request.getSession().getServletContext().getRealPath("");
		String sBasePath=sRootPath+"/upload/img/";//获得存储根路径
//		String sBasePath="/diskplugin/wwwroot/nanny/upload/img/";//获得存储根路径
		//1.获得当前月份，创建文件夹
		String sNowMonth=TimeUtil.getNowDate("yyyyMM");
		File OutfileFolder=new File(sBasePath+"/"+sNowMonth);
		if (!OutfileFolder.exists() && !OutfileFolder.isDirectory())
			OutfileFolder.mkdir();
		//2.设置存储地址
		String sContentType=(file.getOriginalFilename()).split("\\.")[1];
		String sNowDate=TimeUtil.getNowDate("yyyyMMddHHmmSSss");
		String sSavePath=sBasePath+"/"+sNowMonth+"/"+sNowDate+"."+sContentType;//保存物理路径
//		String sNetPath="/upload/img/"+sNowMonth+"/"+sNowDate+"."+sContentType;//访问地址
		//3.输出文件
		InputStream input=file.getInputStream();
		
		commonFileOutPut(input, sContentType, sSavePath);//输出原图文件
		input.close();
		
		InputStream oriInput=new BufferedInputStream(new FileInputStream(sSavePath));
		imgCut(oriInput, sContentType, sBasePath+"/"+sNowMonth+"/"+sNowDate+"."+sContentType+"_360",0,0);//输出压缩大小文件
		
    	//4.組織json
    	result.element("name", sNowDate+"."+sContentType);
    	result.element("originalName", file.getOriginalFilename());
    	result.element("size", "");
    	result.element("state", "SUCCESS");
    	result.element("type", "."+(file.getOriginalFilename()).split("\\.")[1]);
    	result.element("url", sNowMonth+"/"+sNowDate+"."+sContentType);
    	return result;
	}
	
	/**
	 * 输出截取图片
	 * @param input
	 * @param sContentType
	 * @param outputPath
	 */
	private void imgCut(InputStream input,String sContentType,String outputPath,int height,int width){
		try{
			FileOutputStream out = new FileOutputStream(outputPath);
			
			// 0.取得图片读入器
			Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(sContentType);
			ImageReader reader = readers.next();
			ImageInputStream iis = ImageIO.createImageInputStream(input);
			reader.setInput(iis, true);
			
			ImageReadParam param=null;
			// 2.计算截取长宽
			if(height==0 || width==0)
			{
				width=360;
				Double scale=360D / Double.valueOf(reader.getWidth(0));
				Double nowHeight=MathDelUtil.halfUp(scale * reader.getHeight(0), 0);
				height=nowHeight.intValue();
			}else{
				// 3.设定截取范围
				param = reader.getDefaultReadParam();
				Rectangle rect = new Rectangle(0, 0, width, height);
				param.setSourceRegion(rect);
			}
			
			BufferedImage bi;
			if(param!=null)
				bi = reader.read(0, param);
			else
				bi=reader.read(0);
			
			// 4.输出截取图片
			Image image=bi.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			BufferedImage show_img =new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = show_img.createGraphics();
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿
	        g.drawImage(image, 0, 0, null); // 绘制图像
	        g.dispose();
	        
	        ImageIO.write(show_img, sContentType, out);
	        out.flush();
	        out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}
	
	/**
	 * 通常文件输出
	 * @param input
	 * @param sContentType
	 * @param outputPath
	 */
	private void commonFileOutPut(InputStream input,String sContentType,String outputPath){
		try{
			FileOutputStream fos=new FileOutputStream(new File(outputPath));
			byte[] buffer = new byte[1024];
			int len;
			while ((len = input.read(buffer)) > 0)
				fos.write(buffer, 0, len);
			fos.flush();
	    	fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
