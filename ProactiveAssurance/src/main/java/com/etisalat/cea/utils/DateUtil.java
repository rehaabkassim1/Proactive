package com.etisalat.cea.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import com.mysql.cj.util.DataTypeUtil;

/**
 * @author singh
 *	Class is responsible for Date and its operations in the application.
 */
public class DateUtil {

	//private static //logger //logger = //logger.get//logger(DateUtil.class);

	public static boolean isCurrentTimeGreaterThanGivenTime(Date date) {
		boolean isGreater = false;
		long currentTimeMillis = 0l;
		long givenTimeMillis = 0l;
		Date currentTime = new Date();
		currentTimeMillis = currentTime.getTime();
		givenTimeMillis = date.getTime();

		if(currentTimeMillis>=givenTimeMillis) {
			isGreater =true;
		}
		//logger.info("Current time is greater than given time: "+isGreater);
		return isGreater;
	}
	
	public static boolean isValidityTimeExpired(Timestamp validityTime) {
		if(System.currentTimeMillis()>=validityTime.getTime()) {
			return true;
		}
		return false;
	}


	/**
	 * Convert Timestamp date string to Timestamp object
	 * @param date
	 * input= 2018-06-01 00:00:00
	 * output=yyyy-MM-dd HH:mm:ss (timestamp obj)
	 */
	public static Timestamp parseDateStringToTimestamp(String dateStr) {
		Timestamp ts1 =null;
		SimpleDateFormat format = new SimpleDateFormat();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateObj=null;
		try {
			dateObj = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ts1 = new Timestamp(dateObj.getTime());
		return ts1;
	}

	/**
	 * To get Current Date and Last 7th day date string.
	 * @return
	 */
	public static Map<String,String> getLast7DayAndCurrentDateString() {
		//logger.info("Generating current and Last 7th Day date string");
		String currentDate = null;
		String fromDate=null, toDate=null;
		Map<String,String> dateMap = new HashMap<String, String>();
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate = year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		dateMap.put("toDate", toDate);

		cal.add(Calendar.DATE, -7);
		int year1 = cal.get(Calendar.YEAR);
		int month1 = cal.get(Calendar.MONTH)+1;
		int day1 = cal.get(Calendar.DAY_OF_MONTH);
		fromDate =year1+""+get2DigitMonth(month1+"")+""+get2DigitMonth(day1+"");
		dateMap.put("fromDate", fromDate);
		//logger.info("Current and Last 7 Day date string: "+dateMap.toString());
		return dateMap;

	}

	public static String getBigDataDateStr(LocalDateTime date) {
		//logger.info("Getting current date string for big data api");
		String currentDate=null;
		int year = date.getYear();
		int month = date.getMonthValue();
		int day = date.getDayOfMonth();
		currentDate = year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		//logger.info(" Current Date: "+currentDate);
		return currentDate;
	}

	public static int getHourFromDate(LocalDateTime date) {
		//logger.info("Fetching hour from date");
		int hour = date.getHour();
		return hour;
	}

	public static String getHourOfTheDay() {
		Calendar now = Calendar.getInstance();
		String hour = now.get(Calendar.HOUR_OF_DAY)+"";
		if(hour.length()==1) {
			hour="0"+hour;
		}
		//logger.info("Hour of the day: "+hour);
		return hour;
	}

	public static String getNextHourOfTheDay() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR_OF_DAY, 1);
		String hour = now.get(Calendar.HOUR_OF_DAY)+"";
		if(hour.length()==1) {
			hour="0"+hour;
		}
		//logger.info("Next Hour of the day: "+hour);
		return hour;
	}

	public static int getHourFromDate(Timestamp date) {
		//logger.info("Fetching hour from date");
		int hour = date.getHours();
		return hour;
	}



	private static String get2DigitMonth(String month) {
		//logger.info("Converting month to two digit string: "+month);
		String monthStr = null;
		if(month!=null && month.length()==1) {
			monthStr = "0"+month;
		}else {
			monthStr = month;
		}
		return monthStr;
	}

	public static Date getHomologousDate(Date currentDate){
		//logger.debug("Date Utility: Getting Homologous date for: "+currentDate);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// String todate = dateFormat.format(currentDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.DATE, -7);
		Date fromDate = cal.getTime();    
		String fromdate = dateFormat.format(fromDate);
		//logger.debug("Date Utility:Homologous date: "+fromdate);
		return fromDate;
	}

	public static java.sql.Date convertUtilDateToSQLDate(Date currentDate){
		java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
		System.out.println("sqlDate:" + sqlDate);
		return sqlDate;
	}
	/*	public static Date convertStringToDate(String date){
		//logger.debug("Date Utility: Converting : "+date);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String todate = dateFormat.format(date);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Date fromDate = cal.getTime();    
        String fromdate = dateFormat.format(fromDate);
        //logger.debug("Date Utility:Converted date: ");
        return null;
	}*/

	/*
	 * It converts string {dateStr} to Date object
	 * input: 5/22/2017  12:00:00 AM (String) and formatter
	 * output: 2017-05-22 12:00:00.0 (Date) 
	 */
	public static Date convertStringToDate(String dateStr) throws ParseException{
		//logger.debug("Formatting date string : ["+dateStr+"]");
		Date date = null;
		DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
		date = inputFormatter.parse(dateStr);
		//logger.debug("Successfully formatted : "+date);
		return date;
	}

	public static Date convertStringToDate_WFM(String dateStr) throws ParseException{
		//logger.debug("Formatting date string : ["+dateStr+"]");
		Date date = null;
		DateFormat inputFormatter = new SimpleDateFormat("yyyyMMdd");
		date = inputFormatter.parse(dateStr);
		//logger.debug("Successfully formatted : "+date);
		return date;
	}

	public static Date convertStringToDates(String dateStr) throws ParseException{
		//logger.debug("Formatting date string : ["+dateStr+"]");
		Date date = null;
		DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
		date = inputFormatter.parse(dateStr);
		//logger.debug("Successfully formatted : "+date);
		return date;
	}



	/*
	 * It converts Date Object {date} to Timestamp Object.
	 * 
	 */
	public static Timestamp convertDateToTimestamp(Date date){
		//logger.debug("Formatting date string : ["+date+"]");
		Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		//logger.debug("Successfully formatted : "+date);
		return timestamp;
	}

	/*public Date convertStringToDate(String dateStr) throws ParseException{
		//logger.debug("Formatting date string : ["+dateStr+"]");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return (Date)sdf.parse(dateStr);
	}*/

	public static String formatDateToString(Date date){
		//logger.info("Formatting date : ["+date+"] to String");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		return formatter2.format(date);
	}

	public static String formatDateToString_wfm(Date date){
		//logger.info("Formatting date : ["+date+"] to String");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
		return formatter2.format(date);
	}

	public static String convertDateToString(Date date){
		//logger.debug("Formatting date : ["+date+"] to String");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yy");
		return formatter2.format(date);
	}

	public static Timestamp convertStringToTimestamp(String timeString) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parsedDate = dateFormat.parse(timeString);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { 
			//logger.info("Exception in converting: "+timeString+" to timestamp."+e);
			return  null;
		}
		return timestamp;
	}

	public static Timestamp convertStringToTimestampMTTT(String timeString) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date parsedDate = dateFormat.parse(timeString);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { 
			//logger.info("Exception in converting: "+timeString+" to timestamp."+e);
			return  null;
		}
		return timestamp;
	}

	public static long getTimeDifferenceInMinutes(java.util.Date startDate, java.util.Date endDate) {
		//logger.debug("Computing difference between startDate: "+startDate+" and endDate: "+endDate);
		long diff = endDate.getTime() - startDate.getTime();
		//long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000);
		//long diffHours = diff / (60 * 60 * 1000) % 24;
		//long diffDays = diff / (24 * 60 * 60 * 1000);
		//logger.debug("Difference between startDate: "+startDate+" and endDate: "+endDate+" = "+diffMinutes);	
		return diffMinutes;
	}

	public static long getTimeDifferenceInSeconds(java.util.Date startDate, java.util.Date endDate) {
		//logger.debug("Computing difference between startDate: "+startDate+" and endDate: "+endDate);
		long diff = endDate.getTime() - startDate.getTime();
		long diffSeconds = diff / 1000;
		//long diffMinutes = diff / (60 * 1000) % 60;
		//long diffHours = diff / (60 * 60 * 1000) % 24;
		//long diffDays = diff / (24 * 60 * 60 * 1000);
		//logger.debug("Difference between startDate: "+startDate+" and endDate: "+endDate);	
		return diffSeconds;
	}

	public static long getTimeDifferenceInSeconds(long startTimeInMS, long endTimeInMS) {
		long diff = endTimeInMS-startTimeInMS;
		long diffSeconds = diff / 1000;
		return diffSeconds;
	}

	public static long getTimeDifferenceInHours(java.util.Date startDate, java.util.Date endDate) {
		//logger.debug("Computing difference between startDate: "+startDate+" and endDate: "+endDate);
		long diff = endDate.getTime() - startDate.getTime();
		//long diffSeconds = diff / 1000 % 60;
		//long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (1000*60*60);
		//long diffDays = diff / (24 * 60 * 60 * 1000);
		//logger.debug("Difference between startDate: "+startDate+" and endDate: "+endDate);	
		return diffHours;
	}

	public static long getTimeDifferenceInHours(Timestamp startDate, Timestamp endDate) {
		//logger.debug("Computing difference between startDate: "+startDate+" and endDate: "+endDate);
		long diff = endDate.getTime() - startDate.getTime();
		//long diffSeconds = diff / 1000 % 60;
		//long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		//long diffDays = diff / (24 * 60 * 60 * 1000);
		//logger.debug("Difference between startDate: "+startDate+" and endDate: "+endDate);	
		return diffHours;
	}


	public static String getCurrentDateOfLastMonthString() {
		//logger.info("Computing current and last month date");
		String dateString = "";
		Date date = new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		Timestamp dateStamp =    new java.sql.Timestamp(c.getTimeInMillis());
		if(dateStamp!=null) {
			String dateStr = dateStamp.toString();
			String dStr = dateStr.split(" ")[0];
			dateString = dStr.replaceAll("-", "");
		}
		//logger.info("Current date for last month: "+dateString);
		return dateString;
	}

	public static String getCurrentDateStr() {
		//logger.info("Computing current date string");
		String dateString = "";
		Date currentDate = new Date();
		Timestamp dateStamp = new Timestamp(currentDate.getTime());
		if(dateStamp!=null) {
			String dateStr = dateStamp.toString();
			String dStr = dateStr.split(" ")[0];
			dateString =  dStr.replaceAll("-", "");
		}
		//logger.info("Current date: "+dateString);
		return dateString;

	}

	public static Timestamp getCurrentTimestamp() {
		//logger.info("Computing current timestamp");
		Timestamp current_ts = new Timestamp(System.currentTimeMillis());
		//logger.info("Current Timsetamp: "+current_ts);
		return current_ts;

	}

	public static long getTimeDifferenceBWDateAndCurrentDate(Date date) {
		long diff = 0;
		if(date!=null) {
			diff = System.currentTimeMillis() - date.getTime();
		}
		//logger.info("Difference: "+diff);
		return diff;
	}

	public static long getTimeDifferenceBWTimestamps(Timestamp time) {
		long diff = 0;
		if(time!=null) {
			diff = (System.currentTimeMillis() - time.getTime())/(1000*3600);
		}
		return diff;
	}

	/*	public static LocalDateTime subHoursFromCurrentTimestamp(Timestamp currentDate, int hours) {
		//logger.info("Subtracting "+hours+" hours from Date: "+currentDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String currentTime= currentDate.toString();
		LocalDateTime datetime = LocalDateTime.parse(currentTime,formatter);
		//System.out.println(datetime.toString());
		datetime=datetime.minusHours(hours);
		//String aftersubtraction=datetime.format(formatter);
		return datetime;
	}*/
	public static LocalDateTime subHoursFromCurrentTimestamp(LocalDateTime ldt, int hours) {
		//logger.info("Subtracting "+hours+" hours from Date: "+ldt);
		LocalDateTime datetime=ldt.minusHours(hours);
		//logger.info("Date after subtracting hours: "+datetime);
		return datetime;
	}

	public static Timestamp getTimestampSubtractHoursFromCurrentTime(Timestamp currentTime, int hours) {
		//logger.info("Computing Timestamp "+hours+" hours before current time: "+currentTime);
		if(currentTime==null) {
			currentTime = new Timestamp(System.currentTimeMillis());
		}
		long current_ms = currentTime.getTime();
		long hours_before_ms = hours*60*60*1000;
		Timestamp ts = new Timestamp(current_ms-hours_before_ms);
		//logger.info("Current Time: "+currentTime+" and Time "+hours+"hours ago: "+ts);
		return ts;
	}

	public static Timestamp getTimestampBeforeHalfAnHour(Timestamp currentTime) {
		//logger.info("Computing Timestamp Before half an hour from current time: "+currentTime);
		Timestamp time = null;
		long current_ms = currentTime.getTime();
		long msBeforeHalfAnHour =  30*60*1000;
		time = new Timestamp(current_ms-msBeforeHalfAnHour);
		//logger.info("Current Time: "+currentTime+" and Time half an hours ago: "+time);
		return time;

	}

	public static Date convertTimestampToDate(Timestamp time) {
		//logger.info("Converting Timestamp to Date");
		Date date = new Date(time.getTime());
		//logger.info("Converted Date: "+date);
		return date;
	}


	public static long getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}

	public static long getEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime().getTime();
	}


	public static String getDateStrWithCurrentHour12HourFormat() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy hh aa");
		String formattedDate = dateFormat.format(new Date()).toString();
		//System.out.println(formattedDate);
		return formattedDate;
	}

	public static String getTodayDateHour12HourFormat() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = dateFormat.format(new Date()).toString();
		//System.out.println(formattedDate);
		return formattedDate;
	}

	public static String getYesterdayDateForEmailSubject() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, -1);
		String formattedDate = dateFormat.format(cal.getTime()).toString();
		//System.out.println(formattedDate);
		return formattedDate;
	}



	public static String getDateStrOfHourMin12HourFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy HH:mm aa");
		String formattedDate = dateFormat.format(date).toString();
		//System.out.println(formattedDate);
		return formattedDate;
	}

	public static Timestamp getSpecificTimeObj(Date date) {
		Timestamp ts = null;
		String hr = DateUtil.getHourOfTheDay();
		String dateStr = DateUtil.formatDateToString(date);
		String finalDateStr = dateStr+" "+hr+":45:00";
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}

	public static Timestamp getSpecificTimeForEmail(Date date) {
		Timestamp ts = null;
		String hr = DateUtil.getHourOfTheDay();
		String dateStr = DateUtil.formatDateToString(date);
		String finalDateStr = dateStr+" "+hr+":40:00";
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}


	public static Timestamp getSpecificMidnightTime(Date date) {
		Timestamp ts = null;
		String dateStr = DateUtil.formatDateToString(date);
		String finalDateStr = dateStr+" 23:50:00";
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}

	public static Timestamp getSpecificReportStartTime(Date date) {
		Timestamp ts = null;
		String dateStr = DateUtil.formatDateToString(date);
		String finalDateStr = dateStr+" 05:45:00";
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}

	public static Timestamp getSpecificReportEndTime(Date date) {
		Timestamp ts = null;
		String dateStr = DateUtil.formatDateToString(date);
		String finalDateStr = dateStr+" 23:00:00";
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}

	public static Timestamp getSpecificEmailStartTime(Date date) {
		Timestamp ts = null;
		String dateStr = DateUtil.formatDateToString(date);
		String finalDateStr = dateStr+" 06:40:00";
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}

	public static Timestamp getSpecificEmailEndTime(Date date) {
		Timestamp ts = null;
		String dateStr = DateUtil.formatDateToString(date);
		String finalDateStr = dateStr+" 23:50:00";
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}

	public static Timestamp getSpecificWFMReportStartTime(Date date) {
		Timestamp ts = null;
		String dateStr = DateUtil.formatDateToString(date);
		String finalDateStr = dateStr+" 08:00:00";
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}

	public static Timestamp getLastHourFormattedTimeForEmail(Timestamp ts) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
			Date parsedDate = dateFormat.parse(ts.toString());
			Calendar cal = Calendar.getInstance();
			cal.setTime(parsedDate);
			cal.add(Calendar.HOUR, -1);
			timestamp = new java.sql.Timestamp(cal.getTimeInMillis());

		} catch(Exception e) { 
			//logger.info("Exception in converting: "+timestamp+" to timestamp."+e);
			return  null;
		}
		return  timestamp;
	}
	public static Timestamp getCurrentFormattedTimeForEmail() {
		Timestamp current = new Timestamp(System.currentTimeMillis());
		Timestamp formattedTime = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
			Date parsedDate = dateFormat.parse(current.toString());
			formattedTime = new Timestamp(parsedDate.getTime());
			//logger.info("Formatted Time: "+formattedTime);
		} catch(Exception e) { 
			//logger.info("Exception in converting: "+current+" to timestamp."+e);
			return  null;
		}
		return formattedTime;
	}

	public static String getFormattedTimeForEmail(Timestamp current ) {
		Timestamp formattedTime = null;
		int hr = 0;
		String formattedString = "";
		try {
			hr = getHourFromDate(current);
			String dateStr = DateUtil.formatDateToString(current);
			formattedString = dateStr+"_"+hr;
		} catch(Exception e) { 
			//logger.info("Exception in converting: "+current+" to timestamp."+e);
			return  null;
		}
		//logger.info("Formatted String: "+formattedString);
		return formattedString;
	}

	public static Timestamp convertStringToTimestampForEmail(String timeString) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parsedDate = dateFormat.parse(timeString);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { 
			//logger.info("Exception in converting: "+timeString+" to timestamp."+e);
			return  null;
		}
		return timestamp;
	}



	public static Timestamp getTimestampForNextInterval(Timestamp current,int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(current.getTime());
		// add Interval seconds
		cal.add(Calendar.MINUTE, interval);
		current = new Timestamp(cal.getTime().getTime());
		//logger.info("WFM Report: Timestamp for next interval - "+current);
		return current;
	}

	public static Timestamp getTimestampForLastInterval(Timestamp current,int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(current.getTime());
		// add Interval seconds
		cal.add(Calendar.MINUTE, (interval*-1));
		current = new Timestamp(cal.getTime().getTime());
		//logger.info("WFM Report: Timestamp for last interval - "+current);
		return current;
	}

	public static Timestamp getTimeIntervalObj(Date date, int slot) {
		Timestamp ts = null;
		String finalDateStr = null;
		String dateStr = DateUtil.formatDateToString(date);
		if(slot==1) {
			finalDateStr = dateStr+" 00:30:00";
		}else if(slot==2) {
			finalDateStr = dateStr+" 08:00:00";
		}else if(slot==3) {
			finalDateStr = dateStr+" 12:00:00";
		}else if(slot==4) {
			finalDateStr = dateStr+" 16:00:00";
		}else if(slot==5) {
			//finalDateStr = dateStr+" 08:00:00";
		}
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}

	public static String getWFMCurrentDateString() {
		String currentDate = null;
		String toDate = null;
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate = year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return toDate;

	}

	public static String getWFMYesterdayDateString() {
		String toDate = null;
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return toDate;
	}

	public static String getYesterdayDateString_24hrsEmail() {
		String toDate = null;
		Timestamp current = null;
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		current = new Timestamp(cal.getTime().getTime());
		toDate = convertDateToString(current);
		return toDate;
	}

	public static String get15MinsIntervalTime(Date currentDate, int intervalTimeInMins) {
		String minutes = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.MINUTE, intervalTimeInMins*-1);
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		if(hour.length()==1) {
			hour="0"+hour;
		}
		int min = calendar.get(Calendar.MINUTE);
		if(min<15) {
			minutes = "00";
		}else if(min>=15 && min<30)
			minutes="15";
		else if(min>=30 && min<45)
			minutes="30";
		else if(min>=45)
			minutes="45";
		return hour+minutes;
	}

	public static String get15MinsIntervalDate(Date currentDate, int intervalTimeInMins) {
		String toDate = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.MINUTE, -50);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		toDate = year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return toDate;
	}



	public static Timestamp get15MinsRestSpecificTime(Date currentDate) {
		String toDate=null;
		String minutes = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		if(hour.length()==1) {
			hour="0"+hour;
		}
		int min = calendar.get(Calendar.MINUTE);
		if(min>=5 && min<20) {
			minutes = "05";
		}else if(min>=20 && min<35)
			minutes="20";
		else if(min>=35 && min<50)
			minutes="35";
		else if(min>=50)
			minutes="50";

		toDate = year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" "+hour+":"+minutes+":00";
		return convertStringToTimestampForEmail(toDate);
	}

	public static String getFormattedTimeWFM(String time) {
		String formattedTime = "";
		if(time==null) {
			return "0000 AM";
		}else {
			String[] timeArr = time.split("");

			if(timeArr.length==4) {
				formattedTime = timeArr[0]+timeArr[1]+":"+timeArr[2]+timeArr[3];
			}else if(timeArr.length==3) {
				formattedTime = "0"+timeArr[0]+":"+timeArr[1]+timeArr[2];
			}
			int formatTime=Integer.parseInt(time);
			if(formatTime>=0000 && formatTime<1200) {
				formattedTime = formattedTime+" AM";
			}else {
				formattedTime = formattedTime+" PM";
			}
		}
		return formattedTime;
	}


	public static String getDateFromTransdate(String transdate) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy hh:mm aa");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hhmm");
		Date parsedDate = formatter.parse(transdate);
		String formattedDate = dateFormat.format(parsedDate).toString();
		System.out.println(formattedDate);
		return formattedDate;
	}

	public static String formatStringToFomattedDate(String dateStr) throws ParseException{
		//logger.info("Formatting date : ["+dateStr+"] to String");
		SimpleDateFormat month_date = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date parsedDate = formatter.parse(dateStr);
		String month_name = month_date.format(parsedDate);
		return month_name;
	}

	public static String getDate1(){
		//logger.debug("Date Utility: Getting Date1");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		Date fromDate = cal.getTime();    
		String fromdate = dateFormat.format(fromDate);
		//logger.debug("Date Utility: Getting Date1: "+fromdate);
		return fromdate;
	}

	public static String getDate2(){
		//logger.debug("Date Utility: Getting Date1");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -2);
		Date fromDate = cal.getTime();    
		String fromdate = dateFormat.format(fromDate);
		//logger.debug("Date Utility: Getting Date1: "+fromdate);
		return fromdate;
	}

	public static String getDate3(){
		//logger.debug("Date Utility: Getting Date1");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -3);
		Date fromDate = cal.getTime();    
		String fromdate = dateFormat.format(fromDate);
		//logger.debug("Date Utility: Getting Date1: "+fromdate);
		return fromdate;
	}

	public static String getDate4(){
		//logger.debug("Date Utility: Getting Date1");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -4);
		Date fromDate = cal.getTime();    
		String fromdate = dateFormat.format(fromDate);
		//logger.debug("Date Utility: Getting Date1: "+fromdate);
		return fromdate;
	}

	public static String getDate5(){
		//logger.debug("Date Utility: Getting Date1");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -5);
		Date fromDate = cal.getTime();    
		String fromdate = dateFormat.format(fromDate);
		//logger.debug("Date Utility: Getting Date1: "+fromdate);
		return fromdate;
	}

	public static String getDate6(){
		//logger.debug("Date Utility: Getting Date1");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -6);
		Date fromDate = cal.getTime();    
		String fromdate = dateFormat.format(fromDate);
		//logger.debug("Date Utility: Getting Date1: "+fromdate);
		return fromdate;
	}

	public static String getDate7(){
		//logger.debug("Date Utility: Getting Date1");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -7);
		Date fromDate = cal.getTime();    
		String fromdate = dateFormat.format(fromDate);
		//logger.debug("Date Utility: Getting Date1: "+fromdate);
		return fromdate;
	}

	public static String getMobileMajorBEExcYesterdayDateString() {
		String toDate = null;
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return toDate;
	}

	public static String getMobileIVRDateString_7DaysAgo() {
		String toDate = null;
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -7);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return toDate;
	}

	public static String getMobileIVRDateString_yesterday() {
		String toDate = null;
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return toDate;
	}

	public static String getConsumerMajorBEExcYesterdayDateString() {
		String toDate = null;
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return toDate;
	}

	public static String getYesterdayDateStringMotiveReports() {
		String toDate = null;
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"");
		return toDate;
	}

	public static String get7DaysAgoStringMotiveReports() {
		String toDate = null;
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -7);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"");
		return toDate;
	}

	public static String getSevenDaysAgoDate_fileName() {
		String toDate = null, fromDate=null;
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;	
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"_"+get2DigitMonth(month+"")+"_"+get2DigitMonth(day+"");

		cal.setTime(date);
		cal.add(Calendar.DATE, -7);
		int year_1 = cal.get(Calendar.YEAR);
		int month_1 = cal.get(Calendar.MONTH)+1;
		int day_1 = cal.get(Calendar.DAY_OF_MONTH);
		fromDate =year_1+"_"+get2DigitMonth(month_1+"")+"_"+get2DigitMonth(day_1+"");
		return fromDate+"_to_"+toDate;
	}

	public static String getYesterdayDate_fileName() {
		String toDate = null,fromDate=null;
		Date date=new Date(System.currentTimeMillis()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 0);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;	
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"_"+get2DigitMonth(month+"")+"_"+get2DigitMonth(day+"");

		cal.add(Calendar.DATE, -1);
		int year_1 = cal.get(Calendar.YEAR);
		int month_1 = cal.get(Calendar.MONTH)+1;
		int day_1 = cal.get(Calendar.DAY_OF_MONTH);
		fromDate =year_1+"_"+get2DigitMonth(month_1+"")+"_"+get2DigitMonth(day_1+"");

		return fromDate+"_to_"+toDate;
	}


	public static String formatNumbers(String num) {
		String fNum = null;
		NumberFormat format = NumberFormat.getInstance();
		fNum = format.format(Long.parseLong(num));
		return fNum;
	}

	public static String getStartTimeInSeconds(Date currentDate, int intervalTimeInMins) {
		Long timeInMillis = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.MINUTE, -1*intervalTimeInMins);
		timeInMillis = (calendar.getTimeInMillis()/1000);
		return timeInMillis+"";
	}

	public static String getEndTimeInSeconds(Date currentDate) {
		String time = (currentDate.getTime()/1000)+"";
		return time;
	}

	public static Timestamp getStartTimeMO(Date currentDate) {
		String toDate=null;
		String minutes = "00";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		String hour = (calendar.get(Calendar.HOUR_OF_DAY)-1)+"";
		int year = calendar.get(Calendar.YEAR);
		int month = (calendar.get(Calendar.MONTH)+1);  
		int day = calendar.get(Calendar.DAY_OF_MONTH); 
		//int day=26;
		if(hour.length()==1) {
			hour="0"+hour;
		}
		toDate = year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" "+hour+":"+minutes+":00";
		//logger.info("toDate -- "+toDate);
		return convertStringToTimestampForEmail(toDate);
	}

	public static Timestamp getEndTimeMO(Date currentDate) {
		String toDate=null;
		String minutes = "55";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		String hour = (calendar.get(Calendar.HOUR_OF_DAY)-1)+"";
		int year = calendar.get(Calendar.YEAR);
		int month = (calendar.get(Calendar.MONTH)+1);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		//int day=26;
		if(hour.length()==1) {
			hour="0"+hour;
		}
		toDate = year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" "+hour+":"+minutes+":00";
		//logger.info("toDate -- "+toDate);
		return convertStringToTimestampForEmail(toDate);
	}

	public static String getStartTimeInSec(Date today) {
		String startTime = null;
		Timestamp time = getStartTimeMO(today);
		startTime = (time.getTime()/1000)+"";
		return startTime;

	}
	public static String getEndTimeInSec(Date today) {
		String startTime = null;
		Timestamp time = getEndTimeMO(today);
		startTime = (time.getTime()/1000)+"";
		return startTime;

	}

	public static String getCurrentHour(Date currentDate) {
		String toDate=null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		String hour = (calendar.get(Calendar.HOUR_OF_DAY))+"";
		if(hour.length()==1) {
			hour="0"+hour;
		}
		return hour;
	}

	public static Timestamp getSpecificTimeMO(Date date) {
		Timestamp ts = null;
		String dateStr = DateUtil.formatDateToString(date);
		String finalDateStr = dateStr+" 11:30:00";
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}

	public static Timestamp getSpecificTimeForAutoOnboard(Date date) {
		Timestamp ts = null;
		String hour = getCurrentHour(date);
		String dateStr = DateUtil.formatDateToString(date);
		String finalDateStr = dateStr+" 04:30:00";
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}

	public static Timestamp getDefaultStartTime(Date currentDate) {
		String toDate=null;
		String minutes = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		toDate = year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" 00:00:00";
		return convertStringToTimestampForEmail(toDate);
	}

	public static Timestamp getDefaultEndTime(Date currentDate) {
		String toDate=null;
		String minutes = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if(hour.length()==1) {
			hour="0"+hour;
		}
		int min = calendar.get(Calendar.MINUTE);
		toDate = year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" "+hour+":00:00";
		return convertStringToTimestampForEmail(toDate);
	}

	public static Timestamp getDefaultStartTime_yesterday(Date currentDate) {
		String toDate=null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH)-1;

		toDate = year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" 00:00:00";
		return convertStringToTimestampForEmail(toDate);
	}

	public static Timestamp getDefaultEndTime_yesterday(Date currentDate) {
		String toDate=null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH)-1;
		if(hour.length()==1) {
			hour="0"+hour;
		}
		int min = calendar.get(Calendar.MINUTE);
		toDate = year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" 23:55:00";
		return convertStringToTimestampForEmail(toDate);
	}

	public static String fomatTimestamp(Timestamp ts) {
		String timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			timestamp = dateFormat.format(ts);
		} catch(Exception e) { 
			//logger.info("Exception in converting: "+timestamp+" to timestamp."+e);
			return  null;
		}
		return  timestamp;
	}

	public static Timestamp convertStringToTimestamps(String timeString) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date parsedDate = dateFormat.parse(timeString);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { 
			//logger.info("Exception in converting: "+timeString+" to timestamp."+e);
			return  null;
		}
		System.out.println(timestamp);
		return timestamp;
	}

	public static int getFinalHourForToday(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		int hour = (calendar.get(Calendar.HOUR_OF_DAY));
		return hour;
	}

	public static String getStartTimeInSec_loadData(Date today, int day, String hour) {
		String startTime = null;
		Timestamp time = getStartTimeMO_loadData(today,day==0?false:true,hour );
		startTime = (time.getTime()/1000)+"";
		return startTime;

	}
	public static String getEndTimeInSec_loadData(Date today, int day, String hour) {
		String startTime = null;
		Timestamp time = getEndTimeMO_loadData(today,day==0?false:true,hour);
		startTime = (time.getTime()/1000)+"";
		return startTime;

	}

	public static Timestamp getStartTimeMO_loadData(Date currentDate, boolean today, String hour) {
		String toDate=null;
		int day = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		int year = calendar.get(Calendar.YEAR);
		int month = (calendar.get(Calendar.MONTH)+1);  
		if(today) {
			day = calendar.get(Calendar.DAY_OF_MONTH); 
		}else {
			day = calendar.get(Calendar.DAY_OF_MONTH)-1;
		}
		toDate = year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" "+hour+":00:00";
		//logger.info("Loading start time: "+toDate);
		return convertStringToTimestampForEmail(toDate);
	}

	public static Timestamp getEndTimeMO_loadData(Date currentDate, boolean today, String hour) {
		String toDate=null;
		int day=0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		int year = calendar.get(Calendar.YEAR);
		int month = (calendar.get(Calendar.MONTH)+1);
		if(today) {
			day = calendar.get(Calendar.DAY_OF_MONTH); 
		}else {
			day = calendar.get(Calendar.DAY_OF_MONTH)-1;
		}

		toDate = year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" "+hour+":55:00";
		//logger.info("Loading end time: "+toDate);
		return convertStringToTimestampForEmail(toDate);
	}

	public static String formatDatePonOnboardCSV(Date date){
		//logger.info("Formatting date : ["+date+"] to String");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
		return formatter2.format(date);
	}

	public static String getTransdateForToday(Date date) {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,0);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return toDate;
	}

	public static String getTransdateForYesterday(Date date) {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,-1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return toDate;
	}

	public static String getCurrentHourOfTheDay() {
		Calendar now = Calendar.getInstance();
		String hour = now.get(Calendar.HOUR_OF_DAY)+"00";
		if(hour.length()==3) {
			hour="0"+hour;
		}
		//logger.info("Hour of the day: "+hour);
		return hour;
	}


	public static Timestamp getYesterdayAccountLoadTime(Date date) {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" 13:30:00";
		return convertStringToTimestampForEmail(toDate);
	}	


	public static Timestamp getNextDayStartTimeForWFExe(Date date) {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, +1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" 01:00:00";
		return convertStringToTimestampForEmail(toDate);
	}

	public static Timestamp getNextDayEndTimeForWFExe(Date date) {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, +1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" 05:30:00";
		return convertStringToTimestampForEmail(toDate);
	}

	public static Timestamp getNextDayEndTimeForWFResultsExtraction(Date date) {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, +1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" 06:30:00";
		return convertStringToTimestampForEmail(toDate);
	}

	public static Timestamp getTimeBeforeSpecificMinutes(Date currentDate, int minutesBefore) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.MINUTE, -1*minutesBefore);
		return new Timestamp(calendar.getTimeInMillis());

	}

	public static Timestamp getEmailNotificationTime(Date date) {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" 08:00:00";
		return convertStringToTimestampForEmail(toDate);
	}

	public static Date getYesterdayDateForWFResults(Date date) throws ParseException {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"");
		return convertStringToDates(toDate);
	}

	public static String getYesterdayDate(Date date) throws ParseException {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE,-1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return formatStringToFomattedDate(toDate);
	}

	public static String getTodayDate(String datestr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(datestr).toString();
		//System.out.println(formattedDate);
		return formattedDate;
	}

	public static Timestamp getYesterdayTimestamp() throws ParseException {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"");
		return convertStringToTimestampMTTT(toDate);
	}

	public static Timestamp getOutageEndTime() throws ParseException {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+"-"+get2DigitMonth(month+"")+"-"+get2DigitMonth(day+"")+" 06:00:00";
		return convertStringToTimestamp(toDate);
	}

	public static String getTransdateForMonthStart() {
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		String toDate = year+"-"+get2DigitMonth(month+"")+"-01";
		return toDate;
	}


	public static Timestamp formatStartTime(Timestamp date) throws ParseException {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String hour = cal.get(Calendar.HOUR_OF_DAY)+"";
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		toDate =year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"")+" "+hour+":"+min+":"+sec;
		return convertStringToTimestampForEmail(toDate);
	}

	public static long getTimeDifference(Timestamp startDate, Timestamp endDate) {
		long diff = endDate.getTime() - startDate.getTime();
		long diffSeconds = diff / 1000 % 60;
		return diffSeconds;
	}

	public static Timestamp convertStringToTimestampMTT(String timeString) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date parsedDate = dateFormat.parse(timeString);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) { 
			//logger.info("Exception in converting: "+timeString+" to timestamp."+e);
			return  null;
		}
		return timestamp;
	}


	public static String getYesterdayDateMTT(Timestamp time) {
		String toDate = null,fromDate=null;
		Date date=new Date(time.getTime()); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		//cal.add(Calendar.DATE, -1);
		int year_1 = cal.get(Calendar.YEAR);
		int month_1 = cal.get(Calendar.MONTH)+1;
		int day_1 = cal.get(Calendar.DAY_OF_MONTH);
		fromDate =year_1+"-"+get2DigitMonth(month_1+"")+"-"+get2DigitMonth(day_1+"");

		return fromDate;
	}

	public static String getFormattedMttTime(Timestamp currentDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		System.out.println(formatter.format(currentDate));       
		return formatter.format(currentDate);
	}


	public static String getTodayDate() throws ParseException {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return formatStringToFomattedDate(toDate);
	}


	public static Timestamp getCurrentTime(Date date) {
		Timestamp ts = null;
		String dateStr = DateUtil.formatDateToString(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		String finalDateStr = dateStr+" "+hour+":15:00";
		ts = convertStringToTimestamp(finalDateStr);
		//logger.info("Specific Time: "+ts);
		return ts;
	}


	public static String getTransdateForToday() {
		String toDate = null;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		toDate =year+""+get2DigitMonth(month+"")+""+get2DigitMonth(day+"");
		return toDate;
	}

	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String getCurrentHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		if(hour.length()==1) {
			hour="0"+hour;
		}
		return hour;
	}

	public static String getTranshrmin() {
		String minutes = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		if(hour.length()==1) {
			hour="0"+hour;
		}
		minutes = calendar.get(Calendar.MINUTE)+"";

		return hour+minutes;
	}

	public static boolean isValidTime(Timestamp generatedTime) {
		long timeDiff = getTimeDifferenceInSeconds(generatedTime.getTime(), System.currentTimeMillis());
		if(timeDiff<600) 
			return true;
		return false;
	}
	
	public static Timestamp getTimeAfterSeconds(Timestamp creationTime, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(creationTime.getTime());
        cal.add(Calendar.SECOND, seconds);
        Timestamp later = new Timestamp(cal.getTime().getTime());
		return later;
	}





	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		System.out.println(DateUtil.getTodayDate());


	}

}