package com.youflix.cust.dao.cust;


import org.apache.ibatis.annotations.Mapper;

import com.youflix.cust.model.M_CHECK_CUST_EMAIL_DUPLICATE;
import com.youflix.cust.model.M_SIGN_IN;
import com.youflix.cust.model.M_SIGN_UP;

@Mapper
public interface CUSTDao {
	public void  Sign_Up(M_SIGN_UP mSignUp) throws Exception;
	public void  Sign_In(M_SIGN_IN mSignIp) throws Exception;
	public void Check_Cust_Email_Duplicate(M_CHECK_CUST_EMAIL_DUPLICATE mCheckCustEmailDuplicate) throws Exception;
}
