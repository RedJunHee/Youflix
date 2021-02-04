package com.youflix.cust.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.youflix.cust.common.API_ERROR;
import com.youflix.cust.model.*;
import com.youflix.cust.service.CUSTService;
import com.youflix.cust.util.DateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustController extends ControllerBase {

	public CustController(HttpServletRequest request) throws Exception {
	}

	@Inject
	private CUSTService custService;

	/**
	 * @FileName : 사용자 회원 가입 (sign_up)
	 * @Project : CUST
	 * @Date : 2021.02.01
	 * @Author : 조 준 희
	 * @Description : 사용자 회원 가입
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/sign_up" })
	public void sign_up(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "사용자 회원 가입";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		M_SIGN_UP mSignUp = null ;
		
		try {
			mSignUp  = gson.fromJson( getPostRequestBody(request), M_SIGN_UP.class); 
			
				resultMap = custService.Sign_Up(mSignUp);

				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
					ResultCode = DBLogType.OK;
				} else if (resultMap.get("ResultCode").equals("400")) {
					result = API_ERROR.response_error_toJson(400, "");
				} 

				// Log
				logResult.append(result);

			} catch (Exception e) {
				//e.printStackTrace();
				result = API_ERROR.response_error_toJson(599, e.getMessage());

				// Exception Log String
				logResult.setLength(0);
				logResult.append(
						String.format("[%s]", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
				logResult.append(System.getProperty("line.separator"));
				logResult.append("RESULT=ERROR");
				logResult.append(System.getProperty("line.separator"));
				logResult.append("ErrorCode=599");
				logResult.append(System.getProperty("line.separator"));
				logResult.append("ErrorMsg=" + result);
				logResult.append(System.getProperty("line.separator"));

			}
		
		// -- Response --//
		super.displayResponseData(request, response, mSignUp, ResultCode, result, logResult.toString());
	}
	
	
	/**
	 * @FileName : 사용자 회원 가입 (sign_up)
	 * @Project : CUST
	 * @Date : 2021.02.01
	 * @Author : 조 준 희
	 * @Description : 사용자 회원 가입
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/sign_in" })
	public void sign_in(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "사용자 로그인";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		M_SIGN_IN mSignIn =null;
		
		try {
			mSignIn  = gson.fromJson( getPostRequestBody(request), M_SIGN_IN.class); 
			
				resultMap = custService.Sign_In(mSignIn);

				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
					ResultCode = DBLogType.OK;
				} else if (resultMap.get("ResultCode").equals("400")) {
					result = API_ERROR.response_error_toJson(400, "");
				} 

				// Log
				logResult.append(result);

			} catch (Exception e) {
				//e.printStackTrace();
				result = API_ERROR.response_error_toJson(599, e.getMessage());

				// Exception Log String
				logResult.setLength(0);
				logResult.append(String.format("[%s]", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
				logResult.append(System.getProperty("line.separator"));
				logResult.append("RESULT=ERROR");
				logResult.append(System.getProperty("line.separator"));
				logResult.append("ErrorCode=599");
				logResult.append(System.getProperty("line.separator"));
				logResult.append("ErrorMsg=" + result);
				logResult.append(System.getProperty("line.separator"));

			}
		
		// -- Response --//
		super.displayResponseData(request, response, mSignIn, ResultCode, result, logResult.toString());
	}
	
	
	/**
	 * @FileName : 사용자 Email 중복 체크 (check_cust_email_duplicate)
	 * @Project : CUST
	 * @Date : 2021.02.01
	 * @Author : 조 준 희
	 * @Description : 사용자 회원 가입
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/check_cust_email_duplicate" })
	public void check_cust_email_duplicate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "사용자 Email 중복 체크";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		M_CHECK_CUST_EMAIL_DUPLICATE mCheckCustEmailDuplicate = null;
		
		try {
			mCheckCustEmailDuplicate  = gson.fromJson( getPostRequestBody(request), M_CHECK_CUST_EMAIL_DUPLICATE.class); 
			
				resultMap = custService.Check_Cust_Email_Duplicate(mCheckCustEmailDuplicate);

				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
					ResultCode = DBLogType.OK;
				} else if (resultMap.get("ResultCode").equals("201")) {
					result = API_ERROR.response_error_toJson(201, "");
				} 

				// Log
				logResult.append(result);

			} catch (Exception e) {
				//e.printStackTrace();
				result = API_ERROR.response_error_toJson(599, e.getMessage());

				// Exception Log String
				logResult.setLength(0);
				logResult.append(
						String.format("[%s]", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
				logResult.append(System.getProperty("line.separator"));
				logResult.append("RESULT=ERROR");
				logResult.append(System.getProperty("line.separator"));
				logResult.append("ErrorCode=599");
				logResult.append(System.getProperty("line.separator"));
				logResult.append("ErrorMsg=" + result);
				logResult.append(System.getProperty("line.separator"));

			}
		
		// -- Response --//
		super.displayResponseData(request, response, mCheckCustEmailDuplicate, ResultCode, result, logResult.toString());
	}
}
