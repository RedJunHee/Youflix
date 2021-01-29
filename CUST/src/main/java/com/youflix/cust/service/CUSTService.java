package com.youflix.cust.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youflix.cust.dao.cust.CUSTDao;
import com.youflix.cust.model.ResultMapType2;


@Service
public class CUSTService extends BaseService{

//   @Autowired
//   private LogService logService;
//    
   private final LinkedHashMap<String, String[][]> responseFields = new LinkedHashMap<String, String[][]>() {
		// Value ,  isEncodeUTF8
		private static final long serialVersionUID = 9037841291415888227L;
		{
			put("GetUserData", 
					new String[][] {
						  { "UUID"            	, ""  } 
					    , { "PASSWORD"         	, ""  }
						, { "USER_"             , ""  } 
					}
			);
		};
   };
	@Autowired
	private CUSTDao custDao;

	/**
	 * @FileName : 유저 정보 조회 (GetUserData)
	 * @Project : CUST
	 * @Date : 2021.01.29
	 * @Author : 조 준 희
	 * @Description : 유저 정보 조회
	 * @History :
	 */
	public HashMap<String, Object> GetUserData(HashMap<String, Object> paramMap) throws Exception {
		
    	List<ResultMapType2> resolution_code ;
		//
		try {
			resolution_code = custDao.GetUserData(paramMap);
			
			return ResponseDatatoController(200, resolution_code);

		} catch (Exception ex) {
			throw ex;
		}
	}
}

