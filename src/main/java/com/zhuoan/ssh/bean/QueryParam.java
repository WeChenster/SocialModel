package com.zhuoan.ssh.bean;

import java.util.Map;

public class QueryParam {

	private Map<String, Object> paramMap; // 存放参数值
	private Map<String, String> typeMap;  // 存放参数类型
	private Map<String, String> orderMap; // 排序字段 asc、desc
	
	
	public Map<String, Object> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	public Map<String, String> getTypeMap() {
		return typeMap;
	}
	public void setTypeMap(Map<String, String> typeMap) {
		this.typeMap = typeMap;
	}
	public Map<String, String> getOrderMap() {
		return orderMap;
	}
	public void setOrderMap(Map<String, String> orderMap) {
		this.orderMap = orderMap;
	}
	
}
