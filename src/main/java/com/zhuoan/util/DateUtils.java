package com.zhuoan.util;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.util.StringUtils;

/**
 * 
 * 类描述：时间操作定义类
 * 
 * @author: zhuoan
 * @date： 日期：2015-8-18 时间：下午12:15:03
 * @version 1.0
 */
public class DateUtils extends PropertyEditorSupport {

	// 各种时间格式
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
			"yyyyMMdd");
	public static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat yyyy_MM_dd_wz = new SimpleDateFormat(
			"yyyy年MM月dd日");
	public static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat(
			"HH:mm");
	public static final SimpleDateFormat time_sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat datetime_sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final DateFormat  timedate_sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final DateFormat  MM_dd = new SimpleDateFormat(
			"MM-dd");

	// 以毫秒表示的时间
	private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
	private static final long HOUR_IN_MILLIS = 3600 * 1000;
	private static final long MINUTE_IN_MILLIS = 60 * 1000;
	private static final long SECOND_IN_MILLIS = 1000;
	private static Calendar calendar = Calendar.getInstance();

	// 指定模式的时间格式
	private static SimpleDateFormat getSDFormat(String pattern) {

		return new SimpleDateFormat(pattern);
	}

	/**
	 * 当前日历，这里用中国时间表示
	 * 
	 * @return 以当地时区表示的系统当前日历
	 */
	public static Calendar getCalendar() {

		return Calendar.getInstance();
	}

	/**
	 * 指定毫秒数表示的日历
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日历
	 */
	public static Calendar getCalendar(long millis) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(millis));
		return cal;
	}

	/**
	 * 时间转字符串
	 * 
	 * @return
	 */
	public static String date2SStr() {

		Date date = getDate();
		if (null == date) {
			return null;
		}
		return yyyyMMdd.format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// getDate
	// 各种方式获取的Date
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 当前日期
	 * 
	 * @return 系统当前时间
	 */
	public static Date getDate() {

		return new Date();
	}

	/**
	 * 指定毫秒数表示的日期
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日期
	 */
	public static Date getDate(long millis) {

		return new Date(millis);
	}

	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamptoStr(Timestamp time) {

		Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return yyyy_MM_dd.format(date);
	}
	
	public static String timestamptoStrMMdd(Timestamp time) {
		
		Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return MM_dd.format(date);
	}
	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestampDate(Timestamp time) {
		
		Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return yyyy_MM_dd_wz.format(date);
	}

	/**
	 * 字符串转换时间戳
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {

		Date date = str2Date(str, yyyy_MM_dd);
		return new Timestamp(date.getTime());
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @param sdf
	 * @return
	 */
	public static Date str2Date(String str, SimpleDateFormat sdf) {

		if (null == str || "".equals(str)) {
			return null;
		}
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(SimpleDateFormat date_sdf) {

		Date date = getDate();
		if (date == null) {
			return null;
		}
		return date_sdf.format(date);
	}

	/**
	 * 格式化时间
	 * 
	 * @param data
	 * @param format
	 * @return
	 */
	public static String dataformat(String data, String format) {

		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sformat.parse(data);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return sformat.format(date);
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, SimpleDateFormat date_sdf) {

		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String getDate(String format) {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 指定毫秒数的时间戳
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数的时间戳
	 */
	public static Timestamp getTimestamp(long millis) {

		return new Timestamp(millis);
	}

	/**
	 * 以字符形式表示的时间戳
	 * 
	 * @param time
	 *            毫秒数
	 * @return 以字符形式表示的时间戳
	 */
	public static Timestamp getTimestamp(String time) {

		return new Timestamp(Long.parseLong(time));
	}

	/**
	 * 系统当前的时间戳
	 * 
	 * @return 系统当前的时间戳
	 */
	public static Timestamp getTimestamp() {

		return new Timestamp(new Date().getTime());
	}

	/**
	 * 指定日期的时间戳
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期的时间戳
	 */
	public static Timestamp getTimestamp(Date date) {

		return new Timestamp(date.getTime());
	}

	/**
	 * 指定日历的时间戳
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的时间戳
	 */
	public static Timestamp getCalendarTimestamp(Calendar cal) {

		return new Timestamp(cal.getTime().getTime());
	}

	public static Timestamp gettimestamp() {

		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		java.sql.Timestamp buydate = java.sql.Timestamp.valueOf(nowTime);
		return buydate;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// getMillis
	// 各种方式获取的Millis
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 系统时间的毫秒数
	 * 
	 * @return 系统时间的毫秒数
	 */
	public static long getMillis() {

		return new Date().getTime();
	}

	/**
	 * 指定日历的毫秒数
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的毫秒数
	 */
	public static long getMillis(Calendar cal) {

		return cal.getTime().getTime();
	}

	/**
	 * 指定日期的毫秒数
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期的毫秒数
	 */
	public static long getMillis(Date date) {

		return date.getTime();
	}

	/**
	 * 指定时间戳的毫秒数
	 * 
	 * @param ts
	 *            指定时间戳
	 * @return 指定时间戳的毫秒数
	 */
	public static long getMillis(Timestamp ts) {

		return ts.getTime();
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatDate
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：年-月-日
	 * 
	 * @return 默认日期按“年-月-日“格式显示
	 */
	public static String formatDate() {

		return yyyy_MM_dd.format(getCalendar().getTime());
	}

	/**
	 * 获取时间字符串
	 */
	public static String getDataString(SimpleDateFormat formatstr) {

		return formatstr.format(getCalendar().getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“年-月-日“格式显示
	 */
	public static String formatDate(Calendar cal) {

		return yyyy_MM_dd.format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“年-月-日“格式显示
	 */
	public static String formatDate(Date date) {

		return yyyy_MM_dd.format(date);
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：年-月-日
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“年-月-日“格式显示
	 */
	public static String formatDate(long millis) {

		return yyyy_MM_dd.format(new Date(millis));
	}

	/**
	 * 默认日期按指定格式显示
	 * 
	 * @param pattern
	 *            指定的格式
	 * @return 默认日期按指定格式显示
	 */
	public static String formatDate(String pattern) {

		return getSDFormat(pattern).format(getCalendar().getTime());
	}

	/**
	 * 指定日期按指定格式显示
	 * 
	 * @param cal
	 *            指定的日期
	 * @param pattern
	 *            指定的格式
	 * @return 指定日期按指定格式显示
	 */
	public static String formatDate(Calendar cal, String pattern) {

		return getSDFormat(pattern).format(cal.getTime());
	}

	/**
	 * 指定日期按指定格式显示
	 * 
	 * @param date
	 *            指定的日期
	 * @param pattern
	 *            指定的格式
	 * @return 指定日期按指定格式显示
	 */
	public static String formatDate(Date date, String pattern) {

		return getSDFormat(pattern).format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatTime
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：年-月-日 时：分
	 * 
	 * @return 默认日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime() {

		return time_sdf.format(getCalendar().getTime());
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(long millis) {

		return time_sdf.format(new Date(millis));
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(Calendar cal) {

		return time_sdf.format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(Date date) {

		return time_sdf.format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatShortTime
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：时：分
	 * 
	 * @return 默认日期按“时：分“格式显示
	 */
	public static String formatShortTime() {

		return short_time_sdf.format(getCalendar().getTime());
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：时：分
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“时：分“格式显示
	 */
	public static String formatShortTime(long millis) {

		return short_time_sdf.format(new Date(millis));
	}

	/**
	 * 指定日期的默认显示，具体格式：时：分
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“时：分“格式显示
	 */
	public static String formatShortTime(Calendar cal) {

		return short_time_sdf.format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：时：分
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“时：分“格式显示
	 */
	public static String formatShortTime(Date date) {

		return short_time_sdf.format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// parseDate
	// parseCalendar
	// parseTimestamp
	// 将字符串按照一定的格式转化为日期或时间
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws ParseException
	 * @throws AIDateFormatException
	 */
	public static Date parseDate(String src, String pattern)
			throws ParseException {

		return getSDFormat(pattern).parse(src);

	}

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws ParseException
	 * @throws AIDateFormatException
	 */
	public static Calendar parseCalendar(String src, String pattern)
			throws ParseException {

		Date date = parseDate(src, pattern);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static String formatAddDate(String src, String pattern, int amount)
			throws ParseException {

		Calendar cal;
		cal = parseCalendar(src, pattern);
		cal.add(Calendar.DATE, amount);
		return formatDate(cal);
	}

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的时间戳
	 * @throws ParseException
	 * @throws AIDateFormatException
	 */
	public static Timestamp parseTimestamp(String src, String pattern)
			throws ParseException {

		Date date = parseDate(src, pattern);
		return new Timestamp(date.getTime());
	}

	// ////////////////////////////////////////////////////////////////////////////
	// dateDiff
	// 计算两个日期之间的差值
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 计算两个时间之间的差值，根据标志的不同而不同
	 * 
	 * @param flag
	 *            计算标志，表示按照年/月/日/时/分/秒等计算
	 * @param calSrc
	 *            减数
	 * @param calDes
	 *            被减数
	 * @return 两个日期之间的差值
	 */
	public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

		long millisDiff = getMillis(calSrc) - getMillis(calDes);

		if (flag == 'y') {
			return (calSrc.get(Calendar.YEAR) - calDes.get(Calendar.YEAR));
		}

		if (flag == 'd') {
			return (int) (millisDiff / DAY_IN_MILLIS);
		}

		if (flag == 'h') {
			return (int) (millisDiff / HOUR_IN_MILLIS);
		}

		if (flag == 'm') {
			return (int) (millisDiff / MINUTE_IN_MILLIS);
		}

		if (flag == 's') {
			return (int) (millisDiff / SECOND_IN_MILLIS);
		}

		return 0;
	}

	/**
	 * String类型 转换为Date, 如果参数长度为10 转换格式”yyyy-MM-dd“ 如果参数长度为19 转换格式”yyyy-MM-dd
	 * HH:mm:ss“ * @param text String类型的时间值
	 */
	public void setAsText(String text) throws IllegalArgumentException {

		if (StringUtils.hasText(text)) {
			try {
				if (text.indexOf(":") == -1 && text.length() == 10) {
					setValue(DateUtils.yyyy_MM_dd.parse(text));
				} else if (text.indexOf(":") > 0 && text.length() == 19) {
					setValue(DateUtils.datetime_sdf.parse(text));
				} else {
					throw new IllegalArgumentException(
							"Could not parse date, date format is error ");
				}
			} catch (ParseException ex) {
				IllegalArgumentException iae = new IllegalArgumentException(
						"Could not parse date: " + ex.getMessage());
				iae.initCause(ex);
				throw iae;
			}
		} else {
			setValue(null);
		}
	}

	public static int getYear() {

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getDate());
		return calendar.get(Calendar.YEAR);
	}
	/**
	 * 获得两个日期间间隔的天数
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static String getDaysBetweenTwoTime(Timestamp time1,Timestamp time2)
	{
		 Calendar fromCalendar = Calendar.getInstance(); 
		 fromCalendar.setTime(time1);
		 fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
         fromCalendar.set(Calendar.MINUTE, 0);  
         fromCalendar.set(Calendar.SECOND, 0);  
         fromCalendar.set(Calendar.MILLISECOND, 0); 
         Calendar toCalendar = Calendar.getInstance(); 
         toCalendar.setTime(time2);  
         toCalendar.set(Calendar.HOUR_OF_DAY, 0);  
         toCalendar.set(Calendar.MINUTE, 0);  
         toCalendar.set(Calendar.SECOND, 0);  
         toCalendar.set(Calendar.MILLISECOND, 0);
         Long tempString = (Math.abs(toCalendar.getTime().getTime() - fromCalendar.getTime().getTime())) / (1000 * 60 * 60 * 24);
         return  String.valueOf(tempString);
	}
	
	/**
	 * 判断两个时间的大小start>end?true:false;
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
	 * 判断两个时间的大小start>end?true:false;
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isLatter(Timestamp startTime,Timestamp endTime){
		calendar.setTime(startTime);
		long start=calendar.getTimeInMillis();
		calendar.setTime(endTime);
		long end=calendar.getTimeInMillis();
		return start>end?true:false;
	}
	/**
	 * 获得当前时间的前n天
	 * @param n
	 * @return
	 */
	public static String calendared(int n,String format){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, n); //得到前一天
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
}