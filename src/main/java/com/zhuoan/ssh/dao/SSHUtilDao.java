package com.zhuoan.ssh.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhuoan.ssh.bean.PageUtil;
import com.zhuoan.ssh.bean.QueryParam;


public interface SSHUtilDao {
	
	
	/*
	 * 保存
	 */
	
	/**
	 * 将实体对象持久化（保存到数据库）
	 * @param obj 实体对象
	 * @return 返回id
	 */
	public Serializable saveObject(Object obj);
	
	/**
	 * 将实体对象持久化（保存到数据库）
	 * @param clazz 实体类型 实体类型
	 * @param obj 实体对象
	 * @return 返回实体对象
	 */
	public Object saveAsObject(Class clazz, Object obj);
	
	/**
	 * 将实体对象持久化（保存到数据库）
	 * @param clazz 实体类型 实体类型
	 * @param obj 实体对象
	 * @return 成功返回true，失败返回false
	 */
	public boolean saveOrUpdateObject(Class clazz, Object obj);


	/*
	 * 删除
	 */
	
	/**
	 * 根据id将实体对象删除
	 * @param clazz 实体类型 实体类型
	 * @param id 对象编号（通常为字符串或数字）
	 * @return 成功返回true，失败返回false
	 */
	public boolean delObjectById(Class clazz, Serializable id);
	
	/**
	 * 将实体对象删除
	 * @param obj 实体对象
	 * @return 成功返回true，失败返回false
	 */
	public boolean delObject(Object obj);
	
	/**
	 * 删除符合条件的实体对象信息
	 * @param hql hql语句（格式：HQL语句+占位符‘？’）
	 * @param param 条件参数数组
	 * @return 成功返回true，失败返回false
	 */
	public boolean delObjectByHQL(String hql, Object[] param);
	
	/**
	 * 删除符合条件的实体对象信息
	 * @param sql sql语句（格式：SQL语句+占位符‘？’）
	 * @param param 条件参数数组
	 * @return 成功返回true，失败返回false
	 */
	public boolean delObjectBySQL(String sql, Object[] param);
	
	
	/*
	 * 更新
	 */
	
	/**
	 * 更新实体对象信息（完全覆盖）
	 * @param obj 实体对象
	 * @return 成功返回true，失败返回false
	 */
	public boolean updObject(Object obj);
	
	/**
	 * 根据条件更新实体对象信息
	 * @param hql hql语句（格式：HQL语句+占位符‘？’）
	 * @param param 条件参数数组
	 * @return 成功返回true，失败返回false
	 */
	public boolean updObjectByHQL(String hql, Object[] param);
	
	/**
	 * 根据条件更新实体对象信息
	 * @param sql sql语句（格式：SQL语句+占位符‘？’）
	 * @param param 条件参数数组
	 * @return 成功返回true，失败返回false
	 */
	public boolean updObjectBySQL(String sql, Object[] param);
	
	
	/*
	 * 查询
	 */

	/**
	 * 根据实体对象ID获取实体对象
	 * @param clazz 实体类型
	 * @param id
	 * @return 返回实体对象或null
	 */
	public Object getObjectById(Class<?> clazz, Serializable id);

	/** 
	 * 根据条件获取单条记录
	 * @param hql
	 * @param queryParam 查询参数
	 * @return  返回实体对象或null
	 */
	public Object getObjectByHQL(String hql, Object[] queryParam);
	
	/** 
	 * 根据条件获取单条记录
	 * @param sql
	 * @param queryParam 查询参数
	 * @return  返回实体对象或null
	 */
	public Object getObjectBySQL(String sql, Object[] queryParam);
	
	/** 
	 * 根据条件获取单条记录
	 * @param sql
	 * @param queryParam 查询参数
	 * @return  返回实体对象或null
	 */
	public Object getObjectBySQL(String sql, Object[] queryParam, Class clazz);
	
	/**
	 * 根据条件获取记录列表
	 * @param clazz 实体类型
	 * @param queryParam 查询参数
	 * @param pageUtil
	 * @return 返回列表数据
	 */
	public List<?> getObjectList(Class<?> clazz, QueryParam queryParam, PageUtil pageUtil);
	
	/**
	 * 根据条件获取记录列表
	 * @param hql
	 * @param queryParam 查询参数
	 * @param pageUtil
	 * @return 返回列表数据
	 */
	public List<?> getObjectList(String hql, Object[] queryParam, PageUtil pageUtil);
	
	/**
	 * 根据条件获取记录列表
	 * @param sql
	 * @param queryParam 查询参数
	 * @param pageUtil
	 * @return 返回列表数据
	 */
	public List<?> getObjectListBySQL(String sql, Object[] queryParam, PageUtil pageUtil);
	
	/**
	 * 分页查询
	 * @param sql
	 * @param queryParam 查询参数
	 * @param indexPage 页号
	 * @param pageSize 每页条数
	 * @return 返回列表数据
	 */
	public List<?> getObjectListBySQL(String sql, Object[] queryParam, Integer indexPage, Integer pageSize);

	/** 
	 * 根据条件获取记录列表
	 * @param sql
	 * @param queryParam 查询参数
	 * @param clazz 实体类型 
	 * @return 返回列表数据
	 */
	public List<?> getObjectListBySQL(String sql, Object[] queryParam, Class clazz);
	
	
	// 其他查询类型
	
	/** 
	 * 根据条件获取记录总数
	 * @param sql
	 * @param queryParam 查询参数
	 * @return 返回记录条数
	 */
	public Integer getCount(String sql,Object[] queryParam);
	
	/**
	 * 根据条件获取列colName的总数
	 * @param clazz 实体类型
	 * @param colName 列名
	 * @param queryParam 查询参数
	 * @return 返回记录条数
	 */
	public Integer getCount(Class<?> clazz, String colName, QueryParam queryParam);
	
	/** 
	 * 根据条件获取记录总和
	 * @param sql
	 * @param queryParam 查询参数
	 * @return 返回数据列的和
	 */
	public Object getSum(String sql,Object[] queryParam);
	
	/**
	 * 根据列名取数据列总和
	 * @param clazz 实体类型
	 * @param colName 列名
	 * @param queryParam 查询参数
	 * @return 返回数据列的和
	 */
	public Object getSum(Class<?> clazz, String colName, QueryParam queryParam);
	
	/** 
	 * 根据条件获取某一列的平均值
	 * @param sql
	 * @param queryParam 查询参数
	 * @return 返回数据列的平均数
	 */
	public Object getAvg(String sql, Object[] queryParam);
	
	/**
	 * 根据列名取该数据列平均值
	 * @param clazz 实体类型
	 * @param colName 列名
	 * @param queryParam 查询参数
	 * @return 返回数据列的平均数
	 */
	public Object getAvg(Class<?> clazz, String colName, QueryParam queryParam);
	
	// 其他功能
	
	/**
	 * 基础分页功能（Json）
	 * @param listSQL 获取列表数据的SQL语句
	 * @param countSQL 获取记录总条数的SQL语句
	 * @param queryParam 查询参数
	 * @param indexPage 查询的页号
	 * @param pageSize 每页大小
	 * @return 返回一个json类型数据：{"totalCount":count, "list":array, "totalPage":pageCount}
	 */
	public JSONObject getJsonPageList(String listSQL, String countSQL, Object[] queryParam, int indexPage, int pageSize);
	
	/**
	 * 基础分页功能（Json）
	 * @param listSQL 获取列表数据的SQL语句
	 * @param countSQL 获取记录总条数的SQL语句
	 * @param queryParam 查询参数
	 * @param indexPage 查询的页号
	 * @param pageSize 每页大小
	 * @param frame 时间的格式
	 * @return 返回一个json类型数据：{"totalCount":count, "list":array, "totalPage":pageCount}
	 */
	public JSONObject getJsonPageList(String listSQL, String countSQL, Object[] queryParam, int indexPage, int pageSize,String frame);
	/**
	 * 基础分页功能（Map）
	 * @param listSQL 获取列表数据的SQL语句
	 * @param countSQL 获取记录总条数的SQL语句
	 * @param queryParam 查询参数
	 * @param indexPage 查询的页号
	 * @param pageSize 每页大小
	 * @return 返回一个map类型数据：{"totalCount":count, "list":list, "totalPage":pageCount}
	 */
	public Map<String, Object> getMapPageList(String listSQL, String countSQL, Object[] queryParam, int indexPage, int pageSize);

}
