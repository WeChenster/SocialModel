package com.zhuoan.ssh.bean;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @ClassName: CompressFileUtil
* @Description: 文件压缩
* @author LHP
* @date 2015-11-25
*
*/ 
public class CompressFileUtil {

	private OutputStream res;
	private ZipOutputStream zos;
	private String outPath;


	/**
	 * 压缩处理
	 * @param savePath
	 * @param length
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void zipFile(String savePath,int length,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException { 
		
		// 文件相对路径
		String path = savePath.substring(length)+"/";
		byte[] buf = new byte[8192];
		int len;
		// 取得文件夹下所有文件
		File[] files = new File(savePath).listFiles();

		for (File file : files) {

			if (file.isDirectory()){
				// 递归
				zipFile(file.getPath(), length, request, response);
			}else {
				
				zos.putNextEntry(new ZipEntry(path+file.getName()));
				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(file));
				while ((len = bis.read(buf)) > 0) {
					zos.write(buf, 0, len);
				}
				bis.close();
				zos.closeEntry();
			}
		}
	}
	
	
	/**
	 * 预处理方法
	 * @param name
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void preProcess(String name,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		res = response.getOutputStream();
		// 清空输出流
		response.reset();
		// 设定输出文件头
		response.setHeader("Content-Disposition", "attachment;filename="
				+ name + ".zip");

		response.setContentType("application/zip");
		response.setCharacterEncoding("utf-8");
		zos = new ZipOutputStream(res);
	}

	/**
	 * 后处理
	 * @throws IOException
	 */
	public void afterProcess() throws IOException {

		zos.close();
		res.close();
	}

	public OutputStream getRes() {
		return res;
	}

	public void setRes(OutputStream res) {
		this.res = res;
	}

	public ZipOutputStream getZos() {
		return zos;
	}

	public void setZos(ZipOutputStream zos) {
		this.zos = zos;
	}

	public String getOutPath() {
		return outPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}

}
