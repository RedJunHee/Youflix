package com.youflix.cust.service;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youflix.cust.controller.ControllerBase.DBLogType;
import com.youflix.cust.dao.log.LOGDao;

@Service
public class LogService {

//	@Autowired
//	private Producer producer;
	
	
	@Autowired
	private LOGDao logDao;
	
	private static Logger logger_sign_up = LoggerFactory.getLogger("sign_up");

	/**
	 * @FileName : DB Service Log Write
	 * @Project : CUST
	 * @Date : 2021.01.29
	 * @Author : 조 준 희
	 * @Description : ServiceLogManager.WriteServiceLog(request, '2020-12-11 00:00:00','2020-12-11 00:00:00', "sign_up", result, log_MSG);
	 * @History :
	 */
	//@Async("executorSMLS")
	public void WriteServiceLog(String functionDesc, Long startTime, Long endTime, String apiName, DBLogType Result, String log_MSG) 
	{
			final Long elapsed_time = endTime - startTime;

			HashMap<String, String> paramServLog = new HashMap<String, String>();
			paramServLog.put("API_NAME", apiName);
			paramServLog.put("API_DESCRIPTION", functionDesc);
			paramServLog.put("LOG_DESCRIPTION", log_MSG);
			paramServLog.put("RESULT", Integer.toString(Result.GET_DBLogType()));
			paramServLog.put("ELAPSED_TIME", elapsed_time.toString());
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


	public static void WriteFileLog(String apiName, String log_MSG) {

		if (log_MSG.toString().isEmpty())
			return;

		switch (apiName) {
		case "sign_up":
			logger_sign_up.info(log_MSG);
			break;

		}

	}
}
