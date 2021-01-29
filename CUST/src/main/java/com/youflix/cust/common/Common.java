package com.youflix.cust.common;

import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Common {


	public  String NVL(String rValue, String nullValue) {
		
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
	
}
