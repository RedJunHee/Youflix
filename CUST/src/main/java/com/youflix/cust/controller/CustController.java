package com.youflix.cust.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import com.youflix.cust.common.UserCookie;
import com.youflix.cust.exception.CookieValidationException;
import com.youflix.cust.model.*;
import com.youflix.cust.service.CMSService;
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
	@Inject
	private CMSService cmsService;
	/**
	 * @throws Exception 
	 * @FileName : 테스트 (sign_up)
	 * @Project : CUST
	 * @Date : 2021.02.01
	 * @Author : 조 준 희
	 * @Description : 사용자 회원 가입
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/test" })
	public String test(HttpServletRequest request, HttpServletResponse response)  {
		String ip = "";
		InetAddress local;
		UserCookie cookie ;
		try { 
			cookie = CheckCookie(request, response);
			local = InetAddress.getLocalHost(); 
			 ip = local.getHostAddress(); 
			
		} 
		catch (UnknownHostException e1) 
		{ 
			e1.printStackTrace(); 
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return "test : " + ip ; 
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
			String temp = null;
			if( ( temp = mSignUp.paramCheck()) != null )
			{
				result = API_ERROR.response_error_toJson(400, temp);
			}
			else
			{
				resultMap = custService.Sign_Up(mSignUp);

				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
					ResultCode = DBLogType.OK;
				}
				else if (resultMap.get("ResultCode").equals("400")) {
					result = API_ERROR.response_error_toJson(400, "");
				}

				// Log
				logResult.append(result);
			}
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
			String temp = null;
			if( (temp =  mSignIn.paramCheck()) != null )
			{
				result = API_ERROR.response_error_toJson(400, temp);
			}
			else
			{
				resultMap = custService.Sign_In(mSignIn);

				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
					ResultCode = DBLogType.OK;
				} else if (resultMap.get("ResultCode").equals("400")) {
					result = API_ERROR.response_error_toJson(400, "");
				} 

				// Log
				logResult.append(result);
			}
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
			String temp = null;
			if( ( temp = mCheckCustEmailDuplicate.paramCheck()) != null )
			{
				result = API_ERROR.response_error_toJson(400, temp);
			}
			else
			{
				resultMap = custService.Check_Cust_Email_Duplicate(mCheckCustEmailDuplicate);

				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
					ResultCode = DBLogType.OK;
				} else if (resultMap.get("ResultCode").equals("201")) {
					result = API_ERROR.response_error_toJson(201, "");
				} 

				// Log
				logResult.append(result);
			}
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
	
	/**
	 * @FileName : 컨텐츠 재생 ( play_video)
	 * @Project : CUST
	 * @Date : 2021.02.01
	 * @Author : 조 준 희
	 * @Description : 사용자 회원 가입
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/play_video" })
	public void play_video(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "컨텐츠 재생";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		M_PLAY_VIDEO mPlayVideo = null;
		UserCookie cookie = null;
		
		try {
			cookie = CheckCookie(request, response);
			
			
			mPlayVideo  = gson.fromJson( getPostRequestBody(request), M_PLAY_VIDEO.class); 
			String temp = null;
			if( ( temp = mPlayVideo.paramCheck()) != null )
			{
				result = API_ERROR.response_error_toJson(400, temp);
			}
			else
			{
				resultMap = cmsService.Play_Video(mPlayVideo);

				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, (List<ResultMapType2>)resultMap.get("ResultDesc"), false, false, null, false, null);
					ResultCode = DBLogType.OK;
				} else if (resultMap.get("ResultCode").equals("301")) {
					result = API_ERROR.response_error_toJson(301, "");
				} 

				// Log
				logResult.append(result);
			}
		} catch( CookieValidationException e){
			result = API_ERROR.response_error_toJson(401, e.getMessage());
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
		super.displayResponseData(request, response, mPlayVideo, ResultCode, result, logResult.toString());
	}

	/**
	 * @FileName : 로그 아웃 ( log_out )
	 * @Project : CUST
	 * @Date : 2021.02.01
	 * @Author : 조 준 희
	 * @Description : 로그아웃
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/log_out" })
	public void log_out(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "로그 아웃";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		M_LOG_OUT mLogOut = null;
		UserCookie cookie = null;
		
		try {
			cookie = CheckCookie(request, response);
			
			mLogOut  = gson.fromJson( getPostRequestBody(request), M_LOG_OUT.class); 
			String temp = null;
			if( ( temp = mLogOut.paramCheck()) != null )
			{
				result = API_ERROR.response_error_toJson(400, temp);
			}
			else
			{
				resultMap = custService.Log_Out(mLogOut);

				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
					ResultCode = DBLogType.OK;
				} else if(resultMap.get("ResultCode").equals("401")) {
					result = API_ERROR.response_error_toJson(401, "존재하지 않는 SESSION_ID");
				}

				// Log
				logResult.append(result);
			}
		} catch( CookieValidationException e){
			result = API_ERROR.response_error_toJson(401, e.getMessage());
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
		super.displayResponseData(request, response, mLogOut, ResultCode, result, logResult.toString());
	}
	
	
	/**
	 * @FileName : 시청 종료 ( play_end)
	 * @Project : CUST
	 * @Date : 2021.02.01
	 * @Author : 조 준 희
	 * @Description : 시청 종료
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/play_end" })
	public void play_end(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "재생 종료";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		M_PLAY_END mPlayEnd = null;
		UserCookie cookie = null;
		
		try {
			cookie = CheckCookie(request, response);
			
			mPlayEnd  = gson.fromJson( getPostRequestBody(request), M_PLAY_END.class); 
			String temp = null;
			if( ( temp = mPlayEnd.paramCheck()) != null )
			{
				result = API_ERROR.response_error_toJson(400, temp);
			}
			else
			{
				resultMap = custService.Play_End(mPlayEnd);

				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
					ResultCode = DBLogType.OK;
				}

				// Log
				logResult.append(result);
			}
		} catch( CookieValidationException e){
			result = API_ERROR.response_error_toJson(401, e.getMessage());
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
		super.displayResponseData(request, response, mPlayEnd, ResultCode, result, logResult.toString());
	}
	
}
