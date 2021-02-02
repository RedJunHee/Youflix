package com.youflix.cust.model;

/**
 * @FileName : M_CHECK_CUST_EMAIL_DUPLICATE
 * @Project : CUST
 * @Date : 2020.12.09
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_CHECK_CUST_EMAIL_DUPLICATE implements BaseModel {

	private String CUSTEMAIL;
	private String RES;
	
	public String getCUSTEMAIL() {
		return CUSTEMAIL;
	}
	public void setCUSTEMAIL(String cUSTEMAIL) {
		CUSTEMAIL = cUSTEMAIL;
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
		sb.append( String.format("CUSTEMAIL : %s \n", CUSTEMAIL));
		sb.append( String.format("RES : %s \n", RES));
		return sb.toString();
	}
}
