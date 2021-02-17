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

	private String VIDEO_ID;

	public String getVIDEO_ID() {
		return VIDEO_ID;
	}
	public void setVIDEO_ID(String vIDEO_ID) {
		VIDEO_ID = vIDEO_ID;
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
