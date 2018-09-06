package com.zhuoan.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;


/**
 * 上传基类
 * @author lhp
 *
 */
public class BaseUpload {
	
	
	private String allowSuffix = "jpg,JPG,jpeg,JPEG,png,PNG,gif,GIF,zip,ZIP,rar,RAR";// 允许文件格式
	
	private long allowSize = 1000 * 1024*1024L; // 允许文件大小
	
	private String fileName;
	
	private String[] fileNames;
	

	public String getAllowSuffix() {
		return allowSuffix;
	}

	public void setAllowSuffix(String allowSuffix) {
		this.allowSuffix = allowSuffix;
	}

	public long getAllowSize() {
		return allowSize * 1024 * 1024;
	}

	public void setAllowSize(long allowSize) {
		this.allowSize = allowSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	
	/**
	 * 功能：重新命名文件
	 * @return
	 */
	public String getFileNameNew() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return fmt.format(new Date());
	}

	
	/**
	 * 功能：文件批量上传
	 * @param files
	 * @param destDir
	 * @throws Exception
	 */
	public String[] uploads(MultipartFile[] files, String destDir,
			HttpServletRequest request) throws Exception {
		try {
			fileNames = new String[files.length];
			int index = 0;
			for (MultipartFile file : files) {
				String suffix = file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf(".") + 1);
				int length = getAllowSuffix().indexOf(suffix);
				if (length == -1) {
					throw new Exception("请上传允许格式的文件");
				}
				if (file.getSize() > getAllowSize()) {
					throw new Exception("您上传的文件大小已经超出范围");
				}
				
				String realPath = request.getServletContext().getRealPath("/");
				File destFile = new File(realPath + destDir);
				if (!destFile.exists()) {
					destFile.mkdirs();
				}	
				String fileNameNew = getFileNameNew() + "." + suffix;
				String imgFilePath = destFile.getAbsoluteFile() + "/" + fileNameNew;
				File f = new File(imgFilePath);
				file.transferTo(f);
				f.createNewFile();
				fileNames[index++] = destDir + fileNameNew;
			}
			return fileNames;
			
		} catch (Exception e) {
			System.out.println("图片上传失败！");
		}
		return null;
	}
	

	/**
	 * 功能：文件上传
	 * @param files
	 * @param destDir
	 * @throws Exception
	 */
	public String upload(MultipartFile file, String destDir,
			HttpServletRequest request) throws Exception {
		
		if(file!=null){
			
			try {
				String suffix = file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf(".") + 1);
				int length = getAllowSuffix().indexOf(suffix);
				if (length == -1) {
					throw new Exception("请上传允许格式的文件");
				}
				if (file.getSize() > getAllowSize()) {
					throw new Exception("您上传的文件大小已经超出范围");
				}
				
				String realPath = request.getServletContext().getRealPath("/");
				File destFile = new File(realPath + destDir);
				if (!destFile.exists()) {
					destFile.mkdirs();
				}	
				String fileNameNew = UUID.randomUUID() + "." + suffix;
				String imgFilePath = destFile.getAbsoluteFile() + "/" + fileNameNew;
				File f = new File(imgFilePath);
				file.transferTo(f);
				f.createNewFile();
				return destDir + fileNameNew;
				
			} catch (Exception e) {
				System.out.println("图片上传失败！");
			}
		}

		return null;
	}
	
	
	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 * @param imgStr
	 * @return
	 */
	public boolean generateImage(String imgStr, String imgFilePath) {    
		
		if (imgStr == null) //图像数据为空  
			return false;  
		Base64 decoder = new Base64();  
		try{  
			//Base64解码  
			byte[] b = decoder.decode(imgStr.getBytes());  
			//生成jpeg图片  
			OutputStream out = new FileOutputStream(imgFilePath);      
			out.write(b);  
			out.flush();  
			out.close();  
			return true;  
		}catch (Exception e) {  
			return false;  
		}  
	}  

}