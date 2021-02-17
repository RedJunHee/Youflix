package com.youflix.cust.model;

/**
 * @FileName : M_SIGN_IN
 * @Project : CUST
 * @Date : 2020.12.09
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_SIGN_IN implements BaseModel{

	private String CUST_EMAIL;
	private String PASSWORD;
	private String RES;
	

	public String getCUST_EMAIL() {
		return CUST_EMAIL;
	}
	public void setCUST_EMAIL(String cUST_EMAIL) {
		CUST_EMAIL = cUST_EMAIL;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
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
		sb.append( String.format("PASSWORD : %s \n", PASSWORD));
		sb.append( String.format("RES : %s \n", RES));
		return sb.toString();
	}

	public String paramCheck()
	{
		if ( CUST_EMAIL == null || CUST_EMAIL.length() == 0)
			return "CUST_EMAIL is null";
		if ( PASSWORD == null || PASSWORD.length() == 0)
			return "PASSWORD is null";
		return null;
	}
}
