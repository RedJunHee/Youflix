package com.youflix.cust.common;

import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Common {


	
	/**
	 * @FileName : 문자열 NULL 또는 빈값일 경우 대체문자열 반환
	 * @Project : CUST
	 * @Date : 2021.01.29
	 * @Author : 조 준 희
	 * @Description :
	 * @History :
	 */
	public static  String NVL(String rValue, String nullValue) {		
		String result = "";
		if(rValue != null && rValue.length() > 0) {
			result = rValue.trim();
		} else {			
			if(nullValue != null && nullValue.length() > 0) {				
				result = nullValue;				
			} else {				
				result = "";				
			}			
		}		
		return result;		
	}
	
	
	public synchronized static HashMap<String,String> GetYouflixCookieValue(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		HashMap<String, String> cookieMap = new HashMap<String,String>();

		for(int i = 0 ; i < cookies.length ; i++)
		{
			cookieMap.put(cookies[i].getName(),cookies[i].getValue());
		}
			
		return cookieMap;
			

	}
	
}
