package com.youflix.cust.dao.cust;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.youflix.cust.model.M_CHECK_CUST_EMAIL_DUPLICATE;
import com.youflix.cust.model.M_LOG_OUT;
import com.youflix.cust.model.M_PLAY_END;
import com.youflix.cust.model.M_PLAY_VIDEO;
import com.youflix.cust.model.M_SESSION_CHECK;
import com.youflix.cust.model.M_LOG_IN;
import com.youflix.cust.model.M_SIGN_UP;
import com.youflix.cust.model.ResultMapType2;

@Mapper
public interface CUSTDao {
	public void Sign_Up(M_SIGN_UP mSignUp) throws Exception;
	public List<ResultMapType2> Log_In(M_LOG_IN mSignIp) throws Exception;
	public void Check_Cust_Email_Duplicate(M_CHECK_CUST_EMAIL_DUPLICATE mCheckCustEmailDuplicate) throws Exception;
	public void Session_Check(M_SESSION_CHECK mSessionCheck) throws Exception;
	public void Play_End(M_PLAY_END mPlayEnd) throws Exception;
	public void Log_Out(M_LOG_OUT mPlayEnd) throws Exception;     
}
