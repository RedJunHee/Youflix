package com.youflix.cust.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.youflix.cust.common.API_ERROR;
import com.youflix.cust.model.*;
import com.youflix.cust.service.MYService;
import com.youflix.cust.util.DateTime;
import com.youflix.cust.service.CUSTService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController extends ControllerBase {

	public MyController(HttpServletRequest request) throws Exception {
	}

	@Inject
	private CUSTService custService;

	/**
	 * @FileName : 선호 키워드 목록 조회 (get_my_keyword)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : 선호 키워드 목록 조회 API
	 * @History :
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = { "/app6/api/get_my_keyword" })
	public void get_my_keyword(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String function_desc = "선호 키워드 목록 조회";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		int ResultCode = 0;
		HashMap<String, Object> m_param = new HashMap<String, Object>();
		UserAgent userAgent = null;
		UserCookie userCookie = null;
		
		try {

			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());

				// Log
				logResult.append(result);
				
				// -- Response --//
				super.displayResponseData(request, response, m_param, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return;
				
			} else {
				try {

					m_param.put("SUID", userCookie.getSUID());

					resultMap = myService.get_my_keyword(m_param);

					if (resultMap.get("ResultCode").equals("200")) {
						result = API_ERROR.response_success_toJson(200,
								(List<ResultMapType2>) resultMap.get("ResultDesc"), false, false, null, false, null);
						ResultCode = 1;
					} else if (resultMap.get("ResultCode").equals("204")) {
						result = API_ERROR.response_error_toJson(204, "");
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
					logResult.append("SUID=" + m_param.get("SUID").toString());
					logResult.append(System.getProperty("line.separator"));
					logResult.append("ErrorCode=599");
					logResult.append(System.getProperty("line.separator"));
					logResult.append("ErrorMsg=" + result);
					logResult.append(System.getProperty("line.separator"));

				}
			}
			// User Agent Exception
		} catch (Exception e) {
			result = API_ERROR.response_error_toJson(400, e.getMessage());

			// Log
			logResult.append(result);
		}

		// 로그에서 SUID 안보여줌.
		m_param.remove("SUID");

		// -- Response --//
		super.displayResponseData(request, response, m_param, ResultCode, result, logResult.toString(), userAgent, userCookie);
	
	}

	/**
	 * 
	 * @FileName : 선호 키워드 설정 (set_my_keyword)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : 선호 키워드 설정 (set_my_keyword)
	 * @History :
	 */
	@RequestMapping(value = { "/app6/api/set_my_keyword" })
	public void set_my_keyword(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String function_desc = "선호 키워드 설정";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();

		int ResultCode = 0;
		M_set_my_keyword m_param = new M_set_my_keyword();
		HashMap<String, Object> InputParamMap = new HashMap<String, Object>();
		UserAgent userAgent= null;
		UserCookie userCookie = null;
		try {

			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());

				// Log
				logResult.append(result);
				// -- Response --//
				super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return;
				
			} else {
				try {

					Boolean ParamCheckResult = m_param.ParamCheck(request, response);
					if (!ParamCheckResult) {
						String errMsg = m_param.ReturnParamMap.get("Error").toString();
						m_param.ReturnParamMap.remove("Error");

						result = API_ERROR.response_error_toJson(400, errMsg);
					} else {
						// input 파라미터 외 추가로 필요한 쿠키값 해시맵에 저장
						InputParamMap.putAll(m_param.ReturnParamMap);
						m_param.ReturnParamMap.put("SUID", userCookie.getSUID());

						resultMap = myService.set_my_keyword(m_param.ReturnParamMap);

						if (resultMap.get("ResultCode").equals("200")) {
							result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
							ResultCode = 1;
						} else if (resultMap.get("ResultCode").equals("204")) {
							result = API_ERROR.response_error_toJson(204, "");
						} else {
							throw new Exception("선호 키워드 등록, 해지 실패.");
						}
					}

				} catch (Exception e) {
					// e.printStackTrace();
					result = API_ERROR.response_error_toJson(599, e.getMessage());
				}
			}
		} catch (Exception e) {
			result = API_ERROR.response_error_toJson(400, e.getMessage());
		}

		// Log
		logResult.append(result);

		// -- Response --//
		super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
	}

	/**
	 * @FileName : 구매 목록 조회 (get_buy_list)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : 구매 목록 조회 (get_buy_list)
	 * @History :
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/app6/api/get_buy_list" })
	public void get_buy_list(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		
		String function_desc = "구매 목록 조회";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();
		int ResultCode = 0;
		M_get_buy_list m_param = new M_get_buy_list();
		UserAgent userAgent = null;
		UserCookie userCookie = null;
		long t = System.currentTimeMillis();
		try {

			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());

				// Log
				logResult.append(result);
				// -- Response --//
				super.displayResponseData(request, response, m_param.ReturnParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return;
				
			} else {
				try {
					System.out.println("1 : " + (System.currentTimeMillis() - t) );
					t = System.currentTimeMillis();
					Boolean ParamCheckResult = m_param.ParamCheck(request, response);

					if (!ParamCheckResult) {
						String errMsg = m_param.ReturnParamMap.get("Error").toString();
						m_param.ReturnParamMap.remove("Error");
						throw new Exception(errMsg);
					} else {

						m_param.ReturnParamMap.put("SUID", userCookie.getSUID());
						
						System.out.println("2 : " + (System.currentTimeMillis() - t) );
						t = System.currentTimeMillis();
						
						resultMap = myService.get_buy_list(m_param.ReturnParamMap);

						System.out.println("3 : " + (System.currentTimeMillis() - t) );
						t = System.currentTimeMillis();
						
						List<ResultMapType2> rowData = ((List<List<ResultMapType2>>) resultMap.get("ResultDesc"))
								.get(0);
						String totalcount = ((List<List<ResultMapType2>>) resultMap.get("ResultDesc")).get(1).get(0)
								.get("total_count").toString();

						System.out.println("4 : " + (System.currentTimeMillis() - t) );
						t = System.currentTimeMillis();
						
						if (resultMap.get("ResultCode").equals("200")) {
							result = API_ERROR.response_success_toJson(200, rowData, true, true, totalcount, true,
									m_param.ReturnParamMap.get("PAGE").toString());
							ResultCode = 1;
						} else if (resultMap.get("ResultCode").equals("204")) {
							result = API_ERROR.response_error_toJson(204, "");

						}
					}

					// Log
					logResult.append(System.getProperty("line.separator"));
					logResult.append("RESULT = OK ");
					logResult.append(System.getProperty("line.separator"));
					logResult.append(result);

				} catch (Exception e) {
					//e.printStackTrace();
					result = API_ERROR.response_error_toJson(599, e.getMessage());

					// Exception Log String
					logResult.append(" RESULT=OK SAID=");
					logResult.append(userCookie.getSAID());
					logResult.append(System.getProperty("line.separator"));

					Date startdate = new Date((Long) request.getAttribute("START".trim()));
					Date enddate = new Date(System.currentTimeMillis());
					logResult.append(String.format("<TIME>START=%s, END=%s, SPAN=%s</TIME>",
							DateTime.getDateToString(startdate), DateTime.getDateToString(enddate),
							enddate.getTime() - (Long) request.getAttribute("START".trim())));
				}
			}
		} catch (Exception e) {
			result = API_ERROR.response_error_toJson(400, e.getMessage());

			// Log
			logResult.append(result);

		}

		// -- Response --//
		super.displayResponseData(request, response, m_param.ReturnParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
	}

	/**
	 * @FileName : 책갈피 목록 조회 (get_bookmark_list)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : 책갈피 목록 조회 (get_bookmark_list)
	 * @History :
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/app6/api/get_bookmark_list" })
	public void get_bookmark_list(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String function_desc = "책갈피 목록 조회";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = null;
		StringBuffer logResult = new StringBuffer();

		int ResultCode = 0;
		M_get_bookmark_list m_param = new M_get_bookmark_list();

		UserAgent userAgent = null;
		UserCookie userCookie = null;
		try {
			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());

				// Log
				logResult.append(result);
				// -- Response --//
				super.displayResponseData(request, response, m_param.ReturnParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return;
				
			} else {

				try {

					Boolean ParamCheckResult = m_param.ParamCheck(request, response);

					if (!ParamCheckResult) {
						String errMsg = m_param.ReturnParamMap.get("Error").toString();
						m_param.ReturnParamMap.remove("Error");
						// Bed Request
						result = API_ERROR.response_error_toJson(400, errMsg);
					} else {
						m_param.ReturnParamMap.put("SUID", userCookie.getSUID());

						resultMap = myService.get_bookmark_list(m_param.ReturnParamMap);

						String totalcount = String
								.valueOf(((List<ResultMapType2>) (resultMap.get("ResultDesc"))).size());

						if (resultMap.get("ResultCode").equals("200")) {
							result = API_ERROR.response_success_toJson(200,
									(List<ResultMapType2>) resultMap.get("ResultDesc"), false, true, totalcount, false,
									null);
							ResultCode = 1;
						} else if (resultMap.get("ResultCode").equals("204")) {
							result = API_ERROR.response_error_toJson(204, "");
						}

					}

					// Log
					logResult.append(System.getProperty("line.separator"));
					logResult.append("RESULT = OK ");
					logResult.append(System.getProperty("line.separator"));
					logResult.append(result);

				} catch (Exception e) {
					//e.printStackTrace();
					result = API_ERROR.response_error_toJson(599, e.getMessage());

					// Exception Log
					logResult.append(String.format("%s : RESULT=ERROR&ErrorCode=599&ErrorMsg=SAID: %s, 마이 책갈피 조회 ",
							DateTime.getCurrentDateTimeToString(), result));

				}
			}
		} catch (Exception e) {

			result = API_ERROR.response_error_toJson(400, e.getMessage());

			// Log
			logResult.append(result);
		}

		// -- Response --//
		super.displayResponseData(request, response, m_param.ReturnParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
	}

	/**
	 * @FileName : 시청 목록 조회 (get_play_list)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : 시청 목록 조회 (get_play_list)
	 * @History :
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/app6/api/get_play_list" })
	public void get_play_list(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String function_desc = "시청 목록 조회";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();

		int ResultCode = 0;
		M_get_play_list m_param = new M_get_play_list();

		UserAgent userAgent = null;
		UserCookie userCookie = null;
		
		try {

			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());

				// Log
				logResult.append(result);
				
				// -- Response --//
				super.displayResponseData(request, response, m_param.ReturnParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return;
			} else {
				try {

						Boolean ParamCheckResult = m_param.ParamCheck(request, response);

						if (!ParamCheckResult) {
							String errMsg = m_param.ReturnParamMap.get("Error").toString();
							m_param.ReturnParamMap.remove("Error");
							throw new Exception(errMsg);
						} else {
							m_param.ReturnParamMap.put("SUID", userCookie.getSUID());

							resultMap = myService.get_play_list(m_param.ReturnParamMap);

							String totalcount = String
									.valueOf(((List<ResultMapType2>) (resultMap.get("ResultDesc"))).size());

							if (resultMap.get("ResultCode").equals("200")) {
								result = API_ERROR.response_success_toJson(200,
										(List<ResultMapType2>) resultMap.get("ResultDesc"), false, true, totalcount,
										false, null);
								ResultCode = 1;
							} else if (resultMap.get("ResultCode").equals("204")) {
								result = API_ERROR.response_error_toJson(204, "");
							}
						}

				} catch (Exception e) {
					//e.printStackTrace();
					result = API_ERROR.response_error_toJson(599, e.getMessage());

				}

				// Log
				logResult.append(result);
				logResult.append(System.getProperty("line.separator"));

				Date startdate = new Date((Long) request.getAttribute("START".trim()));
				Date enddate = new Date(System.currentTimeMillis());
				logResult.append(String.format("<TIME>START=%s, END=%s, SPAN=%s</TIME>",
						DateTime.getDateToString(startdate), DateTime.getDateToString(enddate),
						enddate.getTime() - (Long) request.getAttribute("START".trim())));

			}
		} catch (Exception e) {
			result = API_ERROR.response_error_toJson(400, e.getMessage());
			// Log
			logResult.append(result);

		}

		// -- Response --//
		super.displayResponseData(request, response, m_param.ReturnParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
	}

	/**
	 * @FileName : 홈 화면 최근 시청 목록 (last_play_info)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : 홈 화면 최근 시청 목록 (last_play_info)
	 * @History :
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/app6/api/last_play_info" })
	public void last_play_info(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String function_desc = "홈 화면 최근 시청 목록";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();

		int ResultCode = 0;
		M_last_play_info m_param = new M_last_play_info();
		HashMap<String, Object> InputParamMap = new HashMap<String, Object>();

		UserAgent userAgent = null;
		UserCookie userCookie = null;
		
		try {

			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());
				// Log
				logResult.append(result);
				
				// -- Response --//
				super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return;
				
			} else {
				try {

					Boolean ParamCheckResult = m_param.ParamCheck(request, response);

					if (!ParamCheckResult) {
						String errMsg = m_param.ReturnParamMap.get("Error").toString();
						m_param.ReturnParamMap.remove("Error");
						throw new Exception(errMsg);
					} else {
						// input 파라미터 외 추가로 필요한 쿠키값 해시맵에 저장
						InputParamMap.putAll(m_param.ReturnParamMap);
						m_param.ReturnParamMap.put("SUID", userCookie.getSUID());

						resultMap = myService.last_play_info(m_param.ReturnParamMap);

						if (resultMap.get("ResultCode").equals("200")) {
							result = API_ERROR.response_success_toJson(200,
									(List<ResultMapType2>) resultMap.get("ResultDesc"), false, false, null, false,
									null);
							ResultCode = 1;
						} else if (resultMap.get("ResultCode").equals("204")) {
							result = API_ERROR.response_error_toJson(204, "");
						}
					}

				} catch (Exception e) {
					//e.printStackTrace();
					result = API_ERROR.response_error_toJson(599, e.getMessage());

				}

				// Log
				logResult.append(result);
				logResult.append(System.getProperty("line.separator"));

				Date startdate = new Date((Long) request.getAttribute("START".trim()));
				Date enddate = new Date(System.currentTimeMillis());
				logResult.append(String.format("<TIME>START=%s, END=%s, SPAN=%s</TIME>",
												DateTime.getDateToString(startdate), DateTime.getDateToString(enddate),
												DateTime.getDateDiffToString(startdate, enddate)
											   )
								);

			}
		} catch (Exception e) {

			result = API_ERROR.response_error_toJson(400, e.getMessage());

			// Log
			logResult.append(result);
		}

		// -- Response --//
		super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
	}

	/**
	 * @FileName : My Channel 등록 (set_my_channel)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : My Channel 등록 (set_my_channel)
	 * @History :
	 */
	@RequestMapping(value = { "/app6/api/set_my_channel" })
	public void set_my_channel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String function_desc = "My Channel 등록";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();

		int ResultCode = 0;
		M_set_my_channel m_param = new M_set_my_channel();
		HashMap<String, Object> InputParamMap = new HashMap<String, Object>();

		UserAgent userAgent = null;
		UserCookie userCookie = null;
		
		try {

			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());
				// Log
				logResult.append(result);
				// -- Response --//
				super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return ;
				
			} else {

				try {

					Boolean ParamCheckResult = m_param.ParamCheck(request, response);

					if (!ParamCheckResult) {
						String errMsg = m_param.ReturnParamMap.get("Error").toString();
						m_param.ReturnParamMap.remove("Error");
						// Bed Request
						result = API_ERROR.response_error_toJson(400, errMsg);
					} else {
						// input 파라미터 외 추가로 필요한 쿠키값 해시맵에 저장
						InputParamMap.putAll(m_param.ReturnParamMap);
						m_param.ReturnParamMap.put("SUID", userCookie.getSUID());

						resultMap = myService.set_my_channel(m_param.ReturnParamMap);

						if (resultMap.get("ResultCode").equals("200")) {
							result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
							ResultCode = 1;
						} else if (resultMap.get("ResultCode").equals("204")) {
							result = API_ERROR.response_error_toJson(204, "");
						}
						else
						{
							if(m_param.ReturnParamMap.get("TYPE").toString().equals("I"))
							{
								 throw new Exception("My Channel 등록 실패.");
							}
							else
							{
								 throw new Exception("My Channel 삭제 실패.");
							}
						}
					}
					
					
				} catch (Exception e) {
					//e.printStackTrace();
					result = API_ERROR.response_error_toJson(599, e.getMessage());
				}
				
				// Log
				logResult.append(result);
				
			}
		} catch (Exception e) {
			result = API_ERROR.response_error_toJson(400, e.getMessage());

			// Log
			logResult.append(result);

		}

		// -- Response --//
		super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
	}

	/**
	 * @FileName : SLT 구매 목록 삭제 (slt_deleteall_buy_list)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : SLT 구매 목록 삭제 (slt_deleteall_buy_list)
	 * @History :
	 */
	@RequestMapping(value = { "/app6/api/slt_deleteall_buy_list" })
	public void slt_deleteall_buy_list(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String function_desc = "SLT 구매 목록 삭제";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();

		int ResultCode = 0;
		HashMap<String, Object> m_param = new HashMap<String, Object>();
		
		UserAgent userAgent = null;
		UserCookie userCookie = null;
		try {

			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());
				// Log
				logResult.append(result);
				// -- Response --//
				super.displayResponseData(request, response, m_param, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return;
				
			} else {
				try {

					m_param.put("SUID", userCookie.getSUID());

					resultMap = myService.slt_deleteall_buy_list(m_param);

					if (resultMap.get("ResultCode").equals("200")) {
						result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
						ResultCode = 1;
					}
					
					// Log
					logResult.append(" RESULT=OK ");
					
				} catch (Exception e) {
					//e.printStackTrace();
					result = API_ERROR.response_error_toJson(599, e.getMessage());
					// Exception Log
					logResult.append("RESULT=FAIL"); 
					logResult.append(System.getProperty("line.separator"));
					logResult.append(String.format("%s %s", result, e.getMessage()));
					logResult.append(System.getProperty("line.separator"));

				}
			}
		} catch (Exception e) {
			result = API_ERROR.response_error_toJson(400, e.getMessage());

			// Log
			logResult.append(result);
		}

		// -- Response --//
		super.displayResponseData(request, response, m_param, ResultCode, result, logResult.toString(), userAgent, userCookie);
	}

	/**
	 * @FileName : SLT 시청 목록 삭제 (slt_deleteall_play_list)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : SLT 시청 목록 삭제 (slt_deleteall_play_list)
	 * @History :
	 */
	@RequestMapping(value = { "/app6/api/slt_deleteall_play_list" })
	public void slt_deleteall_play_list(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String function_desc = "SLT 구매 목록 삭제";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();

		int ResultCode = 0;
		HashMap<String, Object> m_param = new HashMap<String, Object>();

		UserAgent userAgent = null;
		UserCookie userCookie = null;
		try {
			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());
				
				// Log
				logResult.append(result);
				
				// -- Response --//
				super.displayResponseData(request, response, m_param, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return;
				
			} else {
				try {

					m_param.put("SUID", userCookie.getSUID());

					resultMap = myService.slt_deleteall_play_list(m_param);

					if (resultMap.get("ResultCode").equals("200")) {
						result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
						ResultCode = 1;
					}
					
					// Log
					logResult.append(" RESULT=OK ");
					
				} catch (Exception e) {
					//e.printStackTrace();
					result = API_ERROR.response_error_toJson(599, e.getMessage());
					
					// Exception Log
					logResult.append("RESULT=ERROR");
					logResult.append(System.getProperty("line.separator"));
					logResult.append("ErrorCode=599");
					logResult.append(System.getProperty("line.separator"));
					logResult.append(result);
					logResult.append(System.getProperty("line.separator"));
					logResult.append(e.getMessage());

				}
			}
		} catch (Exception e) {
			result = API_ERROR.response_error_toJson(400, e.getMessage());

			// Log
			logResult.append(result);

		}

		// -- Response --//
		super.displayResponseData(request, response, m_param, ResultCode, result, logResult.toString(), userAgent, userCookie);
	}

	/**
	 * @FileName : 시청목록 삭제 (delete_play_list)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : 선호 키워드 목록 조회 API
	 * @History :
	 */
	@RequestMapping(value = { "/app6/api/delete_play_list" })
	public void delete_play_list(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String function_desc = "시청목록 삭제";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();

		int ResultCode = 0;
		M_delete_play_list m_param = new M_delete_play_list();
		HashMap<String, Object> InputParamMap = new HashMap<String, Object>();
		UserAgent userAgent = null;
		UserCookie userCookie = null;
		
		try {
			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());

				// Log
				logResult.append(result);
				
				// -- Response --//
				super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return;
				
			} else {

				try {

					Boolean ParamCheckResult = m_param.ParamCheck(request, response);

					if (!ParamCheckResult) {
						String errMsg = m_param.ReturnParamMap.get("Error").toString();
						m_param.ReturnParamMap.remove("Error");
						// Bed Request
						result = API_ERROR.response_error_toJson(400, errMsg);
					} else {
						// input 파라미터 외 추가로 필요한 쿠키값 해시맵에 저장
						m_param.ReturnParamMap.put("SAID", userCookie.getSAID());
						InputParamMap.putAll(m_param.ReturnParamMap);
						
						m_param.ReturnParamMap.put("SUID", userCookie.getSUID());

						//컨텐츠 id 콤마 JOIN
						m_param.ContentIDParamJoin();
						
						resultMap = myService.delete_play_list(m_param.ReturnParamMap);

						if (resultMap.get("ResultCode").equals("200")) {
							result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
							ResultCode = 1;
						} else if (resultMap.get("ResultCode").equals("204")) {
							result = API_ERROR.response_error_toJson(204, "");
						}
					}

					
					// Log
					logResult.append(" RESULT=OK ");

				} catch (Exception e) {
					//e.printStackTrace();
					result = API_ERROR.response_error_toJson(599, e.getMessage());
					
					// Log
					logResult.append(" RESULT=ERROR ");
					logResult.append(System.getProperty("line.separator"));
					logResult.append("ErrorCode="+599);
					logResult.append(System.getProperty("line.separator"));
					logResult.append(result);
					logResult.append(System.getProperty("line.separator"));
					logResult.append(e.getMessage());
					
				}
			}
		} catch (Exception e) {
			result = API_ERROR.response_error_toJson(400, e.getMessage());

			// Log
			logResult.append(result);
		}
		
		
		// -- Response --//
		super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
	}

	/**
	 * @FileName : 구매목록 삭제 (delete_buy_list)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : 구매목록 삭제 (delete_buy_list)
	 * @History :
	 */
	@RequestMapping(value = { "/app6/api/delete_buy_list" })
	public void delete_buy_list(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String function_desc = "시청목록 삭제";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();

		int ResultCode = 0;
		M_delete_buy_list m_param = new M_delete_buy_list();
		HashMap<String, Object> InputParamMap = new HashMap<String, Object>();
		UserAgent userAgent = null;
		UserCookie userCookie = null;
		
		try {
			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());

				// Log
				logResult.append(result);
				
				// -- Response --//
				super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return;
			
			} else {
				try {

					Boolean ParamCheckResult = m_param.ParamCheck(request, response);

					if (!ParamCheckResult) {
						String errMsg = m_param.ReturnParamMap.get("Error").toString();
						m_param.ReturnParamMap.remove("Error");
						// Bed Request
						result = API_ERROR.response_error_toJson(400, errMsg);
					} else {
						// input 파라미터 외 추가로 필요한 쿠키값 해시맵에 저장
						InputParamMap.putAll(m_param.ReturnParamMap);
						m_param.ReturnParamMap.put("OTN_SUID", userCookie.getSUID());



							resultMap = myService.delete_buy_list(m_param.ReturnParamMap, request, userAgent, userCookie);

							if (resultMap.get("ResultCode").equals("200")) {
								result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
								ResultCode = 1;
							}
							else if (resultMap.get("ResultCode").equals("204")) {
								result = API_ERROR.response_error_toJson(204, "");
							}
							else if (resultMap.get("ResultCode").equals("414")) {
								result = API_ERROR.response_error_toJson(414, "");
							}
							else if (resultMap.get("ResultCode").equals("424")) {
								result = API_ERROR.response_error_toJson(424, "");
							}
						
					}
					
					// Log
					logResult.append(result);

				} catch (Exception e) {
					//e.printStackTrace();
					result = API_ERROR.response_error_toJson(599, e.getMessage());
					
					logResult.append(result);

				}
			}
		} catch (Exception e) {
			result = API_ERROR.response_error_toJson(400, e.getMessage());

			// Log
			logResult.append(result);
		}
		// -- Response --//
		super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
	}

	/**
	 * @FileName : 홈쇼핑 채널 이용 이력 저장 (send_home_channel_use_info)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : 홈쇼핑 채널 이용 이력 저장 (send_home_channel_use_info)
	 * @History :
	 */
	@RequestMapping(value = { "/app6/api/send_home_channel_use_info" })
	public void send_home_channel_use_info(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String function_desc = "홈쇼핑 채널 이용 이력 저장";
		super.writeApiCallLog(request, response, request.getRequestURI(), function_desc);

		HashMap<String, Object> resultMap = null;
		String result = "";
		StringBuffer logResult = new StringBuffer();

		int ResultCode = 0;
		M_send_home_channel_use_info m_param = new M_send_home_channel_use_info();
		HashMap<String, Object> InputParamMap = new HashMap<String, Object>();

		UserAgent userAgent = null;
		UserCookie userCookie = null;
		try {
			// UserAgent Check
			userAgent = super.CheckUserAgent(request);

			// 로그인 체크 - AUTH0000
			userCookie = super.CheckLogon(request, response) ;
			if (!userCookie.getCheckLogon().equals("AUTH0000")) {
				// 로그인 체크 오류 401에러 - FailSSO 공통메소드로 분류
				result = API_ERROR.FailSSO(401, request, userCookie.getCheckLogon());

				// Log
				logResult.append(result);
				
				// -- Response --//
				super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
				return;
				
			} else {
				try {

					Boolean ParamCheckResult = m_param.ParamCheck(request, response);

					if (!ParamCheckResult) {
						String errMsg = m_param.ReturnParamMap.get("Error").toString();
						m_param.ReturnParamMap.remove("Error");
						// Bed Request
						result = API_ERROR.response_error_toJson(400, errMsg);
					} else {
						// input 파라미터 외 추가로 필요한 쿠키값 해시맵에 저장
						InputParamMap.putAll(m_param.ReturnParamMap);
						m_param.ReturnParamMap.put("SUID", userCookie.getSUID());
						m_param.ReturnParamMap.put("SAID", userCookie.getSAID());
						m_param.ReturnParamMap.put("DEVICE_TYPE", userAgent.getDeviceType());

						resultMap = myService.send_home_channel_use_info(m_param.ReturnParamMap);

						if (resultMap.get("ResultCode").equals("200")) {
							result = API_ERROR.response_success_toJson(200, null, false, false, null, false, null);
							ResultCode = 1;
						}
						else
						{
                            throw new Exception("( 홈쇼핑 채널 이용 이력 저장 실패. )");
						}
					}

				} catch (Exception e) {
					//e.printStackTrace();
					result = API_ERROR.response_error_toJson(599, e.getMessage());

				}
				
				// Log
				logResult.append(result);
			}
		} catch (Exception e) {
			result = API_ERROR.response_error_toJson(400, e.getMessage());

			// Log
			logResult.append(result);
		}

		// -- Response --//
		super.displayResponseData(request, response, InputParamMap, ResultCode, result, logResult.toString(), userAgent, userCookie);
	}

}
