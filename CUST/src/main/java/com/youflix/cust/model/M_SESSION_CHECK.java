package com.youflix.cust.model;

/**
 * @FileName : M_SESSION_CHECK
 * @Project : CUST
 * @Date : 2021.02.07
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_SESSION_CHECK implements BaseModel {

	private String CUST_EMAIL;
	private String RES;
	

	public String getCUST_EMAIL() {
		return CUST_EMAIL;
	}
	public void setCUST_EMAIL(String cUST_EMAIL) {
		CUST_EMAIL = cUST_EMAIL;
	}
	public String getRES() {
		return RES;
	}
	public void setRES(String rES) {
		RES = rES;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append( String.format("CUST_EMAIL : %s \n", CUST_EMAIL));
		sb.append( String.format("RES : %s \n", RES));
		return sb.toString();
	}
	public String paramCheck()
	{
		if ( CUST_EMAIL == null || CUST_EMAIL.length() == 0)
			return "CUST_EMAIL is null";
		
		return null;
	}
}
