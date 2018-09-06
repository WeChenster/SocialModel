package com.zhuoan.ssh.bean;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class HttpReqUtil {

	
	/**
	 * http请求
	 * @param url 路径
	 * @param queryString 查询参数
	 * @param charset 参数编码格式
	 * @return
	 */
	public static String doGet(String url, String queryString, String charset) {
		
		StringBuffer resp = new StringBuffer();
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {
			if (StringUtils.isNotBlank(queryString))
				// 对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串
				method.setQueryString(URIUtil.encodeQuery(queryString));
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(method.getResponseBodyAsStream(),
								charset));
				String line;
				while ((line = reader.readLine()) != null) {

					resp.append(line);
				}
				reader.close();
			}
		} catch (URIException e) {
			System.out.println("执行HTTP Get请求时，编码查询字符串“" + queryString
					+ "”发生异常！");
		} catch (IOException e) {
			System.out.println("执行HTTP Get请求" + url + "时，发生异常！");
		} finally {
			method.releaseConnection();
		}
		return resp.toString();
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
}
