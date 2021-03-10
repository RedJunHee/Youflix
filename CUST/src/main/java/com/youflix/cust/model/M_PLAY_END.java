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

public class M_PLAY_END implements BaseModel {

	private String SESSION_ID;
	private String VIDEO_ID;
	private Integer JUMP_POINT;

	
	public String getSESSION_ID() {
		return SESSION_ID;
	}


	public void setSESSION_ID(String sESSION_ID) {
		SESSION_ID = sESSION_ID;
	}


	public String getVIDEO_ID() {
		return VIDEO_ID;
	}

	public void setVIDEO_ID(String vIDEO_ID) {
		VIDEO_ID = vIDEO_ID;
	}

	public Integer getJUMP_POINT() {
		return JUMP_POINT;
	}


	public void setJUMP_POINT(Integer jUMP_POINT) {
		JUMP_POINT = jUMP_POINT;
	}


	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append( String.format("SESSION_ID : %s \n", SESSION_ID));
		sb.append( String.format("VIDEO_ID : %s \n", VIDEO_ID));
		sb.append( String.format("JUMP_POINT : %s \n", JUMP_POINT));
		return sb.toString();
	}
	public String paramCheck()
	{
		if ( SESSION_ID == null || SESSION_ID.length() == 0)
			return "SESSION_ID is null";
		if ( VIDEO_ID == null || VIDEO_ID.length() == 0)
			return "VIDEO_ID is null";
		if ( JUMP_POINT == null)
			return "JUMP_POINT is null";
		return null;
	}
}
