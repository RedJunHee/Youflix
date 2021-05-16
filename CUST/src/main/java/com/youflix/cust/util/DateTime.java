package com.youflix.cust.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;




public final class DateTime {
	
	public DateTime() {
		
	}
	
	/**
	 * @FileName : 현재 날짜와 시각을 "yyyy-MM-dd HH:mm:ss" 형태로 변환 후 return.
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : - 사용 예 String date = DateTime.getCurrentDateTimeToString()
	 * @History :
	 */

   public static String getCurrentDateTimeToString() {
       Date today = new Date();
       Locale currentLocale = new Locale("KOREAN", "KOREA");
       String pattern = "yyyy-MM-dd HH:mm:ss";
       SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
       return formatter.format(today);
   }
   
   
	/**
	 * @FileName : 입력받은  DateString를 Date로 변환
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : - 사용 예  Date date = DateTime.StringToDate("1999-10-21 10:54:44.111 ")
	 * @History :
	 */
   
	public static Date getStringToDate(String SimpleFormat ) throws Exception
	{
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {	
			return fm.parse(SimpleFormat);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * @FileName : 입력받은 Date를 "yyyy-MM-dd HH:mm:ss" 형식의 문자열로 변환
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : - 사용 예 String date = DateTime.getDateToString( DateTime.getCurrentDateTimeToString() )
	 * @History :
	 */
	public static String getDateToString(Date date )
	{
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return fm.format(date);

	}
	
	/**
	 * @FileName : 입력받은 Date를  pattern 형식의 문자열로 변환
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : - 사용 예 String date = DateTime.getDateToString( new Date, "yyyy-MM-dd HH:mm:ss" )
	 * @History :
	 */
	  public static String getDateToString(Date date, String pattern) {
	      Date today = date;
	      if (today == null)
	          today = new Date();
	      Locale currentLocale = new Locale("KOREAN", "KOREA");
	      // String pattern = "yyyyMMddHHmmss";
	      SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
	      return formatter.format(today);
	  }
	
	  
	  
	/**
	 * @FileName : 입력받은 Date의 시간차이 계산 
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : - 사용 예 String date = DateTime.getDateDiffToString( Date1, Date2 )
	 *  				출력형식 = "01:00:12"
	 * @History :
	 */
	public static String getDateDiffToString(Date date1, Date date2)
	  {
		Long diff = (date2.getTime()-date1.getTime())/1000 ;
		Long h = diff / 3600;
		diff = diff%3600;
		 
		Long m = diff / 60;
		diff = diff%60;
		Long s = diff;
				
		return String.format("%02d:%02d:%02d",h,m,s);

		  
	  }
	
	
	 private ZonedDateTime  getZoneData( String originalDate, String originalTime , String strZoneId ) {

		    String[] strDate= originalDate.split("-");
		    int year = Integer.parseInt( strDate[0] );
		    int month = Integer.parseInt( strDate[1] );
		    int day = Integer.parseInt( strDate[2] );
		    String[] strTime = originalTime.split(":");
		    int hour = Integer.parseInt( strTime[0] );
		    int minute = Integer.parseInt( strTime[1] );
		    int second = Integer.parseInt( strTime[2] );
		    int nano = 0 ;

		    ZoneId  zoneIdData = ZoneId.of(strZoneId);


		    return   ZonedDateTime.of( year, month, day ,  hour, minute, second, nano, zoneIdData);
		  }
	 

		
	 public Long getBetWeenTime(Long startDate , Long endDate ,String getTimeType ){
		    String[] strStart = startDate.toString().split(" ");
		    String[] endStart = endDate.toString().split(" ");

		    ZonedDateTime stDay = getZoneData(strStart[0], strStart[1], "Asia/Seoul");
		    ZonedDateTime edDay =  getZoneData(endStart[0] , endStart[1] , "Asia/Seoul");

		    Duration d = Duration.between( stDay , edDay );

		    long toTime= 0 ;
		    if(getTimeType.equals("min")) {

		      return d.toMinutes();
		    }
		    else if(getTimeType.equals("hour")) {
		            return d.toHours();

		    }else if(getTimeType.equals("sec")) {
		      return d.toMillis()/1000;
		    }else {
		      return d.toMinutes();
		    }

		  }
	 
	




}
