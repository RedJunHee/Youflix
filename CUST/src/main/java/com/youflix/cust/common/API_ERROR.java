package com.youflix.cust.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.youflix.cust.model.ResultMapType2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youflix.cust.util.StringUtil;


public final class API_ERROR{
	

	public static HashMap<String, String> ERROR_CODES = new HashMap<String, String>() {
		private static final long serialVersionUID = -2615409978546784333L;
		{
			put("T:200", "OK"); 
			put("M:200", "성공");
			
			put("T:201", "DUPLICATE VALUES"); 
			put("M:201", "중복된 값입니다.");	
			
			put("T:400", "Bad Request"); 
			put("M:400", "문법상 또는 파라미터 오류가 있어서 서버가 요청사항을 처리하지 못함");
			
			put("T:401", "Unauthorized"); 
			put("M:401", "인증 실패. 사용권한 없음");
			
			put("T:599", "System Error"); 
			put("M:599", "시스템 오류.");
			
		}
	};
	

	/**
	 * @throws Exception 
	 * @FileName : 에러응답 Json 반환
	 * @Project : CUST
	 * @Date : 2021.01.29
	 * @Author : 조 준 희
	 * @Description : 에러코드 + 에러 메시지
	 * @History :
	 */
	
	public static String response_error_toJson(int errorCode, String errorMessage) {
		
		ObjectMapper mapper = new ObjectMapper();
	      String result = null;

	         try {
				result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response_error(errorCode, errorMessage));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return result;
	}
	
	
	
	/**
	 * @FileName : response_error
	 * @Project : CUST
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : 에러코드에 맞는 에러메시지와  + 에러 내용으로 해쉬맵 생성 
	 * @History :
	 */
	public static HashMap<String, Object> response_error(int errorCode, String errorMessage) {
		
		if( errorCode == 0)
			errorCode = 599;

		if( !ERROR_CODES.containsKey( ("T:" + errorCode ).trim() ) ||
			!ERROR_CODES.containsKey( ("M:" + errorCode ).trim() ) )
			errorCode = 599;

		return response_error(
			  errorCode
			, ERROR_CODES.get( ("T:" + errorCode ).trim() ) 
			, ERROR_CODES.get( ("M:" + errorCode ).trim() )+ " " +errorMessage
		);
		
	}

	
	/**
	 * @FileName : 정상적인 최종 응답 JSON생성
	 * @Project : CUST
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : 코드, 결과해쉬맵, Count출력 여부, List형식 출력여부, 토탈 카운트, Paging여부, 페이지 
	 * @History :
	 */
	public static String response_success_toJson(int code, List<ResultMapType2> resultDesc, boolean isPrintCount, boolean isPrintList, String total_count,boolean isPaging, String page) {
		HashMap<String, Object> metaMap   = new HashMap<String, Object>();
		metaMap.put("code", code);

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if(resultDesc!=null) {
			if(isPrintList)
			{
				HashMap<String, Object> listMap   = new HashMap<String, Object>();
				listMap.put("list", resultDesc);
			
				if(isPrintCount)
				{
					listMap.put("count", Integer.toString(resultDesc.size()));
				}
				
				if(isPaging)
				{
					listMap.put("page", page);	
				}
				
				listMap.put("total_count", total_count);
				
				resultMap.put("data", listMap);
			}
			else
			{
				resultMap.put("data", resultDesc.get(0));	
			}
		}
		resultMap.put("meta", metaMap);
		ObjectMapper mapper = new ObjectMapper();
	      String result = null;
	      try {
	         result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
		return result;
	}

	
	/**
	 * @FileName : response_error
	 * @Project : CUST
	 * @Date : 2020.11.20
	 * @Author : 조 준 희
	 * @Description : code = 코드값, errorType = 에러타입, errorMessage = 추가적인 에러메시지 
	 * @History :
	 */
	
	public static HashMap<String, Object> response_error(int code, String errorType, String errorMessage) {
		
		LinkedHashMap<String, Object> metaMap   = new LinkedHashMap<String, Object>();
		metaMap.put("code         ".trim(), code                        );
		metaMap.put("error_type   ".trim(), errorType                   );
		metaMap.put("error_message".trim(), errorMessage );

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("meta", metaMap);

		return resultMap;				
		
	}

	
	
}
	
	
