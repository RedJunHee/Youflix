package com.youflix.cust.service;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youflix.cust.dao.log.LOGDao;

@Service
public class LogService {

//	@Autowired
//	private Producer producer;
	
	
	@Autowired
	private LOGDao logDao;
	
	private static Logger logger_get_my_keyword = LoggerFactory.getLogger("Get_User_Data");

	/**
	 * @FileName : DB Service Log Write
	 * @Project : CUST
	 * @Date : 2021.01.29
	 * @Author : 조 준 희
	 * @Description : ServiceLogManager.WriteServiceLog(request, '2020-12-11 00:00:00', "delete_buy_list", result, log_MSG, userAgent, userCookie);
	 * @History :
	 */
	@Async("executorSMLS")
	public void WriteServiceLog(String function_desc, String api_startTime, Long startTime, Long endTime,
			String apiName, int Result, StringBuffer log_MSG) 
	{
			final Long elapsed_time = endTime - startTime;

			HashMap<String, String> paramServLog = new HashMap<String, String>();
			paramServLog.put("LOG_TIME", api_startTime.toString());
//			paramServLog.put("SUID", userCookie.getSUID());
//			paramServLog.put("SAID", userCookie.getSAID());
			paramServLog.put("FUNCTION", apiName);
			paramServLog.put("FUNCTION_DESC", function_desc);
			paramServLog.put("LOG_DESC", log_MSG.toString());
			paramServLog.put("RESULT", Integer.toString(Result));
			paramServLog.put("ELAPSEDTIME", elapsed_time.toString());
			try {
				logDao.SendServiceLog(paramServLog);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//			String result = gson.toJson(paramServLog);			
//			producer.send(result);
	}

	/**
	 * @FileName : DB Service Log Write (+ funcName)
	 * @Project : ContentAPI MY
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : ServiceLogManager.WriteServiceLog(request, '2020-12-11 00:00:00', "delete_buy_list", "SMLS 로그", result, log_MSG, userAgent, userCookie);
	 * @History :
	 */
	@Async("executorSMLS")
	public void WriteServiceLog(String function_desc, String api_startTime, Long startTime, Long endTime, String apiName, String funcName, int Result,
			StringBuffer log_MSG) 
	{
		final Long elapsed_time = endTime - startTime;

		HashMap<String, String> paramServLog = new HashMap<String, String>();
		paramServLog.put("LOG_TIME", api_startTime.toString());
//		paramServLog.put("SUID", userCookie.getSUID());
//		paramServLog.put("SAID", userCookie.getSAID());
		paramServLog.put("FUNCTION", apiName);
		paramServLog.put("FUNCTION_DESC", function_desc);
		paramServLog.put("LOG_DESC", log_MSG.toString());
		paramServLog.put("RESULT", Integer.toString(Result));
		paramServLog.put("ELAPSEDTIME", elapsed_time.toString());
		try {
			logDao.SendServiceLog(paramServLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//		String result = gson.toJson(paramServLog);			
//		producer.send(result);
	}

	public static void WriteFileLog(String apiName, StringBuffer log_MSG) {

		if (log_MSG.toString().isEmpty())
			return;

		switch (apiName) {
		case "get_user_data":
			logger_get_my_keyword.info(log_MSG.toString());
			break;

		}

	}
}
