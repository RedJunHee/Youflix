package com.youflix.cust.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
//import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.inject.Inject;
import com.youflix.cust.common.Common;
import com.youflix.cust.common.UserCookie;
import com.youflix.cust.exception.CookieValidationException;
import com.youflix.cust.model.BaseModel;
import com.youflix.cust.service.LogService;
//import com.youflix.cust.model.ResultMapType2;
//import com.youflix.cust.service.LogService;
import com.youflix.cust.util.Param;
//import org.springframework.stereotype.Controller;

public class ControllerBase {

   @Autowired
   private LogService logService;

    private final HashMap<String, String> allowSvcLog = new HashMap<String, String>() {
		
		private static final long serialVersionUID = -1018518953701185943L;
		{
			//-- VOD
			put("sign_up	".trim(), "Y");
			put("sign_in	".trim(), "Y");
			put("play_video".trim(), "Y");
			put("check_cust_email_duplicate".trim(), "Y");
			put("play_video".trim(), "Y");
			put("play_end".trim(), "Y");
			put("log_out".trim(), "Y");
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

	
	/**
	    * @throws Exception 
	 * @FileName    : CheckCookie
	    * @Project     : CUST
	    * @Date        : 2021.02.17
	    * @Author      : 조 준 희
	    * @Description : 
	    * @History     :
	    */
	protected UserCookie CheckCookie(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserCookie userCookie = null ;
		try {

			HashMap<String,String> cookieMap = Common.GetYouflixCookieValue(request, response);
			
			userCookie = new UserCookie(
					 Common.NVL(request.getHeader("cookie"), "")
					, cookieMap.get("CUST_EMAIL").toString()
					, cookieMap.get("SESSION_ID").toString()
					);
			
			return userCookie;
			
		}
		catch(Exception ex) {
			throw new CookieValidationException(request.getHeader("cookie"));
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

      
    
	/**
     * @FileName    : Request Attribute 부여
     * @Project     : CUST
     * @Date        : 2020.11.20
     * @Author      : 조 준 희
     * @Description : Request 속성에 API 시작 시간, API 명 속성을 부여한다.
     * @History     :
     */
	protected void writeApiCallLog(HttpServletRequest request, HttpServletResponse response, String rApiName, String function_desc) {
		
		if(rApiName != null && rApiName.trim().length() > 0)
			request.setAttribute( "APISTART ".trim(), new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss").format(new Date()));
		    request.setAttribute( "START    ".trim(), System.currentTimeMillis ()           );
		    request.setAttribute( "FUNCTION_DESC".trim(), function_desc );
	}

	/**
     * @throws Exception 
	 * @FileName    : POST Request Body Get
     * @Project     : CUST
     * @Date        : 2021.02.01
     * @Author      : 조 준 희
     * @Description : POST요청의 BODY 반환
     * @History     :
     */
	protected String getPostRequestBody (HttpServletRequest request) throws Exception
	{
		StringBuffer sb = new StringBuffer();
		InputStream is ;
		BufferedReader br ;
		
		try {
			is = request.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String readLine;
			while( (readLine = br.readLine()) != null) 
			{
				sb.append(readLine);
			}
			return sb.toString();
		}catch(Exception e)
		{
			throw e;
		}		
	}
	
	/**
    * @FileName    : displayResponseData
    * @Project     : CUST
    * @Date        : 2020.11.20
    * @Author      : 조 준 희
    * @Description : Input, Output, LogMsg받아 Response데이터 전송
    * @History     :
    */
   protected void displayResponseData(HttpServletRequest request, HttpServletResponse response, BaseModel inputModel, DBLogType Result, String result, String logResult) throws IOException {
	  StringBuffer log_MSG = new StringBuffer();   
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
      final String  function_desc = (String) request.getAttribute("FUNCTION_DESC ".trim());
	  final String  api_startTime = (String) request.getAttribute("APISTART ".trim());
	  final Long     startTime =    (Long)   request.getAttribute("START    ".trim());
      if( allowSvcLog.containsKey(apiName) ) 
      {
         if( "Y".equals(allowSvcLog.get(apiName)) || "R".equals( allowSvcLog.get(apiName)) ) 
         {
            try
            {
               log_MSG.setLength(0);
               log_MSG.append("<INPUT>\n");
               if(inputModel != null)
            	   log_MSG.append(inputModel.toString());
               log_MSG.append("\n</INPUT>");
               log_MSG.append("\n<OUTPUT>\n");
               log_MSG.append(logResult);
               log_MSG.append("\n</OUTPUT>");               
               logService.WriteServiceLog(function_desc, startTime, endTime, apiName, Result, log_MSG.toString());                  
            } 
            catch (Exception e) 
            {               
               e.printStackTrace();
            	//logService.WriteServiceLog(function_desc, api_startTime, startTime, endTime, apiName, Result.GET_DBLogType(), e.getMessage());
            }
         }
         
        // if (Result.GET_DBLogType() == DBLogType.FAIL.GET_DBLogType()) 
        	 //LogService.WriteFileLog(apiName, log_MSG.toString());   
      }
   }
	

    
	
}
