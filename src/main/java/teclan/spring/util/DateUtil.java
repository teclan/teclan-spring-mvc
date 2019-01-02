package teclan.spring.util;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class DateUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    /**
     * 获取当前时间
     * 
     * @return
     */
    public static String getNow() {
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 将字符串转换为Date类型
     * 
     * @param str
     * @return
     */
    public static Date stringToDate(String str, String format) {
	try {
	    format = format == null || "".equals(format) ? "yyyy-MM-dd HH:mm:ss" : format;
	    return new SimpleDateFormat(format).parse(str);
	} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
	}
	return null;
    }

    /**
     * 格式化字符串
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    
    /**
     * 格式化字符串
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date,String format) {
    	format = format == null || "".equals(format) ? "yyyy-MM-dd HH:mm:ss" :format;
    	return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式化字符串
     * 
     * @param date
     * @return
     */
    public static String formatString(String tString) {
	if (tString == null || "".equals(tString))
	    return null;
	Date date = stringToDate(tString, null);
	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    
    /**
     * 格式化字符串
     * 
     * @param date
     * @return
     */
    public static String formatString(String tString,String format) {
	if (tString == null || "".equals(tString))
	    return null;
	format = format == null || "".equals(format) ? "yyyy-MM-dd HH:mm:ss" :format;
	Date date = stringToDate(tString, null);
	return new SimpleDateFormat(format).format(date);
    }
    
    /**
     * 格式化字符串
     * 
     * @param date
     * @return
     */
    public static String formatString(String tString,String inFormat,String outFormat) {
		if (tString == null || "".equals(tString))
		    return null;
		inFormat = inFormat == null || "".equals(inFormat) ? "yyyy-MM-dd HH:mm:ss" :inFormat;
		outFormat = outFormat == null || "".equals(outFormat) ? "yyyy-MM-dd HH:mm:ss" :outFormat;
		Date date = stringToDate(tString, inFormat);
		return new SimpleDateFormat(outFormat).format(date);
    }
    
    /**
     * 格式化字符串
     * 
     * @param time
     * @return 时：分
     */
    public static String formatTimeToString(String time) {
	if (null == time || "".equals(time))
	    return null;
	Date date = stringToDate(time, null);
	return new SimpleDateFormat("HH:mm").format(date);
    }
    
    public static String getEndOfTheDay(String time,String format){
    	Date date = new Date();
    	format = format == null || "".equals(format) ? "yyyy-MM-dd HH:mm:ss" :format;
    	if (time != null && !"".equals(time)){
    		date = stringToDate(time, format);
    	}
    	format = "yyyy-MM-dd 23:59:59";
    	return new SimpleDateFormat(format).format(date);
    }
    
    public static String getStartOfTheDay(String time,String format){
    	Date date = new Date();
    	format = format == null || "".equals(format) ? "yyyy-MM-dd HH:mm:ss" :format;
    	if (time != null && !"".equals(time)){
    		date = stringToDate(time, format);
    	}
    	format = "yyyy-MM-dd 00:00:00";
    	return new SimpleDateFormat(format).format(date);
    }
    
    /**
     * 判断时间是否在startTime跟endTime之间
     * @param time
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isIN(String time,String startTime,String endTime){
    	Date date = stringToDate(time,null);
    	Date sDate = stringToDate(startTime,null);
    	Date eDate = stringToDate(endTime,null);
    	return (date.getTime() >= sDate.getTime())&&(date.getTime() <= eDate.getTime());
    }
    
    /**
     * 
     * @param time 时间
     * @param cTime 被比较时间
     * @return cTime是否大于time
     */
    public static boolean isGTE(String time,String cTime){
    	Date date = stringToDate(time,null);
    	Date cDate = stringToDate(cTime,null);
    	return cDate.getTime() >= date.getTime();
    }
    
    public static boolean isGT(String startTime, Date date, String format) {
    	Date sDate = stringToDate(startTime,format);
    	date = date == null ? new Date() : date;
    	return date.getTime() > sDate.getTime();
	}
    
    public static boolean isGT(String time,String cTime,String format){
    	Date date = stringToDate(time,format);
    	Date cDate = stringToDate(cTime,format);
    	return cDate.getTime() > date.getTime();
    }
    
    public static boolean isLT(String time,String cTime,String format){
    	Date date = stringToDate(time,format);
    	Date cDate = stringToDate(cTime,format);
    	return cDate.getTime() < date.getTime();
    }
    
    public static boolean isLT(String endTime, Date date, String format) {
    	Date eDate = stringToDate(endTime,format);
    	date = date == null ? new Date() : date;
    	return date.getTime() < eDate.getTime();
	}
    
    /**
     * 
     * @param time 时间
     * @param cTime 被比较时间
     * @return cTime是否大于time
     */
    public static boolean isGTE(String time,Date cTime){
    	Date date = stringToDate(time,null);
    	return cTime.getTime() >= date.getTime();
    }
    
    /**
     * 
     * @param time 时间
     * @param cTime 被比较时间
     * @return cTime是否小于time
     */
    public static boolean isLTE(String time,String cTime){
    	Date date = stringToDate(time,null);
    	Date cDate = stringToDate(cTime,null);
    	return cDate.getTime() <= date.getTime();
    }
    
    /**
     * 
     * @param time 时间
     * @param cTime 被比较时间
     * @return cTime是否小于time
     */
    public static boolean isLTE(String time,Date cTime){
    	Date date = stringToDate(time,null);
    	return cTime.getTime() <= date.getTime();
    }
    
    /**
     * 
     * @TODO 根据时间获取星期几
     * @param pTime
     * @return 1,2,3,4,5,6,7
     * @throws Exception
     * 
     * @author yuanwang 00020
     * @throws ParseException 
     * @date 2016年9月20日 上午9:56:00
     */
    public static int getWeekDay(String pTime)  {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Calendar c = Calendar.getInstance();
	try {
	    c.setTime(format.parse(pTime));
	} catch (ParseException e) {
		LOGGER.error(e.getMessage(),e);
	}
	int dayForWeek = 0;
	if (c.get(Calendar.DAY_OF_WEEK) == 1) {
	    dayForWeek = 7;
	} else {
	    dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
	}
	return dayForWeek;
    }
    
    /**
     * 
     * @TODO 返回日期 2016-9-24
     * @param dateTime 2016-9-24 17:02:17
     * @return 2016-9-24
     * 
     * @author yuanwang 00020
     * @date 2016年9月24日 下午5:01:55
     */
    public static String getDate(String dateTime){
	if(null == dateTime || "".equals(dateTime)){
	    dateTime =  getNow();
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	try {
	   return sdf.format(sdf.parse(dateTime));
		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
	}
	return null;
    }
    
    /**
     * 
     * @TODO 当前时间100年后
     * @return
     * 
     * @author yuanwang 00020
     * @date 2016年10月22日 下午3:18:25
     */
    public static String getYesrLaster() {
	Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
//      calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.add(Calendar.YEAR, +100);
        date = calendar.getTime();
        return formatDate(date);
    } 

    /** 
     * 获取某年某月第一天日期 
     * @param year 年份
     * @param month 月份  
     * @return Date 
     */  
    public static Date getFirstDayOfMonth(Integer year,Integer month){ 
    	Calendar calendar = Calendar.getInstance();
   	 	year = year == null ? calendar.get(Calendar.YEAR) : year;
   	 	month = month == null ? month = calendar.get(Calendar.MONTH) : month;
   	 	calendar.set(year, month-1, 1);
        return calendar.getTime();
    }  
      
    /** 
     * 获取某年某月最后一天日期 
     * @param year 年份 
     * @param month 月份 
     * @return Date 
     */  
    public static Date getLastDayOfMonth(Integer year,Integer month){  
    	Calendar calendar = Calendar.getInstance();
   	 	year = year == null ? calendar.get(Calendar.YEAR) : year;
   	 	month = month == null ? month = calendar.get(Calendar.MONTH) : month;
   	 	calendar.set(year,month-1, 1);
   	 	calendar.roll(Calendar.DATE, -1);
        //calendar.set(year, month-1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();  
    } 
    
    /** 
     * 获取某年某月第一天日期 
     * @param year 年份
     * @param month 月份 
     * @param format 格式
     * @return String 
     */  
    public static String getFirstDayOfMonth(Integer year,Integer month,String format){ 
    	Date date = getFirstDayOfMonth(year, month);
    	return formatDate(date, format);
    }  
      
    /** 
     * 获取某年某月最后一天日期 
     * @param year 年份
     * @param month 月份 
     * @param format 格式
     * @return String 
     */ 
    public static String getLastDayOfMonth(Integer year,Integer month,String format){  
    	Date date = getLastDayOfMonth(year, month);
    	return formatDate(date, format);
    } 
    
    /** 
     * 获取指定日期所在月的第一天日期 
     * @param date 日期 "yyyy-MM-dd"格式
     * @param format 格式
     * @return String 
     */  
    public static String getFirstDayOfTheMonth(String date,String format){ 
    	Date time = stringToDate(date, "yyyy-MM-dd");
    	int month = time.getMonth() + 1;
    	int year = time.getYear() + 1900;
    	time = getFirstDayOfMonth(year, month);
    	return formatDate(time, format);
    }  
      
    /** 
     * 获取指定日期所在月的最后一天日期 
     * @param date 日期 "yyyy-MM-dd"格式
     * @param format 格式
     * @return String 
     */ 
	@SuppressWarnings("deprecation")
	public static String getLastDayOfTheMonth(String date, String format) {
    	Date time = stringToDate(date, "yyyy-MM-dd");
    	int month = time.getMonth() + 1;
    	int year = time.getYear() + 1900;
    	time = getLastDayOfMonth(year, month);
    	return formatDate(time, format);
    } 

    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getFirstDayOfYear(Integer year){ 
    	 Calendar calendar = Calendar.getInstance();  
    	 year = year == null ? calendar.get(Calendar.YEAR) : year;
    	 calendar.clear();
         calendar.set(Calendar.YEAR, year);  
         Date firstDay = calendar.getTime();  
         return firstDay; 
    }  
      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getLastDayOfYear(Integer year){  
    	Calendar calendar = Calendar.getInstance();
    	year = year == null ? calendar.get(Calendar.YEAR) : year;
    	calendar.clear();
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date lastDay = calendar.getTime();  
        return lastDay;    
    } 
    
    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @param format 格式
     * @return String 
     */  
    public static String getFirstDayOfYear(Integer year,String format){ 
    	Date date = getFirstDayOfYear(year);
    	return formatDate(date, format);
    }  
      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @param format 格式
     * @return String 
     */  
    public static String getLastDayOfYear(Integer year,String format){  
    	Date date = getLastDayOfYear(year);
    	return formatDate(date, format);
    } 
    
    /**
     * 返回指定日期的季的第一天
     * @param date 指定日期
     * @return Date
     */
    public static Date getFirstDayOfQuarter(Date date) {
    	date = date == null ? new Date() : date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(
        		calendar.get(Calendar.YEAR),
                getQuarterOfYear(date));
    }
    
    /**
     * 返回指定日期的季的最后一天
     * @param date 指定日期
     * @return Date
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR),
                                   getQuarterOfYear(date));
    }
    
    /**
     * 返回某季度的第一天
     * @param year 年份
     * @param quarter 季度
     * @return Date
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        year = year == null ? calendar.get(Calendar.YEAR) : year;
        quarter = quarter == null ? 1 : quarter;
        Integer month = new Integer(0);
        switch (quarter) {
		case 1:
			month = 1;
			break;
		case 2:
			month = 4;
			break;
		case 3:
			month = 7;
			break;
		case 4:
			month = 10;
			break;
		default:
			month = calendar.get(Calendar.MONTH);
			break;
		}
        return getFirstDayOfMonth(year, month);
    }
    
    /**
     * 返回某天某季度的最后一天
     * @param year 年份
     * @param quarter 季度
     * @return Date
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        year = year == null ? calendar.get(Calendar.YEAR) : year;
        quarter = quarter == null ? 1 : quarter;
        switch (quarter) {
		case 1:
			month = 3;
			break;
		case 2:
			month = 6;
			break;
		case 3:
			month = 9;
			break;
		case 4:
			month = 12;
			break;
		default:
			month = calendar.get(Calendar.MONTH);
			break;
		}
        return getLastDayOfMonth(year, month);
    }
    
    /**
     * 返回某季度的第一天
     * @param year 年份
     * @param quarter 季度
     * @param format 格式
     * @return String
     */
    public static String getFirstDayOfQuarter(Integer year, Integer quarter,String format) {
        Date date = getFirstDayOfQuarter(year, quarter);
        return formatDate(date, format);
    }
    
    /**
     * 返回日期所在季度的第一天
     * @param date 日期字符串 "yyyy-MM-dd"格式
     * @param format 格式
     * @return String
     */
    public static String getFirstDayTheQuarter(String date,String format) {
    	Date time = getFirstDayOfQuarter(stringToDate(date, "yyyy-MM-dd"));
        format = format == null ? "yyyy-MM-dd HH:mm:ss" :format;
        return formatDate(time, format);
    }
    
    /**
     * 返回某季度的最后一天
     * @param year 年份
     * @param quarter 季度
     * @param format 格式
     * @return String
     */
    public static String getLastDayOfQuarter(Integer year, Integer quarter,String format) {
        Date date = getLastDayOfQuarter(year, quarter);
        return formatDate(date, format);
    }
    
    /**
     * 返回日期所在季度的最后一天
     * @param year 年份
     * @param quarter 季度
     * @param format 格式
     * @return String
     */
    public static String getLastDayOfTheQuarter(String date,String format) {
    	Date time = getLastDayOfQuarter(stringToDate(date, "yyyy-MM-dd"));
        format = format == null ? "yyyy-MM-dd HH:mm:ss" :format;
        return formatDate(time, format);
    }

    /**
     * 根据日期获取季度
     * @param date 日期
     * @return int
     */
    public static int getQuarterOfYear(Date date) {
    	date = date == null ? new Date() : date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }
    
    /**
     * 根据日期获取季度
     * @param date 日期
     * @return int
     */
    public static int getQuarterOfYear(String time,String format) {
    	Date date = stringToDate(time, format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    /**
     * 根据时间计算天数
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param format 格式
     */
	public static Integer getDaysByTime(String startTime, String endTime,
			String format) {
		Calendar calst = Calendar.getInstance();;
	    Calendar caled = Calendar.getInstance();
	    Date startDate = stringToDate(startTime, format);
	    Date endDate = stringToDate(endTime, format);
	    calst.setTime(startDate);
	    caled.setTime(endDate);
	    //设置时间为0时   
	    calst.set(Calendar.HOUR_OF_DAY, 0);   
	    calst.set(Calendar.MINUTE, 0);   
	    calst.set(Calendar.SECOND, 0);   
	    caled.set(Calendar.HOUR_OF_DAY, 0);   
	    caled.set(Calendar.MINUTE, 0);   
	    caled.set(Calendar.SECOND, 0);   
	    //得到两个日期相差的天数   
	    int days = ((int)(caled.getTime().getTime()/1000)-(int)(calst.getTime().getTime()/1000))/3600/24;   
	    return days;   
	}

    /**
     * 对象转json
     * @param Object
     */
	public static JSONObject objectToJson(Object obj) throws Exception {             
		if(obj == null)return null;                    
		JSONObject map = new JSONObject();                 
		Field[] declaredFields = obj.getClass().getDeclaredFields();             
		for (Field field : declaredFields) {                 
			field.setAccessible(true);               
			map.put(field.getName(), field.get(obj));           
			}                 
		return map;       
	}
	
	/**
     * 将日期字符串转成连起的字符串+6位随机数
     * @param time  yyyy-MM-dd HH:mm:ss
     */
    public static String TimeToStrSix(String time) {
    	String s1 = time.replace("-","");
    	String s2 = s1.replace(" ","");
    	String s3 = s2.replace(":","");
    	int i =(int)((Math.random()*9+1)*100000);
    	String Si = String.valueOf(i);
    	StringBuffer stringBuffer = new StringBuffer(s3);
    	stringBuffer.append(Si);
    	String string = stringBuffer.toString();
		return string;
	}
    /**
     * 将日期字符串转成连起的字符串+14位随机数
     * @param time  yyyy-MM-dd HH:mm:ss
     */
    public static String TimeToFourteen(String time) {
    	String s1 = time.replace("-","");
    	String s2 = s1.replace(" ","");
    	String s3 = s2.replace(":","");
    	int i =(int)((Math.random()*9+1)*1000000);
    	int j =(int)((Math.random()*9+1)*1000000);
    	String Si = String.valueOf(i);
    	String Sj = String.valueOf(j);
    	StringBuffer stringBuffer = new StringBuffer(s3);
    	stringBuffer.append(Si);
    	stringBuffer.append(Sj);
    	String string = stringBuffer.toString();
		return string;
	}
    
    /**
     * yyyyMMddHHmmssSSS+设备编号+两位随机数
     * 
     */
    public static String timeStampAndDevId(String timeStamp, String devId) {
    	String s1 = timeStamp.replace("-","");
    	String s2 = s1.replace(" ","");
    	String s3 = s2.replace(":","");
    	if(s3.length()<17){
    		s3 = s3+"000";
    	}
    	int i =(int)((Math.random()*9+1)*10);
    	StringBuffer stringBuffer = new StringBuffer(s3);
    	stringBuffer.append(devId);
    	stringBuffer.append(String.valueOf(i));
    	String string = stringBuffer.toString();
		return string;
	}
    
    public static String  dateToTimeStamp(String s) throws Exception {
    	String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
	}
}
