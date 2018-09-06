package com.zhuoan.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 时间处理工具类
 * @author nanoha
 *
 */
public class TimeUtil {

	private static SimpleDateFormat ymdhms=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat ymd=new SimpleDateFormat("yyyy-MM-dd");
	private static Calendar calendar = Calendar.getInstance();

	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getNowDate(){
		String time=ymdhms.format(new Date());
		return time;
	}
	/**
	 * 指定格式获取当前时间
	 * @param format
	 * @return
	 */
	public static String getNowDate(String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		String time=sdf.format(new Date());
		return time;
	}
	
	/**
	 * 获得月份+日期
	 * @return X月X日(String)
	 */
	public static String getMonAndDay()
	{
		String time=ymdhms.format(new Date());
		Timestamp timestamp=Timestamp.valueOf(time);
		calendar.setTime(timestamp);
		int month=calendar.get(Calendar.MONTH) + 1;
		int day=calendar.get(Calendar.DATE);
		return month+"月"+day+"日";
	}
	
	/**
	 * 将字符串转化为时间
	 * @param timestr
	 * @return
	 * @throws ParseException 
	 */
	public static String StrTsf(String timestr) throws ParseException
	{
		Date date=ymd.parse(timestr);
		return ymdhms.format(date);
	}
	
	public static Date StrTsfToDate(String timestr,String format) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date date=sdf.parse(timestr);
		return date;
	}
	
	/**
	 * 按格式转化指定timestamp
	 * @param time
	 * @param format
	 * @return
	 */
	public static String transTimeStampToStr(Timestamp time,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date nowDate=new Date();
		nowDate.setTime(time.getTime());
		return sdf.format(nowDate);
	}
	
	/**
	 * 转化查询结果中的对应timestamp值
	 * @param array
	 * @param key
	 * @return
	 */
	public static JSONArray transTimestamp(JSONArray array,String key)
	{
		for(int i=0;i<array.size();i++)
		{
			JSONObject tmpobj=array.getJSONObject(i);
			if(("null").equals(tmpobj.getString(key)))
				continue;
			else{
				JSONObject time=tmpobj.getJSONObject(key);
				Date nowDate=new Date();
				nowDate.setTime(time.getLong("time"));
				tmpobj.element(key, ymd.format(nowDate));
			}
		}
		return array;
	}
	
	public static JSONArray transTimestamp(JSONArray array,String key,String format)
	{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		for(int i=0;i<array.size();i++)
		{
			JSONObject tmpobj=array.getJSONObject(i);
			if(("null").equals(tmpobj.getString(key)))
				continue;
			else{
				JSONObject time=tmpobj.getJSONObject(key);
				Date nowDate=new Date();
				nowDate.setTime(time.getLong("time"));
				tmpobj.element(key, sdf.format(nowDate));
			}
		}
		return array;
	}
	
	public static JSONArray transTimestamp(JSONArray array,String[] keys,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		for(int i=0;i<array.size();i++)
		{
			JSONObject tmpobj=array.getJSONObject(i);
			for(String key:keys){
				if(("null").equals(tmpobj.getString(key)))
					continue;
				else{
					JSONObject time=tmpobj.getJSONObject(key);
					Date nowDate=new Date();
					nowDate.setTime(time.getLong("time"));
					tmpobj.element(key, sdf.format(nowDate));
				}
			}
		}
		return array;
	}
	
	public static JSONArray transTimestampToMd(JSONArray array,String key)
	{
		for(int i=0;i<array.size();i++)
		{
			JSONObject tmpobj=array.getJSONObject(i);
			if(("null").equals(tmpobj.getString(key)))
				continue;
			else{
				JSONObject time=tmpobj.getJSONObject(key);
				Date nowDate=new Date();
				nowDate.setTime(time.getLong("time"));
				calendar.setTime(nowDate);
				int month=calendar.get(Calendar.MONTH) + 1;
				int day=calendar.get(Calendar.DATE);
				tmpobj.element(key, month+"月"+day+"日");
			}			
		}
		return array;
	}
	
	/**
	 * timestamp
	 * @param oData
	 * @param sSdfFormat
	 * @param sKey
	 * @return
	 */
	public static JSONObject transTimeStamp(JSONObject oData,String sSdfFormat,String sKey)
	{
		if(!("null").equals(oData.getString(sKey)))
		{
			SimpleDateFormat format=new SimpleDateFormat(sSdfFormat);
			Date nowDate=new Date();
			JSONObject time=oData.getJSONObject(sKey);
			nowDate.setTime(time.getLong("time"));
			oData.element(sKey, format.format(nowDate));
		}		
		return oData;
	}
	
	public static JSONObject transTimeStamp(JSONObject oData,String sSdfFormat,String[] sKeys)
	{
		for(String sKey:sKeys)
		if(!("null").equals(oData.getString(sKey)))
		{
			SimpleDateFormat format=new SimpleDateFormat(sSdfFormat);
			Date nowDate=new Date();
			JSONObject time=oData.getJSONObject(sKey);
			nowDate.setTime(time.getLong("time"));
			oData.element(sKey, format.format(nowDate));
		}		
		return oData;
	}
	
	/**
	 * 判断大两个时间的小steartTime>ndTime?
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isLatter(String startTime,String endTime){
		calendar.setTime(Timestamp.valueOf(startTime));
		long start=calendar.getTimeInMillis();
		calendar.setTime(Timestamp.valueOf(endTime));
		long end=calendar.getTimeInMillis();
		return start>end?true:false;
	}
	
	/**
	 * 获得本周的周一或周日
	 * @param isMon
	 * @return
	 */
	public static String getMonOrSat(boolean isMon){
		dayCount(new Date());
		if(isMon)
			return ymd.format(calendar.getTime());
		else{
			calendar.add(Calendar.DATE, 6);  
			return ymd.format(calendar.getTime());
		}
			
	}	
	private static Calendar dayCount(Date time){
		calendar.setTime(time);
		int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天 
		if(1 == dayWeek) 
			calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek()-day);
		return calendar;
	}
	
	/**
	 * 获得当前日期是星期几
	 * @param dt
	 * @return
	 */
	public static JSONArray transArrayToWeekOfDate(JSONArray array,String key,String newKey)
	{
		for(int i=0;i<array.size();i++)
        {
        	JSONObject tmpobj=array.getJSONObject(i);
        	if(("null").equals(tmpobj.getString(key)))
        		continue;
        	else{
				JSONObject time=tmpobj.getJSONObject(key);
				Date nowDate=new Date();
				nowDate.setTime(time.getLong("time"));
				String day=getWeekOfDate(nowDate);
				tmpobj.element(newKey, day);
			}
        }
		return array;
	}
	public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
	
	/**
	 * 获得两个日期间间隔的天数
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static String getDaysBetweenTwoTime(String time1,String time2,Long Time)
	{
		Timestamp start=Timestamp.valueOf(time1);
		Timestamp end=Timestamp.valueOf(time2);

		Long tempString = (Math.abs(start.getTime() - end.getTime())) / Time;
		return  String.valueOf(tempString);
	}
	
	/**
	 * 在指定日期基础上加n个月
	 * @param Time
	 * @param addCount
	 * @return
	 */
	public static String addTimeBaseOnNowTime(String Time,int addCount)
	{
		calendar.setTime(Timestamp.valueOf(Time));
		calendar.add(Calendar.MONTH, addCount);
		return ymdhms.format(calendar.getTime());
	}
	
	/**
	 * 在指定日期基础上加任意天数
	 * @param Time
	 * @param addCount
	 * @param format
	 * @return
	 */
	public static String addDaysBaseOnNowTime(String Time,int addCount,String format){
		calendar.setTime(Timestamp.valueOf(Time));
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + addCount); 
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 在指定日期基础上加任意小时数
	 * @param Time
	 * @param addCount
	 * @param format
	 * @return
	 */
	public static String addHoursBaseOnNowTime(String Time,int addCount,String format){
		calendar.setTime(Timestamp.valueOf(Time));
		calendar.add(Calendar.HOUR_OF_DAY, addCount);
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 在指定日期基础上加任意分钟数
	 * @param Time
	 * @param addCount
	 * @param format
	 * @return
	 */
	public static String addMinutesBaseOnNowTime(String Time,int addCount,String format){
		calendar.setTime(Timestamp.valueOf(Time));
		calendar.add(Calendar.MINUTE, addCount);
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 在指定日期基础上加任意秒
	 * @param Time
	 * @param addCount
	 * @return
	 */
	public static String addSecondsBaseOnNowTime(String Time,int addCount){
		calendar.setTime(Timestamp.valueOf(Time));
		calendar.add(Calendar.SECOND, addCount);
		return ymdhms.format(calendar.getTime());
	}

	/**
	 * 获得指定月份日期
	 * @param time
	 * @return
	 */
	public static int getMonthDays(String time)
	{
		calendar.setTime(Timestamp.valueOf(time));
		return calendar.getActualMaximum((Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * 毫秒转化时分秒
	 * @param ms
	 * @return
	 */
	public static JSONObject formatTime(Long ms) {  
	    Integer ss = 1000;  
	    Integer mi = ss * 60;  
	    Integer hh = mi * 60;  
	    Integer dd = hh * 24;  
	  
	    Long day = ms / dd;  
	    Long hour = (ms - day * dd) / hh;  
	    Long minute = (ms - day * dd - hour * hh) / mi;  
	    Long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
	    Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  
	      
	    JSONObject result=new JSONObject();
	    result.element("day", day).element("hour", hour)
	    	  .element("minute", minute).element("second", second)
	    	  .element("milliSecond", milliSecond);
//	    StringBuffer sb = new StringBuffer();  
//	    if(day > 0) {  
//	        sb.append(day+"天");  
//	    }  
//	    if(hour > 0) {  
//	        sb.append(hour+"小时");  
//	    }  
//	    if(minute > 0) {  
//	        sb.append(minute+"分");  
//	    }  
//	    if(second > 0) {  
//	        sb.append(second+"秒");  
//	    }  
//	    if(milliSecond > 0) {  
//	        sb.append(milliSecond+"毫秒");  
//	    }  
//	    return sb.toString();
	    return result;
	} 
	/**
	 * 在指定日期基础上加任意分钟数
	 * @param Time
	 * @param addCount
	 * @param format
	 * @return
	 */
	public static String addMinuteBaseOnNowTime(String Time,int addCount,String format){
		calendar.setTime(Timestamp.valueOf(Time));
		calendar.add(Calendar.MINUTE, addCount);
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(calendar.getTime());
	}
	
	
	/**
	 * list转jsonarray 时间格式设置
	 * @param list
	 * @return
	 */
	public static JSONArray listToJSONArray(List list){
		
		JsonConfig config=new JsonConfig();  
        //注册一个json对象的转换器,如果转换Timestamp类型，调用该转换器  
        config.registerJsonValueProcessor(Timestamp.class,new JsonValueProcessor() {  
            public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {  

            	if(arg1!=null){
            		
            		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            		Timestamp d=(Timestamp) arg1;  
            		return sd.format(d);  
            	}
            	return arg1;
                 
            }  
            public Object processArrayValue(Object arg0, JsonConfig arg1) {  
                return null;  
            }  
        });  
        
		return JSONArray.fromObject(list, config);
	}
	
	
	/**
	 * object转jsonObject 时间设置
	 * @param obj
	 * @return
	 */
	public static JSONObject objectToJSONObject(Object obj){
		
		JsonConfig config=new JsonConfig();  
        //注册一个json对象的转换器,如果转换Timestamp类型，调用该转换器  
        config.registerJsonValueProcessor(Timestamp.class,new JsonValueProcessor() {  
            public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {  
                
            	if(arg1!=null){
            		
            		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            		Timestamp d=(Timestamp) arg1;  
            		return sd.format(d);  
            	}
            	return arg1;
                
            }  
            public Object processArrayValue(Object arg0, JsonConfig arg1) {  
                return null;  
            }  
        });  
        
		return JSONObject.fromObject(obj, config);
	}
	
}
