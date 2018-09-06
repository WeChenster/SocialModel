package com.zhuoan.ssh.biz;

import java.io.Serializable;
import java.util.List;

import com.zhuoan.ssh.bean.PageUtil;
import com.zhuoan.ssh.bean.QueryParam;

public interface SSHUtilBiz {

	/**
	 * 将实体对象持久化（保存到数据库）
	 * @param obj 
	 * @return
	 */
	public Serializable saveObject(Object obj);

	/**
	 * 根据实体对象ID获取实体对象
	 * @param clazz
	 * @param id
	 * @return
	 */
	
	public Object getObjectById(Class<?> clazz, Serializable id);
	
	/**
	 * 更新实体对象信息
	 * @param obj
	 */
	public Boolean updateObject(Object obj);
	
	
	/**
	 * 将实体对象删除（慎用：物理删除）
	 * @param obj
	 */
	public Boolean deleteObject(Object obj);
	
	/**
	 * 根据id将实体对象删除
	 * @param clazz 实体类型 实体类型
	 * @param id 对象编号（通常为字符串或数字）
	 * @return 成功返回true，失败返回false
	 */
	public boolean delObjectById(Class clazz, Serializable id);
	
	
	public Boolean updateObjectBySQL(String sql, Object[] queryParam);
	
	
	/**
	 * 根据条件获取列colName的总数
	 * @param clazz
	 * @param colName
	 * @param queryParam
	 * @return
	 */
	public Integer getObjectCount(Class<?> clazz, String colName, QueryParam queryParam);
	
	/**
	 * 根据条件获取记录总数
	 * @param hql
	 * @param queryParam
	 * @return
	 */
	public Integer getObjectCount(String hql, Object[] queryParam);
	
	/**
	 * 根据条件获取记录列表
	 * @param hql
	 * @param queryParam
	 * @param pageUtil
	 * @return
	 */
	public List<?> getObjectList(String hql, Object[] queryParam, PageUtil pageUtil);

	/**
	 * 根据条件获取记录列表
	 * @param sql
	 * @param queryParam
	 * @param pageUtil
	 * @return
	 */
	public List<?> getObjectListBySQL(String sql, Object[] queryParam, PageUtil pageUtil);
	
	/**
	 * 根据条件获取记录列表
	 * @param clazz
	 * @param queryParam
	 * @param pageUtil
	 * @return
	 */
	
	public List<?> getObjectList(Class<?> clazz, QueryParam queryParam, PageUtil pageUtil);
	
	/**
	 * 根据条件获取总页数
	 * @param clazz
	 * @param queryParam
	 * @param pageUtil 
	 * @return
	 */
	
	public PageUtil getPageCount(Class<?> clazz, QueryParam queryParam, PageUtil pageUtil);
	
	/**
	 * 根据列名取数据列总和
	 * @param clazz
	 * @param colName
	 * @param queryParam
	 * @return
	 */
	
	public Object getSum(Class<?> clazz, String colName, QueryParam queryParam);
	
	/**
	 * 根据列名取该数据列平均值
	 * @param clazz
	 * @param colName
	 * @param queryParam
	 * @return
	 */
	public Object getAvg(Class<?> clazz, String colName, QueryParam queryParam);
	
}
