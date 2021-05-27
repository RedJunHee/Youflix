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

public class M_CUST_PURCHASE implements BaseModel {

	private String CUST_EMAIL;
	private String MEMBERSHIP_ID;
	private String RES;




	public String getCUST_EMAIL() {
		return CUST_EMAIL;
	}

	public void setCUST_EMAIL(String cUST_EMAIL) {
		CUST_EMAIL = cUST_EMAIL;
	}

	public String getMEMBERSHIP_ID() {
		return MEMBERSHIP_ID;
	}

	public void setMEMBERSHIP_ID(String mEMBERSHIP_ID) {
		MEMBERSHIP_ID = mEMBERSHIP_ID;
	}

	public String getRES() {
		return RES;
	}

	public void setRES(String rES) {
		RES = rES;
	}
	
	public String paramCheck()
	{
		if ( MEMBERSHIP_ID == null || MEMBERSHIP_ID.length() == 0)
			return "MEMBERSHIP_ID is null";

		return null;
	}
	
}
