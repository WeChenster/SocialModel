package com.zhuoan.util;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;


public class HttpReqUtil {

	/**
	 * 带证书的http请求
	 * @param url 路径
	 * @param queryString 查询参数
	 * @param charset 参数编码格式
	 * @return
	 * @throws Exception 
	 */
//	public static String doGet(String url, String queryString, String charset,String path) throws Exception {
//		
//		StringBuffer resp = new StringBuffer();
//		CloseableHttpClient httpclient = KeyStoreUtil.getCilentWithKey(path);
//		try {
//			HttpPost method = new HttpPost(url); 
//			
//			//组织传递的参数
//			StringEntity param = new StringEntity(queryString, "UTF-8");
//			param.setContentType("text/xml;charset=UTF-8");
//			param.setContentEncoding("UTF-8");
//			method.addHeader("Content-Type", "text/xml"); 
//			method.setEntity(param); 
//			
//			//发起post请求
//			CloseableHttpResponse response = httpclient.execute(method);
//			try {
//				HttpEntity entity = response.getEntity();
//				if (entity != null) {
//					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),charset));
//					String line;
//					while ((line = bufferedReader.readLine()) != null) {
//						resp.append(line);
//					}
//				}
//				EntityUtils.consume(entity);
//			} finally {
//				response.close();
//			}
//		}finally {
//            httpclient.close();
//        }
//		return resp.toString();
//	}
	
	
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
			e.printStackTrace();
			System.out.println("执行HTTP Get请求时，编码查询字符串“" + queryString
					+ "”发生异常！");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("执行HTTP Get请求" + url + "时，发生异常！");
		} finally {
			method.releaseConnection();
		}
		return resp.toString();
	}
	
	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * @throws UnsupportedEncodingException 
	 */
	public static String doPost(String url, Map<String, String> params,String queryString,
			String charset) throws UnsupportedEncodingException {
		
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		
		// 设置Http Post数据
		if (params != null) {
			
			NameValuePair[] p = new NameValuePair[params.size()];
			
	        int i = 0;
	        
	        for (Map.Entry<String, String> entry : params.entrySet()) {
	        	p[i++] = new NameValuePair(entry.getKey(), entry.getValue());
	        }
	        post.setRequestBody(p);  
		}
		else if(queryString!=null){
			RequestEntity requestEntity = new StringRequestEntity(queryString,"text/xml","UTF-8");
			post.setRequestEntity(requestEntity);
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
	 * httpPost请求，application/json格式
	 * @throws UnsupportedEncodingException
	 */
	public static JSONObject doPostJSON(String url, JSONObject jsonParam, String authorization,
								String charset) throws IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type","application/json");
		if(authorization!=null){
			httpPost.addHeader("Authorization","Bearer  "+authorization);
		}

		StringEntity entity = new StringEntity(jsonParam.toString(), charset);
		entity.setContentEncoding(charset);
		entity.setContentType("application/json");
		httpPost.setEntity(entity);

		// 执行请求
		HttpResponse response = httpClient.execute(httpPost);
		int responseCode = response.getStatusLine().getStatusCode();

		String json2 = EntityUtils.toString(response.getEntity(), charset);
		JSONObject jsonObject = JSONObject.fromObject(json2);

		// 打印执行结果
		// 打印响应状态
		System.out.println("请求："+url+"  状态码："+responseCode+"   结果为："+jsonObject.toString());
		return jsonObject;
	}


	/**
	 * httpPost请求，application/json格式
	 * GET POST DELETE PUT ...
	 * @throws UnsupportedEncodingException
	 */
	public static JSONObject httpByReqMethod (String url, JSONObject parameter,String ReqMethod, String authorization) throws Exception{
		//定义stringbuffer  方便后面读取网页返回字节流信息时的字符串拼接
		StringBuffer buffer = new StringBuffer();

		//创建url_connection
		java.net.URLConnection http_url_connection = null;
		int statusCode=0;
		try {
			http_url_connection = (new java.net.URL(url)).openConnection();
			java.net.HttpURLConnection HttpURLConnection = (java.net.HttpURLConnection)http_url_connection;//将urlconnection类强转为httpurlconnection类

			HttpURLConnection.setDoInput(true);
			HttpURLConnection.setDoOutput(true);

			HttpURLConnection.setRequestMethod(ReqMethod);//设置请求方式。可以是delete put post get
			HttpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameter.toString().length()));//设置内容的长度
			HttpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");//设置编码格式
			HttpURLConnection.setRequestProperty("accept", "application/json");//设置接收返回参数格式

			HttpURLConnection.setUseCaches(false);

			if (null!=authorization) {
				//如果传入的参数不为空，则通过base64将传入参数解码
				HttpURLConnection.setRequestProperty("Authorization", "Bearer "+authorization);
			}

			// write request.
			java.io.BufferedOutputStream output_stream = new java.io.BufferedOutputStream(HttpURLConnection.getOutputStream());
			output_stream.write(parameter.toString().getBytes());
			output_stream.flush();
			output_stream.close();
			output_stream = null;

			statusCode = ((java.net.HttpURLConnection) http_url_connection).getResponseCode();

			java.io.InputStreamReader input_stream_reader = new java.io.InputStreamReader(HttpURLConnection.getInputStream(), "utf-8");
			java.io.BufferedReader buffered_reader = new java.io.BufferedReader(input_stream_reader);
			buffer = new StringBuffer();
			String line;
			while ((line = buffered_reader.readLine()) != null) {
				buffer.append(line);
			}
			line=null;
			input_stream_reader.close();
			input_stream_reader = null;
			buffered_reader.close();
			buffered_reader = null;
			//  http_url_connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("请求："+url+"    状态码："+statusCode+"   结果为："+buffer.toString());
		JSONObject data=new JSONObject();
		if(buffer.toString().length()>0) {
			data = JSONObject.fromObject(buffer.toString());
		}
		return  data;
	}


}
