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

public class M_CUST_REFUND implements BaseModel {

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
	
	public String paramCheck()
	{

		return null;
	}
	
}
