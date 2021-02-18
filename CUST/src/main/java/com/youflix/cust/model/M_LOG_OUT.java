package com.youflix.cust.model;

/**
 * @FileName : M_LOG_OUT
 * @Project : CUST
 * @Date : 2021.02.07
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_LOG_OUT implements BaseModel {

	private String SESSION_ID;
	private String RES;



	public String getRES() {
		return RES;
	}


	public void setRES(String rES) {
		RES = rES;
	}


	public String getSESSION_ID() {
		return SESSION_ID;
	}


	public void setSESSION_ID(String sESSION_ID) {
		SESSION_ID = sESSION_ID;
	}


	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append( String.format("SESSION_ID : %s \n", SESSION_ID));
		return sb.toString();
	}
	public String paramCheck()
	{
		if ( SESSION_ID == null || SESSION_ID.length() == 0)
			return "SESSION_ID is null";

		return null;
	}
}
