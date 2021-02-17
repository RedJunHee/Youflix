package com.youflix.cust.exception;

public class CookieValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8847859511190191711L;

	public CookieValidationException(String cookie)
	{
		super("Youflix Cookie id Validation Faild. Cookie = " + cookie);
		
	}
}
