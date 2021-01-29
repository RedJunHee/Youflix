package com.youflix.cust.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youflix.cust.dao.log.LOGDao;
import com.youflix.cust.model.ResultMapType2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
 

@Service
public class BaseService {
	    

	//결과 코드와 가공 JSON 데이터를 묶어 Controller에 반환, meta,data 형식의 출력 가공은 controller에서 일괄 수행 
	public HashMap<String, Object> ResponseDatatoController(int result_code, Object resultDesc) {
		// TODO Auto-generated method stub
		HashMap<String, Object> ReturnHashMap = new HashMap<String, Object>();
		ReturnHashMap.put("ResultCode", Integer.toString(result_code) );
		if(resultDesc!=null) {
			ReturnHashMap.put("ResultDesc", resultDesc);
		}
		return ReturnHashMap;
	}

	
	/**

	 * @throws UnsupportedEncodingException 
	 * @FileName    : restructure_data
	    * @Project     : ContentAPI MY
	    * @Date        : 2020.11.20
	    * @Author      : 조 준 희
	    * @Description : Output데이터를 가져와 응답 필드에 맞게 응답구조 제작
	    * @History     :
	    */
	public List<ResultMapType2> restructure_data(List<ResultMapType2> oriData, String[][] responseFields) throws UnsupportedEncodingException  {
		
		List<ResultMapType2> tempData = new ArrayList<ResultMapType2>();
		
		//-- --//
		
		if( oriData!= null && oriData.size()>0 && responseFields != null && responseFields.length > 0 ) {
			
			for(int i=0; i<oriData.size(); i++) {
				
				ResultMapType2 tempMap = new ResultMapType2();
				//
				for(int j=0; j<responseFields.length; j++) {
					
					String key           = responseFields[j][0].trim();
					Object val           = null;
					String defaultVal    = responseFields[j][1].trim(); 
					boolean isEncodeUTF8 = ( "Y".equalsIgnoreCase( responseFields[j][2].trim() ) ) ? true : false;

					//-- --//
					
					if( oriData.get(i).containsKey( key ) ) {
						
						
						
						if( oriData.get(i).get( key ) != null && oriData.get(i).getString( key ).trim().length() > 0 )
							val = oriData.get(i).get( key ).toString() ;
						else
							val = defaultVal;
						
						if(isEncodeUTF8)
								val = URLEncoder.encode(val.toString(),"UTF-8");

						
						tempMap.put(key, (val != null && val instanceof java.lang.String) ? val.toString().trim()  : val); 
						
					} else {
						
						tempMap.put(key, defaultVal);
						
					}
				
					
			    }
				tempData.add(tempMap);
			 
	     	}
	
        }
		return tempData;
	}

	
	@Autowired
	private LOGDao logDao;
	
	
	
	public void GetMsg(HashMap<String, String> paramMap) throws SQLException {


		//
		try {

			logDao.SendServiceLog(paramMap);

		} catch (Exception ex) {
			System.out.print(ex.toString());
			//log.error(" << Parameters >> [" + params.toString() + "] << Exception >> [" + ex.toString() + "]", ex);

		} finally {

			//log.debug(" << Result >> [" + ( result != null ? result.toString() : "" ) + "]" );

		}
	}



}

