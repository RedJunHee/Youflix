package com.youflix.cust.model;

/**
 * @FileName : M_POPULAR_TYPE_LIST
 * @Project : CUST
 * @Date : 2020.12.09
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_POPULAR_TYPE_LIST implements BaseModel{

	private String CUST_EMAIL;

	public String getCUST_EMAIL() {
		return CUST_EMAIL;
	}

	public void setCUST_EMAIL(String cUST_EMAIL) {
		CUST_EMAIL = cUST_EMAIL;
	}

	@Override
	public String paramCheck() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
