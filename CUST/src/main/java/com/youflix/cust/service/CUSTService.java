package com.youflix.cust.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youflix.cust.dao.cust.CUSTDao;
import com.youflix.cust.model.M_CHECK_CUST_EMAIL_DUPLICATE;
import com.youflix.cust.model.M_SESSION_CHECK;
import com.youflix.cust.model.M_SIGN_IN;
import com.youflix.cust.model.M_SIGN_UP;
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
			put("Sing_Up", 
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
	 * @FileName : 사용자 회원가입 (Sign_Up)
	 * @Project : CUST
	 * @Date : 2021.01.29
	 * @Author : 조 준 희
	 * @Description : 사용자 회원가입
	 * @History :
	 */
	public HashMap<String, Object> Sign_Up(M_SIGN_UP mSignUp) throws Exception {	
    	
		try {
			 custDao.Sign_Up(mSignUp);

			return ResponseDatatoController(Integer.parseInt(mSignUp.getRES()), "");
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * @FileName : test (test)
	 * @Project : CUST
	 * @Date : 2021.01.29
	 * @Author : 조 준 희
	 * @Description : test
	 * @History :
	 */
	public HashMap<String, Object> test() throws Exception {	
		//
		try {

			return ResponseDatatoController(200, "");

		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * @FileName : 사용자 로그인 (Sign_Up)
	 * @Project : CUST
	 * @Date : 2021.01.29
	 * @Author : 조 준 희
	 * @Description : 사용자 회원가입
	 * @History :
	 */
	public HashMap<String, Object> Sign_In(M_SIGN_IN mSignIn) throws Exception {	
		//
		try {
			custDao.Sign_In(mSignIn);
			
			return ResponseDatatoController(Integer.parseInt(mSignIn.getRES()), "");

		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * @FileName : Email 중복 체크 (Check_Cust_Email_Duplicate)
	 * @Project : CUST
	 * @Date : 2021.01.29
	 * @Author : 조 준 희
	 * @Description : 사용자 회원가입
	 * @History :
	 */
	public HashMap<String, Object> Check_Cust_Email_Duplicate(M_CHECK_CUST_EMAIL_DUPLICATE mCheckCustEmailDuplicate) throws Exception {	
		//
		try {
			custDao.Check_Cust_Email_Duplicate(mCheckCustEmailDuplicate);
			
			return ResponseDatatoController(Integer.parseInt(mCheckCustEmailDuplicate.getRES()), "");

		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * @FileName : 세션 체크 (Session_Check)
	 * @Project : CUST
	 * @Date : 2021.02.07
	 * @Author : 조 준 희
	 * @Description : 사용자 회원가입
	 * @History :
	 */
	public HashMap<String, Object> Session_Check(M_SESSION_CHECK mSessionCheck) throws Exception {	
		//
		try {
			custDao.Session_Check(mSessionCheck);
			
			return ResponseDatatoController(Integer.parseInt(mSessionCheck.getRES()), "");

		} catch (Exception ex) {
			throw ex;
		}
	}
	
}

