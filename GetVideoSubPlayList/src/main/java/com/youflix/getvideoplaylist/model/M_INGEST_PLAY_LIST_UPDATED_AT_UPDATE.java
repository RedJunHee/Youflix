package com.youflix.getvideoplaylist.model;

/**
 * @FileName : M_INGEST_VIDEO_REGSTER
 * @Project : INGEST
 * @Date : 2021.02.10
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_INGEST_PLAY_LIST_UPDATED_AT_UPDATE  {


	private String YOUTUBER_ID;
	private String PLAY_LIST_UPDATED_AT;
	
	
	public M_INGEST_PLAY_LIST_UPDATED_AT_UPDATE(String yOUTUBER_ID, String pLAY_LIST_UPDATED_AT) {
		super();
		YOUTUBER_ID = yOUTUBER_ID;
		PLAY_LIST_UPDATED_AT = pLAY_LIST_UPDATED_AT;
	}
	
	public String getYOUTUBER_ID() {
		return YOUTUBER_ID;
	}
	public void setYOUTUBER_ID(String yOUTUBER_ID) {
		YOUTUBER_ID = yOUTUBER_ID;
	}
	public String getPLAY_LIST_UPDATED_AT() {
		return PLAY_LIST_UPDATED_AT;
	}
	public void setPLAY_LIST_UPDATED_AT(String pLAY_LIST_UPDATED_AT) {
		PLAY_LIST_UPDATED_AT = pLAY_LIST_UPDATED_AT;
	}
	
	
	
	
	
	


}
