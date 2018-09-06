package com.zhuoan.util.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * HTTP工具类
 */
public final class HttpTookit {

	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param charset
	 *            字符集
	 * @param pretty
	 *            是否美化
	 * @return 返回请求响应的HTML
	 */
	public static String doGet(String url, String charset, boolean pretty) {
		
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {

			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(method.getResponseBodyAsStream(),
								charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty)
						response.append(line).append(
								System.getProperty("line.separator"));
					else
						response.append(line);
				}
				reader.close();
			}
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("执行HTTP Get请求" + url + "时，发生异常！");
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}

	
	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 */
	public static String doPost(String url, Map<String, String> params,
			String charset) {
		
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		
		// 设置Http Post数据
		if (params != null) {
			
			NameValuePair[] p = new NameValuePair[params.size()];
			
	        int i = 0;
	        
	        for (Map.Entry<String, String> entry : params.entrySet()) {
	        	
	        	p[i++] = new NameValuePair(entry.getKey(), entry.getValue());
	        }
	        post.setRequestBody(p);  
		}
		
		try {
			
			client.executeMethod(post);
			
			if (post.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(post.getResponseBodyAsStream(),
								charset));
				String line;
				while ((line = reader.readLine()) != null) {
					
						response.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			System.out.println("执行HTTP Post请求" + url + "时，发生异常！");
		} finally {
			post.releaseConnection();
		}
		return response.toString();
	}
	
	
	/**
	 * post XML参数
	 * @param requestUrl
	 * @param xml
	 * @return
	 */
	public static String doPost(String requestUrl, String xml) {
		
		StringBuffer response = new StringBuffer();
		
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setRequestMethod("POST");
			httpUrlConn.setDoOutput(true);
			OutputStream outputStream = httpUrlConn.getOutputStream();
			outputStream.write(xml.getBytes("UTF-8"));
			outputStream.close();
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			
			while ((str = bufferedReader.readLine()) != null) {
				response.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.toString();
	}
	
}