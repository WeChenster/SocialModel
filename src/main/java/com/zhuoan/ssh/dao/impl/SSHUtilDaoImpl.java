package com.zhuoan.ssh.dao.impl;

import com.zhuoan.ssh.bean.PageUtil;
import com.zhuoan.ssh.bean.QueryParam;
import com.zhuoan.ssh.dao.SSHUtilDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class SSHUtilDaoImpl implements SSHUtilDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 获取当前session
	 * @return
	 */
	private Session getSession() {

		return sessionFactory.getCurrentSession();
	}

	/**
	 * 获取对象对应参数的类型
	 * @param value
	 * @return
	 */
	private final Type[] typesFactory(Object[] value) {

		Type[] types = new Type[value.length];

		for (int i = 0; i < value.length; i++) {
			if (value[i].getClass().getName().endsWith("String")) {
				types[i] = new StringType();
			} else if (value[i].getClass().getName().endsWith("Integer")) {
				types[i] = new IntegerType();
			} else if (value[i].getClass().getName().endsWith("Long")) {
				types[i] = new LongType();
			} else if (value[i].getClass().getName().endsWith("Float")) {
				types[i] = new FloatType();
			} else if (value[i].getClass().getName().endsWith("Double")) {
				types[i] = new DoubleType();
			} else if (value[i].getClass().getName().endsWith("Short")) {
				types[i] = new ShortType();
			} else if (value[i].getClass().getName().endsWith("Date")) {
				types[i] = new DateType();
			} else if (value[i].getClass().getName().endsWith("Boolean")) {
				types[i] = new BooleanType();
			} else if (value[i].getClass().getName().endsWith("Timestamp")) {
				types[i] = new TimestampType();
			}
		}

		return types;
	}

	@Override
	public Serializable saveObject(Object obj) {

		return getSession().save(obj);
	}

	@Override
	public Object saveAsObject(Class clazz, Object obj) {
		
		Serializable id = getSession().save(obj);
		return getSession().get(clazz, id);
	}
	
	@Override
	public boolean saveOrUpdateObject(Class clazz, Object obj) {
		
		try {
			getSession().saveOrUpdate(obj);
		} catch (HibernateException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean delObjectById(Class clazz, Serializable id) {
		
		Object obj = getSession().get(clazz, id);
		return delObject(obj);
	}

	@Override
	public boolean delObject(Object obj) {
		
		try {
			getSession().delete(obj);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean delObjectByHQL(String hql, Object[] param) {
		
		Query query = getSession().createQuery(hql); 
		if (param != null && param.length >= 1) {  
	           
			Type[] types = typesFactory(param);  
            query.setParameters(param, types);  
        } 
		if(query.executeUpdate()>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delObjectBySQL(String sql, Object[] param) {
		
		Query query = getSession().createSQLQuery(sql); 
		if (param != null && param.length >= 1) {  
	           
			Type[] types = typesFactory(param);  
            query.setParameters(param, types);  
        } 
		if(query.executeUpdate()>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updObject(Object obj) {

		try {
			getSession().update(obj);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updObjectByHQL(String hql, Object[] param) {

		Query query = getSession().createQuery(hql); 
		if (param != null && param.length >= 1) {  
	           
			Type[] types = typesFactory(param);  
            query.setParameters(param, types);  
        } 
		if(query.executeUpdate()>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updObjectBySQL(String sql, Object[] param) {

		Query query = getSession().createSQLQuery(sql); 
		if (param != null && param.length >= 1) {  
	           
			Type[] types = typesFactory(param);  
            query.setParameters(param, types);  
        } 
		if(query.executeUpdate()>0){
			return true;
		}
		return false;
	}

	@Override
	public Object getObjectById(Class<?> clazz, Serializable id) {
		
		return getSession().get(clazz, id);
	}

	@Override
	public Object getObjectByHQL(String hql, Object[] queryParam) {
		
		Query query = getSession().createQuery(hql);
		
		if (queryParam != null && queryParam.length >= 1) {  
           
			Type[] types = typesFactory(queryParam);  
            query.setParameters(queryParam, types);  
        }  
		List<?> list = query.list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Object getObjectBySQL(String sql, Object[] queryParam) {
		
		Query query = getSession().createSQLQuery(sql);
		
		if (queryParam != null && queryParam.length >= 1) {  
           
			Type[] types = typesFactory(queryParam);  
            query.setParameters(queryParam, types);  
        }  
		List<?> list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}	

	@Override
	public Object getObjectBySQL(String sql, Object[] queryParam,
			Class clazz) {
		
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(clazz);
		if (queryParam != null && queryParam.length >= 1) {
			Type[] types = typesFactory(queryParam);  
            query.setParameters(queryParam, types);  
        }  
		List<?> list = query.list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<?> getObjectList(Class<?> clazz, QueryParam queryParam,
			PageUtil pageUtil) {
		
		Criteria criteria = getSession().createCriteria(clazz);

		if (queryParam != null) {
			
			if (queryParam.getTypeMap() != null) {
				
				// 遍历操作条件
				for (Iterator<String> it = queryParam.getTypeMap().keySet().iterator(); it
						.hasNext();) {
					
					String key = it.next();
					
					String type = queryParam.getTypeMap().get(key);
					
					if ("eq".equals(type)) { // 等于
						
						criteria.add(Restrictions.eq(key,
								queryParam.getParamMap().get(key)));
						
					} else if ("ne".equals(type)) { // 不等于
						
						criteria.add(Restrictions.not(Restrictions.eq(key, queryParam
								.getParamMap().get(key))));
						
					} else if ("gt".equals(type)) { // 大于
						
						criteria.add(Restrictions.gt(key, queryParam.getParamMap().get(key)));
						
					} else if ("lt".equals(type)) { // 小于
						
						criteria.add(Restrictions.lt(key, queryParam.getParamMap().get(key)));
						
					} else if ("ge".equals(type)) { // 大于等于
						
						criteria.add(Restrictions.ge(key, queryParam.getParamMap().get(key)));
						
					} else if ("le".equals(type)) { // 小于等于
						
						criteria.add(Restrictions.le(key, queryParam.getParamMap().get(key)));
						
					} else if ("bet".equals(type)) { // 介于...之间
						
						Object[] i = (Object[]) queryParam.getParamMap().get(key);
						
						criteria.add(Restrictions.between(key, i[0], i[1]));
						
					} else if ("like".equals(type)) { // like查询

						criteria.add(Restrictions.like(key, queryParam
								.getParamMap().get(key)));

					} else if ("isNull".equals(type)) { // 为null

						criteria.add(Restrictions.isNull(key));

					} else if ("notNull".equals(type)) { // 不为null

						criteria.add(Restrictions.isNotNull(key));

					} else if ("isEmpty".equals(type)) { // 为空

						criteria.add(Restrictions.isEmpty(key));

					} else if ("notEmpty".equals(type)) { // 不为空

						criteria.add(Restrictions.isNotEmpty(key));

					}
					// ... 可拓展
					
				}
			}
			
			if (queryParam.getOrderMap() != null) {
				
				for (Iterator<String> it = queryParam.getOrderMap().keySet()
						.iterator(); it.hasNext();) {
					
					String key = it.next();
					
					if ("desc".equals(queryParam.getOrderMap().get(key))) {
						
						criteria.addOrder(Order.desc(key));
					} else {
						
						criteria.addOrder(Order.asc(key));
					}
				}
			}
		}
		
		if (pageUtil == null)
			return criteria.list();
		else
			return criteria
				.setFirstResult(
						(pageUtil.getPageIndex() - 1) * pageUtil.getPageSize())
				.setMaxResults(pageUtil.getPageSize()).list();
	}

	@Override
	public List<?> getObjectList(String hql, Object[] queryParam,
			PageUtil pageUtil) {

		Query query = getSession().createQuery(hql);
		
		if (queryParam != null && queryParam.length >= 1) {  
           
			Type[] types = typesFactory(queryParam);  
            query.setParameters(queryParam, types);  
        }  
		if(pageUtil==null)
			return query.list();
		else
			return query
					.setFirstResult(
							(pageUtil.getPageIndex() - 1) * pageUtil.getPageSize())
					.setMaxResults(pageUtil.getPageSize()).list();
	}

	@Override
	public List<?> getObjectListBySQL(String sql, Object[] queryParam,
			PageUtil pageUtil) {
		
		Query query = getSession().createSQLQuery(sql);   
		
		if (queryParam != null && queryParam.length >= 1) {  
           
			Type[] types = typesFactory(queryParam);  
            query.setParameters(queryParam, types);  
        }  
		
		if(pageUtil==null || pageUtil.getPageIndex()<1)
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		else
			return query
					.setFirstResult((pageUtil.getPageIndex() - 1) * pageUtil.getPageSize())
					.setMaxResults(pageUtil.getPageSize())
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	@Override
	public List<?> getObjectListBySQL(String sql, Object[] queryParam, Integer indexPage, Integer pageSize) {
		
		Query query = getSession().createSQLQuery(sql);   
		
		if (queryParam != null && queryParam.length >= 1) {  
           
			Type[] types = typesFactory(queryParam);  
            query.setParameters(queryParam, types);  
        }  
		
		return query
				.setFirstResult((indexPage - 1) * pageSize)
				.setMaxResults(pageSize)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<?> getObjectListBySQL(String sql, Object[] queryParam,
			Class clazz) {
		
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(clazz);
		if (queryParam != null && queryParam.length >= 1) {
			Type[] types = typesFactory(queryParam);  
            query.setParameters(queryParam, types);  
        }  
		return query.list();
	}

	@Override
	public Integer getCount(String sql, Object[] queryParam) {

		Query query = getSession().createSQLQuery(sql);   
		
		if (queryParam != null && queryParam.length >= 1) {  
           
			Type[] types = typesFactory(queryParam);  
            query.setParameters(queryParam, types);  
        }  
		
		Object obj = query.uniqueResult();
		
		if(obj!=null){
			return ((BigInteger) obj).intValue();
		}
		return 0;
	}

	@Override
	public Integer getCount(Class<?> clazz, String colName,
			QueryParam queryParam) {
		
		Criteria criteria = getSession().createCriteria(clazz);
		criteria.setProjection(Projections.count(colName));
		
		if (queryParam != null) {
			
			if (queryParam.getTypeMap() != null) {
				// 遍历操作条件
				for (Iterator<String> it = queryParam.getTypeMap().keySet().iterator(); it
						.hasNext();) {
					
					String key = it.next();
					
					String type = queryParam.getTypeMap().get(key);
					
					if ("eq".equals(type)) { // 等于
						
						criteria.add(Restrictions.eq(key,
								queryParam.getParamMap().get(key)));
						
					} else if ("ne".equals(type)) { // 不等于
						
						criteria.add(Restrictions.not(Restrictions.eq(key, queryParam
								.getParamMap().get(key))));
						
					} else if ("gt".equals(type)) { // 大于
						
						criteria.add(Restrictions.gt(key, queryParam.getParamMap().get(key)));
						
					} else if ("lt".equals(type)) { // 小于
						
						criteria.add(Restrictions.lt(key, queryParam.getParamMap().get(key)));
						
					} else if ("ge".equals(type)) { // 大于等于
						
						criteria.add(Restrictions.ge(key, queryParam.getParamMap().get(key)));
						
					} else if ("le".equals(type)) { // 小于等于
						
						criteria.add(Restrictions.le(key, queryParam.getParamMap().get(key)));
						
					} else if ("bet".equals(type)) { // 介于...之间
						
						Object[] i = (Object[]) queryParam.getParamMap().get(key);
						
						criteria.add(Restrictions.between(key, i[0], i[1]));
						
					} else if ("like".equals(type)) { // like查询
						
						criteria.add(Restrictions.like(key, queryParam
								.getParamMap().get(key)));
						
					} else if ("isNull".equals(type)) { // 为null
						
						criteria.add(Restrictions.isNull(key));
						
					} else if ("notNull".equals(type)) { // 不为null
						
						criteria.add(Restrictions.isNotNull(key));
						
					} else if ("isEmpty".equals(type)) { // 为空
						
						criteria.add(Restrictions.isEmpty(key));
						
					} else if ("notEmpty".equals(type)) { // 不为空
						
						criteria.add(Restrictions.isNotEmpty(key));
						
					}
					// ... 可拓展
					
				}
			}
		}
		
		Object obj = criteria.uniqueResult();
		
		if(obj!=null){
			return (Integer) obj;
		}
		return 0;
	}

	@Override
	public Object getSum(String sql, Object[] queryParam) {

		Query query = getSession().createSQLQuery(sql);   
		
		if (queryParam != null && queryParam.length >= 1) {  
           
			Type[] types = typesFactory(queryParam);  
            query.setParameters(queryParam, types);  
        }  
		return query.uniqueResult();
	}

	@Override
	public Object getSum(Class<?> clazz, String colName, QueryParam queryParam) {
	
		Criteria criteria = getSession().createCriteria(clazz);
		criteria.setProjection(Projections.sum(colName));

		// 遍历操作条件
		for (Iterator<String> it = queryParam.getTypeMap().keySet().iterator(); it
				.hasNext();) {

			String key = it.next();

			String type = queryParam.getTypeMap().get(key);

			if ("eq".equals(type)) { // 等于

				criteria.add(Restrictions.eq(key,
						queryParam.getParamMap().get(key)));

			} else if ("ne".equals(type)) { // 不等于

				criteria.add(Restrictions.not(Restrictions.eq(key, queryParam
						.getParamMap().get(key))));

			} else if ("gt".equals(type)) { // 大于

				criteria.add(Restrictions.gt(key, queryParam.getParamMap().get(key)));

			} else if ("lt".equals(type)) { // 小于

				criteria.add(Restrictions.lt(key, queryParam.getParamMap().get(key)));

			} else if ("ge".equals(type)) { // 大于等于

				criteria.add(Restrictions.ge(key, queryParam.getParamMap().get(key)));

			} else if ("le".equals(type)) { // 小于等于

				criteria.add(Restrictions.le(key, queryParam.getParamMap().get(key)));

			} else if ("bet".equals(type)) { // 介于...之间

				Object[] i = (Object[]) queryParam.getParamMap().get(key);
				
				criteria.add(Restrictions.between(key, i[0], i[1]));

			} else if ("like".equals(type)) { // like查询

				criteria.add(Restrictions.like(key, queryParam
						.getParamMap().get(key)));

			} else if ("isNull".equals(type)) { // 为null

				criteria.add(Restrictions.isNull(key));

			} else if ("notNull".equals(type)) { // 不为null

				criteria.add(Restrictions.isNotNull(key));

			} else if ("isEmpty".equals(type)) { // 为空

				criteria.add(Restrictions.isEmpty(key));

			} else if ("notEmpty".equals(type)) { // 不为空

				criteria.add(Restrictions.isNotEmpty(key));

			}
			// ...

		}

		return criteria.uniqueResult();

	}

	@Override
	public Object getAvg(String sql, Object[] queryParam) {
		
		Query query = getSession().createSQLQuery(sql);   
		
		if (queryParam != null && queryParam.length >= 1) {  
           
			Type[] types = typesFactory(queryParam);  
            query.setParameters(queryParam, types);  
        }  
		return query.uniqueResult();
	}

	@Override
	public Object getAvg(Class<?> clazz, String colName, QueryParam queryParam) {
		
		Criteria criteria = getSession().createCriteria(clazz);
		
		criteria.setProjection(Projections.avg(colName));
		
		if (queryParam != null) {
			
			if (queryParam.getTypeMap() != null) {
				// 遍历操作条件
				for (Iterator<String> it = queryParam.getTypeMap().keySet().iterator(); it
						.hasNext();) {
					
					String key = it.next();
					
					String type = queryParam.getTypeMap().get(key);
					
					if ("eq".equals(type)) { // 等于
						
						criteria.add(Restrictions.eq(key,
								queryParam.getParamMap().get(key)));
						
					} else if ("ne".equals(type)) { // 不等于
						
						criteria.add(Restrictions.not(Restrictions.eq(key, queryParam
								.getParamMap().get(key))));
						
					} else if ("gt".equals(type)) { // 大于
						
						criteria.add(Restrictions.gt(key, queryParam.getParamMap().get(key)));
						
					} else if ("lt".equals(type)) { // 小于
						
						criteria.add(Restrictions.lt(key, queryParam.getParamMap().get(key)));
						
					} else if ("ge".equals(type)) { // 大于等于
						
						criteria.add(Restrictions.ge(key, queryParam.getParamMap().get(key)));
						
					} else if ("le".equals(type)) { // 小于等于
						
						criteria.add(Restrictions.le(key, queryParam.getParamMap().get(key)));
						
					} else if ("bet".equals(type)) { // 介于...之间
						
						Object[] i = (Object[]) queryParam.getParamMap().get(key);
						
						criteria.add(Restrictions.between(key, i[0], i[1]));
						
					} else if ("like".equals(type)) { // like查询
						
						criteria.add(Restrictions.like(key, queryParam
								.getParamMap().get(key)));
						
					} else if ("isNull".equals(type)) { // 为null
						
						criteria.add(Restrictions.isNull(key));
						
					} else if ("notNull".equals(type)) { // 不为null
						
						criteria.add(Restrictions.isNotNull(key));
						
					} else if ("isEmpty".equals(type)) { // 为空
						
						criteria.add(Restrictions.isEmpty(key));
						
					} else if ("notEmpty".equals(type)) { // 不为空
						
						criteria.add(Restrictions.isNotEmpty(key));
						
					}
					// ...
				}
			}
		}
		
		return criteria.uniqueResult();
	}

	@Override
	public JSONObject getJsonPageList(String listSQL, String countSQL, Object[] queryParam,
			int indexPage, int pageSize) {
		
		List<?> list = getObjectListBySQL(listSQL, queryParam, indexPage, pageSize);
		int count = getCount(countSQL, queryParam);
		int pageCount = ((count + pageSize - 1)/pageSize);
		
		JsonConfig config=new JsonConfig();  
        //注册一个json对象的转换器,如果转换Timestamp类型，调用该转换器  
        config.registerJsonValueProcessor(Timestamp.class,new JsonValueProcessor() {  
            public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {  
                SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                Timestamp d=(Timestamp) arg1;  
                return sd.format(d);  
            }  
            public Object processArrayValue(Object arg0, JsonConfig arg1) {  
                return null;  
            }  
        });  
		JSONArray array = JSONArray.fromObject(list, config);
		JSONObject obj = new JSONObject();
		obj.put("totalCount", count);
		obj.put("list", array);
		obj.put("totalPage", pageCount);
		obj.put("pageIndex", indexPage);
		obj.put("pageSize", pageSize);
		return obj;
	}

	@Override
	public Map<String, Object> getMapPageList(String listSQL, String countSQL, Object[] queryParam,
			int indexPage, int pageSize) {
		
		List<?> list = getObjectListBySQL(listSQL, queryParam, indexPage, pageSize);
		int count = getCount(countSQL, queryParam);
		int pageCount = ((count + pageSize - 1)/pageSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", count);
		map.put("list", list);
		map.put("totalPage", pageCount);
		return map;
	}

	@Override
	public JSONObject getJsonPageList(String listSQL, String countSQL,
			Object[] queryParam, int indexPage, int pageSize, final String frame) {
		// TODO Auto-generated method stub
		List<?> list = getObjectListBySQL(listSQL, queryParam, indexPage, pageSize);
		int count = getCount(countSQL, queryParam);
		int pageCount = ((count + pageSize - 1)/pageSize);
		
		JsonConfig config=new JsonConfig();  
        //注册一个json对象的转换器,如果转换Timestamp类型，调用该转换器  
        config.registerJsonValueProcessor(Timestamp.class,new JsonValueProcessor() {  
            public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {  
                SimpleDateFormat sd=new SimpleDateFormat(frame);  
                Timestamp d=(Timestamp) arg1;  
                return sd.format(d);  
            }  
            public Object processArrayValue(Object arg0, JsonConfig arg1) {  
                return null;  
            }  
        });  
		JSONArray array = JSONArray.fromObject(list, config);
		JSONObject obj = new JSONObject();
		obj.put("totalCount", count);
		obj.put("list", array);
		obj.put("totalPage", pageCount);
		return obj;
	}
}
