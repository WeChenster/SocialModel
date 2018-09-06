package com.zhuoan.util.weixin;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


/**
 * 系统证书工具类
 * @Copyright Copyright (c) 2015
 * @Company zhouan
 * @author wph
 * @version 0.1
 */
public class KeyStoreUtil {

	/**
	 * 生成带证书认证的安全链接
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static CloseableHttpClient getCilentWithKey(String path) throws Exception{
		//0.传入证书，生成认证文件
		KeyStore keyStore  = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File(path));
		try{
			keyStore.load(instream, Configure.getMchid().toCharArray());
		}finally{
			 instream.close();
		}
		
		//1.生成专用安全链接池
		 SSLContext sslcontext = SSLContexts.custom()
				 							.loadKeyMaterial(keyStore, Configure.getMchid().toCharArray())
				 							.build();
		 SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },
																			null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		 CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		 return httpclient;
	}
}
