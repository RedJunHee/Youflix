package com.youflix.getvideoplaylist.model;

/**
 * @FileName : M_INGEST_PLAY_LIST_REGSTER
 * @Project : INGEST
 * @Date : 2021.02.10
 * @Author : 조 준 희
 * @Description : 
 * @ModelParameters : 
 * @History :
 */

public class M_INGEST_PLAY_LIST_REGSTER  {

	private String YOUTUBER_ID;
	private String TITLE;
	private String PLAY_LIST_ID;
	
	
	public M_INGEST_PLAY_LIST_REGSTER(String yOUTUBER_ID, String tITLE, String pLAY_LIST_ID) {
		super();
		YOUTUBER_ID = yOUTUBER_ID;
		TITLE = tITLE;
		PLAY_LIST_ID = pLAY_LIST_ID;
	}
	
	public String getYOUTUBER_ID() {
		return YOUTUBER_ID;
	}
	public void setYOUTUBER_ID(String yOUTUBER_ID) {
		YOUTUBER_ID = yOUTUBER_ID;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getPLAY_LIST_ID() {
		return PLAY_LIST_ID;
	}
	public void setPLAY_LIST_ID(String pLAY_LIST_ID) {
		PLAY_LIST_ID = pLAY_LIST_ID;
	}

	

}
