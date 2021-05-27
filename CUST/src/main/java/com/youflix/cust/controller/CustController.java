package com.youflix.cust.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.youflix.cust.common.API_ERROR;
import com.youflix.cust.common.UserCookie;
import com.youflix.cust.exception.CookieValidationException;
import com.youflix.cust.model.*;
import com.youflix.cust.service.CMSService;
import com.youflix.cust.service.CUSTService;
import com.youflix.cust.service.LogService;
import com.youflix.cust.util.DateTime;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	LogService logService;
	
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
	 * @FileName : 사용자 로그인 (log_in)
	 * @Project : CUST
	 * @Date : 2021.02.01
	 * @Author : 조 준 희
	 * @Description : 사용자 로그인
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/log_in" })
	public void sign_in(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "사용자 로그인";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		M_LOG_IN mSignIn =null;
		
		try {
			mSignIn  = gson.fromJson( getPostRequestBody(request), M_LOG_IN.class); 
			String temp = null;
			if( (temp =  mSignIn.paramCheck()) != null )
			{
				result = API_ERROR.response_error_toJson(400, temp);
			}
			else
			{
				resultMap = custService.Log_In(mSignIn);

				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, (List<ResultMapType2>)resultMap.get("ResultDesc"), false, false, null, false, null);
					ResultCode = DBLogType.OK;
				} else if (resultMap.get("ResultCode").equals("401")) {
					result = API_ERROR.response_error_toJson(401, "");
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
			mPlayVideo.setSESSION_ID(cookie.getSESSION_ID());
			mPlayVideo.setCUST_EMAIL(cookie.getCUST_EMAIL());
			
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
			mLogOut.setSESSION_ID(cookie.getSESSION_ID());
			
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
			mPlayEnd.setSESSION_ID(cookie.getSESSION_ID());
			
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
	
	/**
	 * @FileName : TOP 10 비디오 리스트 ( top_10_list )
	 * @Project : CUST
	 * @Date : 2021.03.04
	 * @Author : 조 준 희
	 * @Description : TOP 10 비디오 리스트
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/top_10_list" })
	public void top_10_list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "TOP 10 비디오 리스트";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		UserCookie cookie = null;
		
		try {
			cookie = CheckCookie(request, response);

			resultMap = cmsService.Top_10_list();
	
			if (resultMap.get("ResultCode").equals("200")) {
				List<ResultMapType2> resultDesc = (List<ResultMapType2>)resultMap.get("ResultDesc");
				result = API_ERROR.response_success_toJson(200, resultDesc, true, true, String.valueOf(resultDesc.size()), false, null);
				ResultCode = DBLogType.OK;
			}
	
			// Log
			logResult.append(result);
			
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
		super.displayResponseData(request, response, null, ResultCode, result, logResult.toString());
	}
	
	/**
	 * @FileName : 카테고리 비디오박스 리스트 ( category_video_list )
	 * @Project : CUST
	 * @Date : 2021.05.12
	 * @Author : 조 준 희
	 * @Description : 카테고리별 비디오 박스 리스트
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/category_video_list" })
	public void category_video_list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "카테고리별 비디오 박스 리스트";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		UserCookie cookie = null;
		M_CATEGORY_VIDEO_LIST mCategoryVideoList;
		try {
			cookie = CheckCookie(request, response);

			mCategoryVideoList  = gson.fromJson( getPostRequestBody(request), M_CATEGORY_VIDEO_LIST.class); 
			
			resultMap = cmsService.CategoryVideoList(mCategoryVideoList);
	
			if (resultMap.get("ResultCode").equals("200")) {
				List<ResultMapType2> resultDesc = (List<ResultMapType2>)resultMap.get("ResultDesc");
				result = API_ERROR.response_success_toJson(200, resultDesc, true, true, String.valueOf(resultDesc.size()), false, null);
				ResultCode = DBLogType.OK;
			}
	
			// Log
			logResult.append(result);
			
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
		super.displayResponseData(request, response, null, ResultCode, result, logResult.toString());
	}

	/**
	 * @FileName : Youflix 메인 추천 비디오 박스 ( recommend_video )
	 * @Project : CUST
	 * @Date : 2021.05.12
	 * @Author : 조 준 희
	 * @Description : Youflix 메인 추천 비디오 박스
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/recommend_video" })
	public void recommend_video(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "Youflix 메인 추천 비디오 박스";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		UserCookie cookie = null;
		try {
			cookie = CheckCookie(request, response);

		
			resultMap = cmsService.RecommendVideo();
	
			if (resultMap.get("ResultCode").equals("200")) {
				List<ResultMapType2> resultDesc = (List<ResultMapType2>)resultMap.get("ResultDesc");
				result = API_ERROR.response_success_toJson(200, resultDesc, true, true, String.valueOf(resultDesc.size()), false, null);
				ResultCode = DBLogType.OK;
			}
	
			// Log
			logResult.append(result);
			
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
		super.displayResponseData(request, response, null, ResultCode, result, logResult.toString());
	}

	/**
	 * @FileName : 유저별 인기 비디오타입 리스트 ( popular_type_list )
	 * @Project : CUST
	 * @Date : 2021.05.12
	 * @Author : 조 준 희
	 * @Description : 유저별 인기 비디오타입 리스트
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/popular_type_list" })
	public void popular_type_list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "유저별 인기 비디오타입 리스트";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		UserCookie cookie = null;
		M_POPULAR_TYPE_LIST mPopularTypeList;
		try {
			cookie = CheckCookie(request, response);
			
			mPopularTypeList = new M_POPULAR_TYPE_LIST();
			mPopularTypeList.setCUST_EMAIL(cookie.getCUST_EMAIL());
			
			resultMap = cmsService.PopularTypeList(mPopularTypeList);
	
			if (resultMap.get("ResultCode").equals("200")) {
				List<ResultMapType2> resultDesc = (List<ResultMapType2>)resultMap.get("ResultDesc");
				result = API_ERROR.response_success_toJson(200, resultDesc, true, true, String.valueOf(resultDesc.size()), false, null);
				ResultCode = DBLogType.OK;
			}
	
			// Log
			logResult.append(result);
			
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
		super.displayResponseData(request, response, null, ResultCode, result, logResult.toString());
	}

	/**
	 * @FileName : 유저 구매 ( cust_purchase )
	 * @Project : CUST
	 * @Date : 2021.05.27
	 * @Author : 조 준 희
	 * @Description : 유저 구매
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/cust_purchase" })
	public void cust_purchase(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "유저 구매";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		UserCookie cookie = null;
		M_CUST_PURCHASE mCustPurchase;
		try {
			cookie = CheckCookie(request, response);
			
			mCustPurchase  = gson.fromJson( getPostRequestBody(request), M_CUST_PURCHASE.class); 
			mCustPurchase.setCUST_EMAIL(cookie.getCUST_EMAIL());
			String temp = null;
			if( ( temp = mCustPurchase.paramCheck()) != null )
			{
				result = API_ERROR.response_error_toJson(400, temp);
			}
			else
			{
				resultMap = custService.CUST_PURCHASE(mCustPurchase);
		
				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
					ResultCode = DBLogType.OK;
				} else if(resultMap.get("ResultCode").equals("400")) {
					result = API_ERROR.response_error_toJson(401, "구매 실패.");
				}
			}
			// Log
			logResult.append(result);
			
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
		super.displayResponseData(request, response, null, ResultCode, result, logResult.toString());
	}
	
	
	/**
	 * @FileName : 유저 환불 ( popular_type_list )
	 * @Project : CUST
	 * @Date : 2021.05.27
	 * @Author : 조 준 희
	 * @Description : 유저 환불
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/cust_refund" })
	public void cust_refund(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "유저 환불";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		UserCookie cookie = null;
		M_CUST_REFUND mCustRefund;
		try {
			cookie = CheckCookie(request, response);
			
			mCustRefund  = gson.fromJson( getPostRequestBody(request), M_CUST_REFUND.class); 
			mCustRefund.setCUST_EMAIL(cookie.getCUST_EMAIL());
			
			String temp = null;
			if( ( temp = mCustRefund.paramCheck()) != null )
			{
				result = API_ERROR.response_error_toJson(400, temp);
			}
			else
			{
				resultMap = custService.CUST_REFUND(mCustRefund);
		
				if (resultMap.get("ResultCode").equals("200")) {
					result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
					ResultCode = DBLogType.OK;
				} else if(resultMap.get("ResultCode").equals("400")) {
					result = API_ERROR.response_error_toJson(401, "환불 실패.");
				}
			}
			// Log
			logResult.append(result);
			
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
		super.displayResponseData(request, response, null, ResultCode, result, logResult.toString());
	}
	
	/**
	 * @FileName : 시청중인 비디오 리스트 ( video_keep_watching )
	 * @Project : CUST
	 * @Date : 2021.05.20
	 * @Author : 조 준 희
	 * @Description : 시청중인 비디오 리스트
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/video_keep_watching" })
	public void video_keep_watching(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "시청중인 비디오 리스트";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		UserCookie cookie = null;
		
		try {
			cookie = CheckCookie(request, response);
			
			resultMap = cmsService.VideoKeepWatching(cookie.getCUST_EMAIL());
	
			if (resultMap.get("ResultCode").equals("200")) {
				List<ResultMapType2> resultDesc = (List<ResultMapType2>)resultMap.get("ResultDesc");
				result = API_ERROR.response_success_toJson(200, resultDesc, true, true, String.valueOf(resultDesc.size()), false, null);
				ResultCode = DBLogType.OK;
			}
	
			// Log
			logResult.append(result);
			
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
		super.displayResponseData(request, response, null, ResultCode, result, logResult.toString());
	}

	
	/**
	 * @FileName : 입수 자동화 유튜버 SubPlayList 동기화 ( ingest_get_sub_play_list )
	 * @Project : CUST
	 * @Date : 2021.03.04
	 * @Author : 조 준 희
	 * @Description : 입수 자동화 유튜버 SubPlayList 동기화
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/ingest_get_sub_play_list" })
	public void IngestGetSubPlayList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "입수 자동화 유튜버 SubPlayList 동기화";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		List<ResultMapType2> youtubersPlayList = null;
		
		try {
			youtubersPlayList = cmsService.GetYoutuberPlayList();
			
			for(int playListCount = 0 ; playListCount < youtubersPlayList.size(); playListCount++)
			{
				 Long sTime = System.currentTimeMillis();
				String playListId = youtubersPlayList.get(playListCount).get("play_list_id").toString();
			    JsonArray playListItems = GetPlayListItem(playListId).get("items").getAsJsonArray();
			    
			    StringBuffer sb = new StringBuffer();
			    
			    sb.append(String.format("유튜버 playList : %s", playListId));
			    
			    for(int itemsCount=0 ; itemsCount < playListItems.size(); itemsCount++)
			    {	    
			    	Thread.sleep(1000);

		            String videoId = playListItems.get(itemsCount).getAsJsonObject().get("contentDetails").getAsJsonObject().get("videoId").getAsString() ;
		            
					cmsService.IngestPlayListVideoRegster(new M_INGEST_SYNC_SUB_PLAY_LIST(playListId, videoId));;
		            
			    }
			    Long eTime = System.currentTimeMillis();
			    
	            logService.WriteServiceLog("유튜버 플레이 리스트 비디오 동기화", sTime, eTime, "GetVideoPlayList", DBLogType.OK, sb.toString());
	            System.out.println(playListId);

			}
			// Log
			logResult.append(result);
			
		}catch (Exception e) {
			//e.printStackTrace();
			result = API_ERROR.response_error_toJson(599, e.getMessage());

			// Exception Log String
			logResult.setLength(0);
			logResult.append(
					String.format("[%s]", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
			logResult.append("ErrorMsg=" + result);
			logResult.append(System.getProperty("line.separator"));

		}
	}
	
    private JsonObject GetPlayListItem(String playlistId) throws Exception
    {
		String playListRequestURL = 
		String.format("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet,contentDetails&maxResults=50&playlistId=%s&key=AIzaSyCi-S47mjwlbn4pENPMmexzuewKKvAX0_E"
				,playlistId);//youtubers.get(i).get("CHANNEL_ID").toString());
		URL url = new URL( playListRequestURL);
		HttpURLConnection request = (HttpURLConnection)url.openConnection();
		request.setRequestProperty("Content-Type", "application/json");
		request.setRequestProperty("Accept-Charset", "UTF-8");
		
		request.setConnectTimeout(2000);
		
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        
        StringBuffer result = new StringBuffer() ;
        String jsonData = null;
        while( (jsonData = br.readLine()) != null )
        {
        	result.append(jsonData);
        }
         
        Gson gson = new Gson();
        
        JsonObject jo = new JsonObject();
        
        jo = gson.fromJson(result.toString(),jo.getClass());
    	
        return jo;

    }
	
	/**
	 * @FileName : 비밀번호 변경 ( password_change )
	 * @Project : CUST
	 * @Date : 2021.03.21
	 * @Author : 조 준 희
	 * @Description : 비밀번호 변경
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/api/password_change" })
	public void password_change(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String function_desc = "비밀번호 변경";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);
		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		DBLogType ResultCode = DBLogType.FAIL;
		Gson gson = new Gson();
		
		M_PASSWORD_CHANGE model ;
		
		try {
			
			model  = gson.fromJson( getPostRequestBody(request), M_PASSWORD_CHANGE.class); 
			
			resultMap = custService.PasswordChange(model);
	
			if (resultMap.get("ResultCode").equals("200")) {
				result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
				ResultCode = DBLogType.OK;
			}
			else if (resultMap.get("ResultCode").equals("400")) {
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
		super.displayResponseData(request, response, null, ResultCode, result, logResult.toString());
	}
    
}
