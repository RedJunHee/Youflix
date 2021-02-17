package com.youflix.cust.common;

public class UserCookie {

	private final String cookie;
	//사용자 변수(쿠키)
	private final String CUST_EMAIL;
	private final String SESSION_ID;
	


	public String getCookie() {
		return cookie;
	}

	public UserCookie(String cookie, String cUST_EMAIL, String sESSION_ID) {
		super();
		this.cookie = cookie;
		CUST_EMAIL = cUST_EMAIL;
		SESSION_ID = sESSION_ID;
	}

	public String getCUST_EMAIL() {
		return CUST_EMAIL;
	}

	public String getSESSION_ID() {
		return SESSION_ID;
	}
	
}
