package com.youflix.cust.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public final class Param {
	
	//-- Global Fields --//
	//
	private Secure _secure = null;
	//
	private StringUtil strUtil = null;
	//
	private ArrayList<String> paramNames = null;
	//
	private HashMap<String, Object> paramMap = null;
	//
	//-- Global Fields --//
	
	
	//-- Getter / Setter --//
	//
	public ArrayList<String> getParamNames() {
		return paramNames;
	}
	public HashMap<String, Object> getParamMap() {
		return paramMap;
	}
	public void setParamNames(ArrayList<String> paramNames) {
		this.paramNames = paramNames;
	}
	public void setParamMap(HashMap<String, Object> paramMap) {
		this.paramMap = paramMap;
	}


	public Param() {
		
	}
	

	public String getParamXSS(HttpServletRequest _request, String rTarget, String defaultValue, String avatag) {
		
		String result = null;
		
		if(_secure == null)
			_secure = new Secure();
		
		try {
			
			result = _secure.clearXSS(_request.getParameter(rTarget), avatag);
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			if(result == null) {
				
				result = defaultValue;
				
			} else {
				
				if(result.trim().length() < 1)
					result = defaultValue;
				
			}
			
		}
		
		return result;
		
	}
	
	

	public int getParamXSS(HttpServletRequest _request, String rTarget, int defaultValue, String avatag) {
		
		int result = 0;
		
		if(_secure == null)
			_secure = new Secure();
		
		try {
			
			result = Integer.parseInt(_secure.clearXSS(_request.getParameter(rTarget), avatag));
			
		} catch(Exception ex) {
			
			result = defaultValue;
			
		}
		
		return result;
		
	}
	
	

	public String getParam(HttpServletRequest _request, String rTarget, String defaultValue) {
		
		String result = null;
		
		try {
			
			result = _request.getParameter(rTarget);
			
		} catch(Exception ex) {
			
		} finally {
			
			if(result == null) {
				result = defaultValue;
				
			} else {
				
				if(result.trim().length() < 1)
					result = defaultValue;
				
			}
			
		}
		
		return result;
		
	}
	
	

	public int getParam(HttpServletRequest _request, String rTarget, int defaultValue) {
		
		int result = 0;
		
		try {
			
			result = Integer.parseInt(_request.getParameter(rTarget));
			
		} catch(Exception ex) {
			
			result = defaultValue;
			
		}
		
		return result;
		
	}
	

	public void getParamsXSS(HttpServletRequest request, HttpServletResponse response, String avatag) {
		
		if(_secure == null)
			_secure = new Secure();
		
		if(strUtil == null)
			strUtil = new StringUtil();
		
		paramNames = new java.util.ArrayList<String>();
		paramMap   = new java.util.HashMap<String, Object>();
		
		Enumeration<String> ee = request.getParameterNames();
			
		while(ee.hasMoreElements()) {
		
			String temp1 = (String)ee.nextElement();
			
			paramNames.add( temp1 );
			
			//-- --//
			
			String[] paramValues = request.getParameterValues(temp1);
			
			String _paramValues = "";
			
			@SuppressWarnings("unused")
			String contentType = request.getContentType();
			
			if(paramValues.length > 1) {
				
				for(int j=0; j<paramValues.length; j++) {
					
					if(j < paramValues.length - 1)
						_paramValues += paramValues[j] + ",";
					else
						_paramValues += paramValues[j];
				}
				
			} else {
				
				_paramValues = paramValues[0];
			}	
			paramMap.put( temp1, _secure.clearXSS(_paramValues, avatag) );
		}
		
	}
	
	

	public void getParams(HttpServletRequest request, HttpServletResponse response) {
		
		if(strUtil == null)
			strUtil = new StringUtil();
		
		paramNames = new java.util.ArrayList<String>();
		paramMap   = new java.util.HashMap<String, Object>();
		
		Enumeration<String> ee = request.getParameterNames();
		
		while(ee.hasMoreElements()) {
		
			String temp1 = (String)ee.nextElement();
			
			paramNames.add( temp1 );
			
			//-- --//
			
			String[] paramValues = request.getParameterValues(temp1);
			
			String _paramValues = "";
			
			@SuppressWarnings("unused")
			String contentType = request.getContentType();
			
			if(paramValues.length > 1) {
				
				for(int j=0; j<paramValues.length; j++) {
					
					if(j < paramValues.length - 1)
						_paramValues += paramValues[j] + ",";
					else
						_paramValues += paramValues[j];

				}
				
			} else {
				
				_paramValues = paramValues[0];
				
			
				
			}	
			
			//-- --//
			
			paramMap.put( temp1, _paramValues );
			
		}
	
		
	}
	
}
