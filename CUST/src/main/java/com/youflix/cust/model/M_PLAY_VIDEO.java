package com.youflix.cust.model;

/**
 * @FileName : M_PLAY_VIDEO
 * @Project : CUST
 * @Date : 2021.02.07
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_PLAY_VIDEO implements BaseModel {

	private String CUST_EMAIL;
	private String VIDEO_ID;
	private String SESSION_ID;
	
	public String getCUST_EMAIL() {
		return CUST_EMAIL;
	}
	public void setCUST_EMAIL(String cUST_EMAIL) {
		CUST_EMAIL = cUST_EMAIL;
	}
	public String getVIDEO_ID() {
		return VIDEO_ID;
	}
	public void setVIDEO_ID(String vIDEO_ID) {
		VIDEO_ID = vIDEO_ID;
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
		sb.append( String.format("VIDEO_ID : %s \n", VIDEO_ID));
		return sb.toString();
	}
	public String paramCheck()
	{
		if ( VIDEO_ID == null || VIDEO_ID.length() == 0)
			return "VIDEO_ID is null";
		return null;
	}
}
