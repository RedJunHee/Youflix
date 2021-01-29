package com.youflix.cust.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import com.youflix.cust.model.ResultMapType2;
import com.youflix.cust.service.LogService;
import com.youflix.cust.util.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;

@EnableCaching
@Controller
public class ControllerBase {

   @Autowired
   private LogService logService;
	
	protected HashMap<String, String> UserAgentMap = new HashMap<String, String>();
	
	StringBuffer log_MSG= new StringBuffer();


	/**
	    * @FileName    : CheckUserAgent
	    * @Project     : ContentAPI MY
	    * @Date        : 2020.11.20
	    * @Author      : 조 준 희
	    * @Description : 
	    * @History     :
    */
	

	

    private final HashMap<String, String> allowSvcLog = new HashMap<String, String>() {
		
		private static final long serialVersionUID = -1018518953701185943L;
		{
			//-- VOD
			put("get_user_data	".trim(), "Y");
		}
		
	};
	
	//적용예정
	
	public enum DBLogType{
		FAIL(0), OK(1);
	    int value;
	    
	    private DBLogType(int value){
	        this.value = value;
	    }
	    public int GET_DBLogType(){
	        return value;
	    }
	    
	}

    public static HashMap<String, Object> getRequestParameterInfo(HttpServletRequest request, HttpServletResponse response) {
		
    	Param param=null;
    	
		if(param == null)
			param = new Param();
		
		HashMap<String, Object> paramMap = null;

		if(param != null) {
			
			param.getParamsXSS(request, response, "");

			paramMap = param.getParamMap();			
		}
		return paramMap;		
	}

      
	protected void writeApiCallLog(HttpServletRequest request, HttpServletResponse response, String rApiName, String function_desc) {
		
		if(rApiName != null && rApiName.trim().length() > 0)
			request.setAttribute( "APISTART ".trim(), new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss").format(new Date()));
		    request.setAttribute( "START    ".trim(), System.currentTimeMillis ()           );
		    request.setAttribute( "FUNCTION_DESC".trim(), function_desc );

		    try {
		       	//	String url = request.getRequestURL().toString() + Utils.getQueryString(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}


	/**
    * @FileName    : displayResponseData
    * @Project     : ContentAPI MY
    * @Date        : 2020.11.20
    * @Author      : 조 준 희
    * @Description : Input, Output, LogMsg받아 Response데이터 전송
    * @History     :
    */
   protected void displayResponseData(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> paramMap, int Result, String result, String logResult) throws IOException {
	      final Long      endTime  = System.currentTimeMillis();
	      
	      if(result == null || result.trim().length() <= 0)
	         result = "";
	      
	      response.setContentType      ("text/plain".trim());
	      response.setCharacterEncoding("utf-8     ".trim());
	      
	      PrintWriter out = null;
	      try { out = response.getWriter(); } catch (IOException e) { e.printStackTrace(); }
	      
	      if(out != null) {
	         
	         out.print(result);
	         out.flush();
	         out.close();
	         
	      }
	      
	      final String apiUrl      = request.getRequestURI();
	      final String apiName     = apiUrl.substring( apiUrl.lastIndexOf("/")+1 ).toLowerCase();

	      if( allowSvcLog.containsKey(apiName) ) 
	      {
	         if( "Y".equals(allowSvcLog.get(apiName)) || "R".equals( allowSvcLog.get(apiName)) ) 
	         {
	            try
	            {
	               log_MSG.setLength(0);
	               log_MSG.append("<INPUT>\n");
	               log_MSG.append(paramMap.toString().replaceAll("[{}]", "").replace(",", "\n"));
	               log_MSG.append("\n</INPUT>");
	               log_MSG.append("\n<OUTPUT>\n");
	               log_MSG.append(logResult);
	               log_MSG.append("\n</OUTPUT>");               
	               final String  function_desc = (String) request.getAttribute("FUNCTION_DESC ".trim());
         		   final String  api_startTime = (String) request.getAttribute("APISTART ".trim());
         		   final Long     startTime =    (Long)   request.getAttribute("START    ".trim());
                   logService.WriteServiceLog(function_desc, api_startTime, startTime, endTime, apiName, Result, log_MSG);                  
	            } 
	            catch (Exception e) 
	            {               
	               e.printStackTrace();
	            }
	         }
	         
	         if (Result == 0) 
	        	 LogService.WriteFileLog(apiName, log_MSG);   
	      }
	   }
	

    
	
}
